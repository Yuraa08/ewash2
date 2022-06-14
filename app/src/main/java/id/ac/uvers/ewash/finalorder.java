package id.ac.uvers.ewash;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class finalorder extends AppCompatActivity {

    private String userlaundryy, strnama, strkategori, strservice, strqbjh, strqbk, strqbc, strqsl, strqsl2, strqgr;
    private String strhargabjh, strhargabk, strhargabc, strhargasl1, strhargasl2, strhargagr;

    TextView namalaundry, namauser, alamatuser, kategori, service;
    TextView qtybjh, qtybk, qtybc, qtyslb, qtyslk, qtygr;
    TextView hargabjh, hargabk, hargabc, hargaslb, hargaslk, hargagr;

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

        namalaundry = findViewById(R.id.namalaundryfinal);
        namauser = findViewById(R.id.namauserfinal);
        alamatuser = findViewById(R.id.alamatuserfinal);
        kategori = findViewById(R.id.kategorifinal);
        service = findViewById(R.id.servicefinal);

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
        namalaundry.setText(strnama);
//        namauser.setText();
//        alamatuser.setText();
        kategori.setText(strkategori);
        service.setText(strservice);

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

    }
}