package id.ac.uvers.ewash;

import static android.widget.Toast.LENGTH_LONG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class finalorder extends AppCompatActivity {

    private String userlaundryy, strnama, strkategori, strservice, strqbjh, strqbk, strqbc, strqsl, strqsl2, strqgr;
    private String strhargabjh, strhargabk, strhargabc, strhargasl1, strhargasl2, strhargagr;
    private String id_laundrydata,user,namalaundry, tgltrans, bajuharian, boneka, bedcover, selimutb, selimutk, gorden, totaltrans, deliveryterm, alamat, kategori, service, payment;

    TextView namalaundry1, namauser, alamatuser, kategori1, service1;
    TextView qtybjh, qtybk, qtybc, qtyslb, qtyslk, qtygr;
    TextView hargabjh, hargabk, hargabc, hargaslb, hargaslk, hargagr;
    Button confirm;

    LinearLayout laybjhfinal, laybkfinal, laybcfinal, layslbfinal, layslkfinal, laygrfinal;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalorder);

        Intent order = getIntent();
        strnama = order.getStringExtra("namalaundry");
        strkategori = order.getStringExtra("kategori");
        strservice = order.getStringExtra("service");
        strqbjh = order.getStringExtra("bjhqty");
        strqbk = order.getStringExtra("bkqty");
        strqbc = order.getStringExtra("bcqty");
        strqsl = order.getStringExtra("slqty");
        strqsl2 = order.getStringExtra("slqty2");
        strqgr = order.getStringExtra("grqty");
        strhargabjh = order.getStringExtra("hargabjh");
        strhargabk = order.getStringExtra("hargabk");
        strhargabc = order.getStringExtra("hargabc");
        strhargasl1 = order.getStringExtra("hargasl1");
        strhargasl2 = order.getStringExtra("hargasl2");
        strhargagr = order.getStringExtra("hargagr");

        namalaundry1 = findViewById(R.id.namalaundryfinal);
        namauser = findViewById(R.id.namauserfinal);
        alamatuser = findViewById(R.id.alamatuserfinal);
        kategori1 = findViewById(R.id.kategorifinal);
        service1 = findViewById(R.id.servicefinal);
        confirm = findViewById(R.id.btnfinalorder);

        /*qty*/
        qtybjh = findViewById(R.id.bjhqtyfinal);
        qtybk = findViewById(R.id.bkqtyfinal);
        qtybc = findViewById(R.id.bcqtyfinal);
        qtyslb = findViewById(R.id.slbqtyfinal);
        qtyslk = findViewById(R.id.slkqtyfinal);
        qtygr = findViewById(R.id.grqtyfinal);

        /*harga*/
        hargabjh = findViewById(R.id.thbjhfinal);
        hargabk = findViewById(R.id.thbkfinal);
        hargabc = findViewById(R.id.thbcfinal);
        hargaslb = findViewById(R.id.thslbfinal);
        hargaslk = findViewById(R.id.thslkfinal);
        hargagr = findViewById(R.id.thgrfinal);

        /*layout*/
        laybjhfinal = findViewById(R.id.laybjhfinal);
        laybkfinal = findViewById(R.id.laybkfinal);
        laybcfinal = findViewById(R.id.laybcfinal);
        layslbfinal = findViewById(R.id.layslbfinal);
        layslkfinal = findViewById(R.id.layslkfinal);
        laygrfinal = findViewById(R.id.laygrfinal);

        /*set*/
        namalaundry1.setText(strnama);
//        namauser.setText();
//        alamatuser.setText();
        kategori1.setText(strkategori);
        service1.setText(strservice);

        /*set qty*/
        qtybjh.setText(strqbjh);
        qtybk.setText(strqbk);
        qtybc.setText(strqbc);
        qtyslb.setText(strqsl);
        qtyslk.setText(strqsl2);
        qtygr.setText(strqgr);

        /*set harga*/
        hargabjh.setText(strhargabjh);
        hargabk.setText(strhargabk);
        hargabc.setText(strhargabc);
        hargaslb.setText(strhargasl1);
        hargaslk.setText(strhargasl2);
        hargagr.setText(strhargagr);

        getuserdata ();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest jar = new JsonObjectRequest(Request.Method.GET,
                        urlcrud.insertFinalorder(id_laundrydata, user,namalaundry, tgltrans, bajuharian, boneka, bedcover, selimutb, selimutk, gorden,totaltrans, deliveryterm, alamat, kategori, service, payment), null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int berhasil = response.getInt("sukses");
                            String pesan = response.getString("pesan");
                            Toast.makeText(finalorder.this, pesan, LENGTH_LONG).show();
                            Intent i = new Intent(finalorder.this, mainMenu.class);
                            i.putExtra("namalaundry",namalaundry);
                            startActivity(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error.response", error.toString());
                        Toast.makeText(finalorder.this, "Periksa Koneksi Internet Anda !", LENGTH_LONG).show();

                    }
                });
                rq.add(jar);
            }
        });

    }

    private void getuserdata() {

    }
}