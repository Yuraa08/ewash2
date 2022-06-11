package id.ac.uvers.ewash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;

import id.ac.uvers.ewash.Adapter.adapterC;
import id.ac.uvers.ewash.model.response.laundrylist.LaundrylistsItem;
import id.ac.uvers.ewash.model.response.laundrylist.Responselaundrylist;
import id.ac.uvers.ewash.retrofit.ApiEndpoint;
import id.ac.uvers.ewash.retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mainMenu extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adp;
    private RecyclerView.LayoutManager lmrc;
    private List<LaundrylistsItem> listln;
    private SwipeRefreshLayout refreshdata;
    LinearLayout lokasiC;
    ImageView account, history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        recyclerView = findViewById(R.id.rclaundrylist);
        refreshdata = findViewById(R.id.swprf);
        lmrc = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lmrc);
        account = findViewById(R.id.btnprofilll);
        history = findViewById(R.id.btnhistoryll);
        lokasiC = findViewById(R.id.lokasiC);

        refreshdata.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshdata.setRefreshing(true);
                tp_data();
                refreshdata.setRefreshing(false);
            }
        });

        getlocation ();

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        lokasiC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check permission
                if (ActivityCompat.checkSelfPermission(mainMenu.this,Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED){
                    Location1();
                }
                else {
                    // if permission denied
                    ActivityCompat.requestPermissions(mainMenu.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},23);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tp_data();
    }

    public void tp_data () {
        ApiEndpoint apidata =  ApiService.getClient().create(ApiEndpoint.class);
        Call<Responselaundrylist> tdata = apidata.lnlist();

        tdata.enqueue(new Callback<Responselaundrylist>() {
            @Override
            public void onResponse(Call<Responselaundrylist> call, Response<Responselaundrylist> response) {
                int id_laundry = response.body().getIdLaundry();
                String msg = response.body().getMsg();

                listln = response.body().getLaundrylists();
                adp = new adapterC(mainMenu.this,listln);
                recyclerView.setAdapter(adp);
            }

            @Override
            public void onFailure(Call<Responselaundrylist> call, Throwable t) {
                Toast.makeText(mainMenu.this, "GAGAL KONEK"+t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Error.ResponsePricelist", t.toString());
            }
        });

    }

    private void getlocation() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(mainMenu.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(mainMenu.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .check();

    }

    private void Location1(){
        LocationRequest request = LocationRequest.create();
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setInterval(5000);
        request.setFastestInterval(2000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(request);
        builder.setAlwaysShow(true);
        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext()).checkLocationSettings(builder.build());
        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    }


