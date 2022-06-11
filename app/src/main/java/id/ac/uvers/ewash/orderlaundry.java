package id.ac.uvers.ewash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import id.ac.uvers.ewash.Adapter.orderlaundryAdapter2;
import id.ac.uvers.ewash.model.response.pricelist.PricelistItem;
import id.ac.uvers.ewash.model.response.pricelist.ResponsePricelist;
import id.ac.uvers.ewash.retrofit.ApiEndpoint;
import id.ac.uvers.ewash.retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class orderlaundry extends AppCompatActivity {

    private String namalaundry, userlaundry;
    TextView detailnamalaundry, kategorijs, services, userldry;

    ImageView back, info, bjhmin, bjhplus, bkmin, bkplus, bcmin, bcplus, slmin, slplus, slmin2, slplus2, grmin, grplus;
    Button order;
    EditText bjhqty, bkqty, bcqty, slqty, slqty2, grqty;
    ScrollView scorderl;

    private String userlaundryy, strnama, strkategori, strservice, strqbjh, strqbk, strqbc, strqsl, strqsl2, strqgr;
    private TextView hargabjh, hargabk, hargabc, hargasl1, hargasl2, hargagr;

    int ibjhqty = 0, ibkqty = 0, ibcqty = 0, islqty = 0, islqty2 = 0, igrqty = 0;
    //    private orderlaundryAdapter olAdapter;
    private LinearLayout productlist;
    private List<PricelistItem> pllist;
//    private static final String URL = "https://ewash22.000webhostapp.com/databaseewash/pricelist2.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlaundry);

        // Get data sent from detail laundry activity
        Intent next = getIntent();
        String kategori = next.getStringExtra("selectjw");
        String service = next.getStringExtra("selects");
        namalaundry = next.getStringExtra("nama");
        userlaundry = next.getStringExtra("userlaundry");

        detailnamalaundry = findViewById(R.id.namalaundrydetail2);
        kategorijs = findViewById(R.id.kategori);
        services = findViewById(R.id.service);
        userldry = findViewById(R.id.userldry);

        /*Qty Text*/
        bjhqty = findViewById(R.id.bjhqty);

        /*Set Qty*/
        bjhqty.setText(String.valueOf(ibjhqty));

        /*Plus and Minus Buttons*/
        bjhmin = findViewById(R.id.bjhmin);
        bjhplus = findViewById(R.id.bjhplus);
        bkmin = findViewById(R.id.bkmin);
        bkplus = findViewById(R.id.bkplus);
        bcmin = findViewById(R.id.bcmin);
        bcplus = findViewById(R.id.bcplus);
        slmin = findViewById(R.id.slmin1);
        slplus = findViewById(R.id.slplus1);
        slmin2 = findViewById(R.id.slmin2);
        slplus2 = findViewById(R.id.slplus2);
        grmin = findViewById(R.id.grmin);
        grplus = findViewById(R.id.grplus);

        /*Text Views*/
        hargabjh = findViewById(R.id.hargabjhd);
        hargabk = findViewById(R.id.hargabkd);
        hargabc = findViewById(R.id.hargabcd);
        hargasl1 = findViewById(R.id.hargasld1);
        hargasl2 = findViewById(R.id.hargasld2);
        hargagr = findViewById(R.id.hargagrd);

        /*Linear Layouts*/
        LinearLayout lyboneka = findViewById(R.id.lyboneka);
        LinearLayout lybedcover = findViewById(R.id.lybedcover);
        LinearLayout lyselimutb = findViewById(R.id.lyselimutb);
        LinearLayout lyselimutk = findViewById(R.id.lyselimutk);
        LinearLayout lygorder = findViewById(R.id.lygorden);

        detailnamalaundry.setText(namalaundry);
        kategorijs.setText(kategori);
        services.setText(service);
        userldry.setText(userlaundry);

        if (service.equals("Setrika")) {
            lyboneka.setVisibility(View.GONE);
            lybedcover.setVisibility(View.GONE);
            lyselimutb.setVisibility(View.GONE);
            lyselimutk.setVisibility(View.GONE);
            lygorder.setVisibility(View.GONE);
        }

        order = findViewById(R.id.btnorder);
        back = findViewById(R.id.btnback2);
        info = findViewById(R.id.btninfo2);

        // get product list layout
        productlist = findViewById(R.id.productlist);

//        Log.d("UVEEEEEEEEEERS", service);

        get_current_laundry(kategori, service);

//        tp_harga();
//        oladapter2 = new orderlaundryAdapter2(orderlaundry.this, laundryprice);
//        productlist.setAdapter(oladapter2);

        /**
         * set onclick listener for back button
         */
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(orderlaundry.this, detailLaundry.class);
                back.putExtra("nama", namalaundry);
                startActivity(back);
            }
        });

        /**
         * set onclick listener for info button
         */
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent info = new Intent(orderlaundry.this, infolaundry.class);
                startActivity(info);
            }
        });

        /*
          set onclick listener for order button
         */
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strnama = detailnamalaundry.getText().toString().trim();
                strkategori = kategorijs.getText().toString().trim();
                strservice = services.getText().toString().trim();
                Intent order = new Intent(getApplicationContext(), finalorder.class);
                order.putExtra("nama", strnama);
                order.putExtra("kategori", strkategori);
                order.putExtra("service", strservice);
                order.putExtra("bjhqty", strqbjh);
                order.putExtra("bkqty", strqbk);
                order.putExtra("bcqty", strqbc);
                order.putExtra("slqty", strqsl);
                startActivity(order);
            }
        });

        bjhmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ibjhqty > 0) {
                    bjhmin.setClickable(true);
                    ibjhqty = ibjhqty - 1;
                    bjhqty.setText(String.valueOf(ibjhqty));
                } else if (ibjhqty == 0) {
                    bjhmin.setClickable(false);
                }
            }
        });

        bjhplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ibjhqty > 0 || ibjhqty == 0) {
                    if (ibjhqty <= 50) {
                        bjhplus.setClickable(true);
                        ibjhqty = ibjhqty + 1;
                        bjhqty.setText(String.valueOf(ibjhqty));
                    } else {
                        bjhplus.setClickable(false);
                        Toast.makeText(orderlaundry.this, "TELAH MELEBIHI BATS MAKSIMUM", Toast.LENGTH_LONG).show();
                    }
                } else if (ibjhqty < 0) {
                    bjhplus.setClickable(false);
                }
            }
        });

    }

    /**
     * Get current laundry data and set each corresponding textview
     */
    private void get_current_laundry(String kategori, String service) {
        ApiEndpoint apidata = ApiService.getClient().create(ApiEndpoint.class);
        Call<ResponsePricelist> tdata = apidata.pllist(namalaundry);

        tdata.enqueue(new Callback<ResponsePricelist>() {
            @Override
            public void onResponse(Call<ResponsePricelist> call, Response<ResponsePricelist> response) {
                int id_laundry = response.body().getId();
                String msg = response.body().getMsg();

                pllist = response.body().getPricelist();
                PricelistItem current_laundry = pllist.get(0);
//                hargabjh.setText(current_laundry.getBajuN());
//                hargabk.setText(current_laundry.getBonekaN());
//                hargabc.setText(current_laundry.getBedcoverE());
//                hargasl1.setText(current_laundry.getSelimutbE());
//                hargasl2.setText(current_laundry.getSelimutbN());
//                hargagr.setText(current_laundry.getGordenE());

                if (kategori.equals("Normal") && service.equals("Complete")) {
                    hargabjh.setText(current_laundry.getBajuN());
                    hargabk.setText(current_laundry.getBonekaN());
                    hargabc.setText(current_laundry.getBedcoverN());
                    hargasl1.setText(current_laundry.getSelimutbN());
                    hargasl2.setText(current_laundry.getSelimutkN());
                    hargagr.setText(current_laundry.getGordenN());
                } else if (kategori.equals("Express") && service.equals("Complete")) {
                    hargabjh.setText(current_laundry.getBajuE());
                    hargabk.setText(current_laundry.getBonekaE());
                    hargabc.setText(current_laundry.getBedcoverE());
                    hargasl1.setText(current_laundry.getSelimutbE());
                    hargasl2.setText(current_laundry.getSelimutkE());
                    hargagr.setText(current_laundry.getGordenE());
                } else if (kategori.equals("Normal") && service.equals("Setrika")) {
                    hargabjh.setText((current_laundry.getSetrikaN()));
                } else if (kategori.equals("Express") && service.equals("Setrika")) {
                    hargabjh.setText(current_laundry.getSetrikaE());
                }
            }

            @Override
            public void onFailure(Call<ResponsePricelist> call, Throwable t) {
                Toast.makeText(orderlaundry.this, "GAGAL KONEK" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Error.ResponsePricelist", t.toString());
            }
        });
    }

//    private void tp_dataa() {
//        ApiEndpoint apidata = ApiService.getClient().create(ApiEndpoint.class);
//        Call<ResponsePricelist> pllist = apidata.pllist();
//
//        pllist.enqueue(new Callback<ResponsePricelist>() {
//            @Override
//            public void onResponse(Call<ResponsePricelist> call, Response<ResponsePricelist> response) {
//                int id = response.body().getId();
//                String msg = response.body().getMsg();
//
//                List<PricelistItem> results = response.body().getPricelist();
//
//                Log.d("UVEERS", "TEEEEEEEEEST");
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponsePricelist> call, Throwable t) {
//
//            }
//        });
//    }
}