package id.ac.uvers.ewash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import id.ac.uvers.ewash.Adapter.adapterC;
import id.ac.uvers.ewash.Adapter.orderlaundryAdapter;
import id.ac.uvers.ewash.Adapter.orderlaundryAdapter2;
import id.ac.uvers.ewash.model.response.laundrylist.Responselaundrylist;
import id.ac.uvers.ewash.model.response.pricelist.PricelistItem;
import id.ac.uvers.ewash.model.response.pricelist.ResponsePricelist;
import id.ac.uvers.ewash.retrofit.ApiEndpoint;
import id.ac.uvers.ewash.retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;

public class orderlaundry extends AppCompatActivity {

    private String kategori, service, namalaundry;
    TextView detailnamalaundry, kategorijs, services;

    ImageView back, info;
    Button order;
    ScrollView scorderl;

    private String strnama, strkategori, strservice, strqbjh, strqbk, strqbc, strqsl, strqsl2,strqgr;
    private List<PricelistItem> laundryprice = new ArrayList<>();
    private orderlaundryAdapter olAdapter;
    private orderlaundryAdapter2 oladapter2;
    private ListView productlist;
    private static final String URL = "https://ewash22.000webhostapp.com/databaseewash/pricelist2.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlaundry);

        Intent next = getIntent();
        kategori=next.getStringExtra("selectjw");
        service=next.getStringExtra("selects");
        namalaundry=next.getStringExtra("nama");

        detailnamalaundry = findViewById(R.id.namalaundrydetail2);
        kategorijs = findViewById(R.id.kategori);
        services = findViewById(R.id.service);
        order = findViewById(R.id.btnorder);

        detailnamalaundry.setText(namalaundry);
        kategorijs.setText(kategori);
        services.setText(service);

        back = findViewById(R.id.btnback2);
        info = findViewById(R.id.btninfo2);


        productlist = findViewById(R.id.productlist);

        tp_dataa();

        //tp_harga();
        //oladapter2 = new orderlaundryAdapter2(orderlaundry.this,laundryprice);
        //productlist.setAdapter(oladapter2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(orderlaundry.this,mainMenu.class);
                startActivity(back);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent info = new Intent(orderlaundry.this,infolaundry.class);
                startActivity(info);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strnama =  detailnamalaundry.getText().toString().trim();
                strkategori = kategorijs.getText().toString().trim();
                strservice = services.getText().toString().trim();
                Intent order = new Intent(getApplicationContext(),finalorder.class);
                order.putExtra("nama",strnama);
                order.putExtra("kategori",strkategori);
                order.putExtra("service",strservice);
                order.putExtra("bjhqty",strqbjh);
                order.putExtra("bkqty",strqbk);
                order.putExtra("bcqty",strqbc);
                order.putExtra("slqty",strqsl);
                startActivity(order);
            }
        });

    }

//    private void tp_harga() {
//        JsonArrayRequest jarr = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                for (int i = 0; i<response.length(); i ++){
//                    try{
//                        JSONObject obj = response.getJSONObject(i);
//                        PricelistItem pli = new PricelistItem();
//
//                        pli.setBajuN(obj.getString());
//                    }
//                }
//            }
//        })
//    }

    public void tp_dataa() {
        ApiEndpoint apidata = ApiService.getClient().create(ApiEndpoint.class);
        Call<ResponsePricelist> pllist = apidata.pllist();

        pllist.enqueue(new Callback<ResponsePricelist>() {
            @Override
            public void onResponse(Call<ResponsePricelist> call, retrofit2.Response<ResponsePricelist> response) {
                int id = response.body().getId();
                String msg = response.body().getMsg();

                laundryprice = response.body().getPricelist();
                olAdapter = new orderlaundryAdapter(orderlaundry.this,laundryprice);
                productlist.setAdapter(olAdapter);
            }

            @Override
            public void onFailure(Call<ResponsePricelist> call, Throwable t) {

            }
        });
    }
}