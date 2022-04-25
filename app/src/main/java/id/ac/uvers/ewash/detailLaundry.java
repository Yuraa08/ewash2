package id.ac.uvers.ewash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import id.ac.uvers.ewash.Adapter.orderlaundryAdapter;

public class detailLaundry extends AppCompatActivity {

    private String id_laundry,namalaundry;
    TextView detailnamal;
    ImageView info, back;
    RadioGroup jangkawaktu, service;
//    RadioButton normal, express, complete, cuciker, setrika, rbjw, rbs;
//    private String rbnormal, rbexpress, rbcomplete, rbcuciker, rbsetrika, textrbjw, textrbjs;
    private String str1, str2, strnama, str11, str22, strnama1;
    Button next;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_laundry);

        Intent intent = getIntent();
        id_laundry = intent.getStringExtra("id_laundry");
        namalaundry = intent.getStringExtra("nama");

        detailnamal = findViewById(R.id.namalaundrydetail);
        next = findViewById(R.id.btnnext);
        info = findViewById(R.id.btninfo);
        back = findViewById(R.id.btnback);
        jangkawaktu = findViewById(R.id.kategorilaundry);
//        normal = findViewById(R.id.ketNormal);
//        express = findViewById(R.id.ketExpress);

        service = findViewById(R.id.typelaundry);

//        complete = findViewById(R.id.tlcomplete);
//        cuciker = findViewById(R.id.tlcucikering);
//        setrika = findViewById(R.id.tlsetrika);

        detailnamal.setText(namalaundry);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str1 = ((RadioButton)findViewById(jangkawaktu.getCheckedRadioButtonId())).getText().toString().trim();
                str2 = ((RadioButton)findViewById(service.getCheckedRadioButtonId())).getText().toString().trim();
                str11 = ((RadioButton)findViewById(jangkawaktu.getCheckedRadioButtonId())).getText().toString().trim();
                str22 = ((RadioButton)findViewById(service.getCheckedRadioButtonId())).getText().toString().trim();
                strnama1 = detailnamal.getText().toString().trim();
                strnama = detailnamal.getText().toString().trim();
                Intent next = new Intent(getApplicationContext(),orderlaundry.class);
                Intent order = new Intent(getApplicationContext(), orderlaundryAdapter.class);
                next.putExtra("selectjw",str1);
                next.putExtra("selects",str2);
                next.putExtra("nama",strnama);
                order.putExtra("kategori",str11);
                order.putExtra("service",str22);
                order.putExtra("namalaundry",strnama1);
                startActivity(next);
                startActivity(order);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent info = new Intent(detailLaundry.this,infolaundry.class);
                startActivity(info);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(detailLaundry.this,mainMenu.class);
                startActivity(back);
            }
        });
    }

}