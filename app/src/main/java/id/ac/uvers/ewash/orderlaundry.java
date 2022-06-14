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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
    private SwipeRefreshLayout refreshdata;

    ImageView back, info, bjhmin, bjhplus, bkmin, bkplus, bcmin, bcplus, slmin, slplus, slmin2, slplus2, grmin, grplus;
    Button order;
    EditText bjhqty, bkqty, bcqty, slqty, slqty2, grqty;
    ScrollView scorderl;

    private String userlaundryy, strnama, strkategori, strservice, strqbjh, strqbk, strqbc, strqsl, strqsl2, strqgr;
    private String strhargabjh, strhargabk, strhargabc, strhargasl1, strhargasl2, strhargagr;
    private TextView hargabjh, hargabk, hargabc, hargasl1, hargasl2, hargagr;
    private TextView bjhharga, bkharga, bcharga, slbharga, slkharga, grharga;
    int bjhtharga,bktharga,bctharga,slbtharga,slktharga,grtharga;

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
        refreshdata = findViewById(R.id.swprf);

        /*Qty Text*/
        bjhqty = findViewById(R.id.bjhqty);
        bkqty = findViewById(R.id.bkqty);
        bcqty = findViewById(R.id.bcqty);
        slqty = findViewById(R.id.slqty1);
        slqty2 = findViewById(R.id.slqty2);
        grqty = findViewById(R.id.grqty);

        /*Set Qty*/
        bjhqty.setText(String.valueOf(ibjhqty));
        bkqty.setText(String.valueOf(ibkqty));
        bcqty.setText(String.valueOf(ibcqty));
        slqty.setText(String.valueOf(islqty));
        slqty2.setText(String.valueOf(islqty2));
        grqty.setText(String.valueOf(igrqty));

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

        /*Text View only for simpan harga sementara*/
        bjhharga = findViewById(R.id.bjhharga);
        bkharga = findViewById(R.id.bkharga);
        bcharga = findViewById(R.id.bcharga);
        slbharga = findViewById(R.id.slbharga);
        slkharga = findViewById(R.id.slkharga);
        grharga = findViewById(R.id.grharga);

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

        refreshdata.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshdata.setRefreshing(true);
                get_current_laundry(kategori, service);
                refreshdata.setRefreshing(false);
            }
        });

