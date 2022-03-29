package id.ac.uvers.ewash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import id.ac.uvers.ewash.Adapter.adapterC;
import id.ac.uvers.ewash.model.laundrylist.laundrylist;
import id.ac.uvers.ewash.model.laundrylist.responlaundrylist;
import id.ac.uvers.ewash.retrofit.ApiEndpoint;
import id.ac.uvers.ewash.retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mainMenu extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adp ;
    private RecyclerView.LayoutManager lmrc;
    private List<laundrylist> listln;
    private SwipeRefreshLayout refreshdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        recyclerView = findViewById(R.id.rclaundrylist);
        refreshdata = findViewById(R.id.swprf);
        lmrc = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lmrc);



        refreshdata.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshdata.setRefreshing(true);
                tp_data();
                refreshdata.setRefreshing(false);
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
        Call<responlaundrylist> tdata = apidata.lnlist();

        tdata.enqueue(new Callback<responlaundrylist>() {
            @Override
            public void onResponse(Call<responlaundrylist> call, Response<responlaundrylist> response) {
                int id_laundry = response.body().getId_laundry();
                String msg = response.body().getMsg();

                listln = response.body().getLaundrylists();
                adp = new adapterC(mainMenu.this,listln);
                recyclerView.setAdapter(adp);

            }

            @Override
            public void onFailure(Call<responlaundrylist> call, Throwable t) {
                Toast.makeText(mainMenu.this, "GAGAL KONEK"+t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Error.Response", t.toString());
            }
        });

    }
    }


