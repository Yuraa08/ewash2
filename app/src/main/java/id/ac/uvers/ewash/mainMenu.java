package id.ac.uvers.ewash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
    private RecyclerView.Adapter adp ;
    private RecyclerView.LayoutManager lmrc;
    private List<LaundrylistsItem> listln;
    private SwipeRefreshLayout refreshdata;
    ImageView account, history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        recyclerView = findViewById(R.id.rclaundrylist);
        refreshdata = findViewById(R.id.swprf);
        lmrc = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lmrc);
        account = findViewById(R.id.btnprofilll);
        history = findViewById(R.id.btnhistoryll);

        refreshdata.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshdata.setRefreshing(true);
                tp_data();
                refreshdata.setRefreshing(false);
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
    }