//        Log.d("UVEEEEEEEEEERS", service);

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
                strqbjh = bjhqty.getText().toString().trim();
                strqbk = bkqty.getText().toString().trim();
                strqbc = bcqty.getText().toString().trim();
                strqsl = slqty.getText().toString().trim();
                strqsl2 = slqty2.getText().toString().trim();
                strqgr = grqty.getText().toString().trim();
                strhargabjh = hargabjh.getText().toString().trim();
                strhargabk = hargabk.getText().toString().trim();
                strhargabc = hargabc.getText().toString().trim();
                strhargasl1 = hargasl1.getText().toString().trim();
                strhargasl2 = hargasl2.getText().toString().trim();
                strhargagr = hargagr.getText().toString().trim();
                Intent order = new Intent(getApplicationContext(), finalorder.class);
                order.putExtra("namalaundry", strnama);
                order.putExtra("kategori", strkategori);
                order.putExtra("service", strservice);
                order.putExtra("bjhqty", strqbjh);
                order.putExtra("bkqty", strqbk);
                order.putExtra("bcqty", strqbc);
                order.putExtra("slqty", strqsl);
                order.putExtra("slqty2", strqsl2);
                order.putExtra("grqty", strqgr);
                order.putExtra("hargabjh", strhargabjh);
                order.putExtra("hargabk", strhargabk);
                order.putExtra("hargabc", strhargabc);
                order.putExtra("hargasl1", strhargasl1);
                order.putExtra("hargasl2", strhargasl2);
                order.putExtra("hargagr", strhargagr);
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
                    if (ibjhqty <= 30) {
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

        bkmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ibkqty > 0) {
                    bkmin.setClickable(true);
                    ibkqty = ibkqty - 1;
                    bkqty.setText(String.valueOf(ibkqty));
                } else if (ibkqty == 0) {
                    bkmin.setClickable(false);
                }
            }
        });

        bkplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ibkqty > 0 || ibkqty == 0) {
                    if (ibkqty <= 10) {
                        bkplus.setClickable(true);
                        ibkqty = ibkqty + 1;
                        bkqty.setText(String.valueOf(ibkqty));
                    } else {
                        bkplus.setClickable(false);
                        Toast.makeText(orderlaundry.this, "TELAH MELEBIHI BATS MAKSIMUM", Toast.LENGTH_LONG).show();
                    }
                } else if (ibkqty < 0) {
                    bkplus.setClickable(false);
                }
            }
        });

        bcmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ibcqty > 0) {
                    bcmin.setClickable(true);
                    ibcqty = ibcqty - 1;
                    bcqty.setText(String.valueOf(ibcqty));
                } else if (ibcqty == 0) {
                    bcmin.setClickable(false);
                }
            }
        });

        bcplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ibcqty > 0 || ibcqty == 0) {
                    if (ibcqty <= 10) {
                        bcplus.setClickable(true);
                        ibcqty = ibcqty + 1;
                        bcqty.setText(String.valueOf(ibcqty));
                    } else {
                        bcplus.setClickable(false);
                        Toast.makeText(orderlaundry.this, "TELAH MELEBIHI BATS MAKSIMUM", Toast.LENGTH_LONG).show();
                    }
                } else if (ibcqty < 0) {
                    bcplus.setClickable(false);
                }
            }
        });

        slmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (islqty > 0) {
                    slmin.setClickable(true);
                    islqty = islqty - 1;
                    slqty.setText(String.valueOf(islqty));
                } else if (islqty == 0) {
                    slmin.setClickable(false);
                }
            }
        });

        slplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (islqty > 0 || islqty == 0) {
                    if (islqty <= 10) {
                        slplus.setClickable(true);
                        islqty = islqty + 1;
                        slqty.setText(String.valueOf(islqty));
                    } else {
                        slplus.setClickable(false);
                        Toast.makeText(orderlaundry.this, "TELAH MELEBIHI BATS MAKSIMUM", Toast.LENGTH_LONG).show();
                    }
                } else if (islqty < 0) {
                    slplus.setClickable(false);
                }
            }
        });

        slmin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (islqty2 > 0) {
                    slmin2.setClickable(true);
                    islqty2 = islqty2 - 1;
                    slqty2.setText(String.valueOf(islqty2));
                } else if (islqty2 == 0) {
                    slmin2.setClickable(false);
                }
            }
        });

        slplus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (islqty2 > 0 || islqty2 == 0) {
                    if (islqty2 <= 10) {
                        slplus2.setClickable(true);
                        islqty2 = islqty2 + 1;
                        slqty2.setText(String.valueOf(islqty2));
                    } else {
                        slplus2.setClickable(false);
                        Toast.makeText(orderlaundry.this, "TELAH MELEBIHI BATS MAKSIMUM", Toast.LENGTH_LONG).show();
                    }
                } else if (islqty2 < 0) {
                    slplus2.setClickable(false);
                }
            }
        });

        grmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (igrqty > 0) {
                    grmin.setClickable(true);
                    igrqty = igrqty - 1;
                    grqty.setText(String.valueOf(igrqty));
                } else if (igrqty == 0) {
                    grmin.setClickable(false);
                }
            }
        });

        grplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (igrqty > 0 || igrqty == 0) {
                    if (igrqty <= 50) {
                        grplus.setClickable(true);
                        igrqty = igrqty + 1;
                        grqty.setText(String.valueOf(igrqty));
                    } else {
                        grplus.setClickable(false);
                        Toast.makeText(orderlaundry.this, "TELAH MELEBIHI BATS MAKSIMUM", Toast.LENGTH_LONG).show();
                    }
                } else if (igrqty < 0) {
                    grplus.setClickable(false);
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
                    bjhharga.setText(current_laundry.getBajuN());
                    bkharga.setText(current_laundry.getBonekaN());
                    bcharga.setText(current_laundry.getBedcoverN());
                    slbharga.setText(current_laundry.getSelimutbN());
                    slkharga.setText(current_laundry.getSelimutkN());
                    grharga.setText(current_laundry.getGordenN());

                    String qtybjh = bjhqty.getText().toString();
                    String qtybk = bkqty.getText().toString();
                    String qtybc = bcqty.getText().toString();
                    String qtyslb =slqty.getText().toString();
                    String qtyslk =slqty2.getText().toString();
                    String qtygr = grqty.getText().toString();

                    String bjhharga1 = bjhharga.getText().toString().trim();
                    String bkharga1 = bkharga.getText().toString().trim();
                    String bcharga1 = bcharga.getText().toString().trim();
                    String slbharga1 =slbharga.getText().toString().trim();
                    String slkharga1 =slkharga.getText().toString().trim();
                    String grharga1 = grharga.getText().toString().trim();

                    /* total harga */
                    bjhtharga = Integer.parseInt(qtybjh)*Integer.parseInt(bjhharga1);
                    bktharga = Integer.parseInt(qtybk)*Integer.parseInt(bkharga1);
                    bctharga = Integer.parseInt(qtybc)*Integer.parseInt(bcharga1);
                    slbtharga =Integer.parseInt(qtyslb)*Integer.parseInt(slbharga1);
                    slktharga =Integer.parseInt(qtyslk)*Integer.parseInt(slkharga1);
                    grtharga = Integer.parseInt(qtygr)*Integer.parseInt(grharga1);

                    hargabjh.setText(String.valueOf(bjhtharga));
                    hargabk.setText(String.valueOf(bktharga));
                    hargabc.setText(String.valueOf(bctharga));
                    hargasl1.setText(String.valueOf(slbtharga));
                    hargasl2.setText(String.valueOf(slktharga));
                    hargagr.setText(String.valueOf(grtharga));

                } else if (kategori.equals("Express") && service.equals("Complete")) {
                    bjhharga.setText(current_laundry.getBajuE());
                    bkharga.setText(current_laundry.getBonekaE());
                    bcharga.setText(current_laundry.getBedcoverE());
                    slbharga.setText(current_laundry.getSelimutbE());
                    slkharga.setText(current_laundry.getSelimutkE());
                    grharga.setText(current_laundry.getGordenE());

                    String qtybjh = bjhqty.getText().toString();
                    String qtybk = bkqty.getText().toString();
                    String qtybc = bcqty.getText().toString();
                    String qtyslb =slqty.getText().toString();
                    String qtyslk =slqty2.getText().toString();
                    String qtygr = grqty.getText().toString();

                    String bjhharga1 = bjhharga.getText().toString().trim();
                    String bkharga1 = bkharga.getText().toString().trim();
                    String bcharga1 = bcharga.getText().toString().trim();
                    String slbharga1 =slbharga.getText().toString().trim();
                    String slkharga1 =slkharga.getText().toString().trim();
                    String grharga1 = grharga.getText().toString().trim();

                    /* total harga */
                    bjhtharga = Integer.parseInt(qtybjh)*Integer.parseInt(bjhharga1);
                    bktharga = Integer.parseInt(qtybk)*Integer.parseInt(bkharga1);
                    bctharga = Integer.parseInt(qtybc)*Integer.parseInt(bcharga1);
                    slbtharga =Integer.parseInt(qtyslb)*Integer.parseInt(slbharga1);
                    slktharga =Integer.parseInt(qtyslk)*Integer.parseInt(slkharga1);
                    grtharga = Integer.parseInt(qtygr)*Integer.parseInt(grharga1);

                    hargabjh.setText(String.valueOf(bjhtharga));
                    hargabk.setText(String.valueOf(bktharga));
                    hargabc.setText(String.valueOf(bctharga));
                    hargasl1.setText(String.valueOf(slbtharga));
                    hargasl2.setText(String.valueOf(slktharga));
                    hargagr.setText(String.valueOf(grtharga));
                } else if (kategori.equals("Normal") && service.equals("Setrika")) {
                    bjhharga.setText((current_laundry.getSetrikaN()));

                    String qtybjh = bjhqty.getText().toString();

                    String bjhharga1 = bjhharga.getText().toString().trim();

                    /* total harga */
                    bjhtharga = Integer.parseInt(qtybjh)*Integer.parseInt(bjhharga1);

                    hargabjh.setText(String.valueOf(bjhtharga));

                } else if (kategori.equals("Express") && service.equals("Setrika")) {
                    bjhharga.setText(current_laundry.getSetrikaE());

                    String qtybjh = bjhqty.getText().toString();

                    String bjhharga1 = bjhharga.getText().toString().trim();

                    /* total harga */
                    bjhtharga = Integer.parseInt(qtybjh)*Integer.parseInt(bjhharga1);

                    hargabjh.setText(String.valueOf(bjhtharga));
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