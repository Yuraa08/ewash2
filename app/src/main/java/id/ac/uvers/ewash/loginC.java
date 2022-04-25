package id.ac.uvers.ewash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class loginC extends AppCompatActivity {

    Button bLoginC;
    TextInputLayout userC, passC;
    TextView newAcctC;
    String userlgn, passlgn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_c);

        bLoginC = (Button) findViewById(R.id.btnLoginC);
        userC = (TextInputLayout) findViewById(R.id.usernameC);
        passC = (TextInputLayout) findViewById(R.id.passwordC);
        newAcctC = (TextView) findViewById(R.id.btnNewActC);

        bLoginC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userlgn = userC.getEditText().getText().toString().trim();
                passlgn = passC.getEditText().getText().toString().trim();

                if (userlgn.equals("")){
                    Toast.makeText(loginC.this, "Silahkan Isi Username Anda !", Toast.LENGTH_LONG).show();
                }
                else if (passlgn.equals("")){
                    Toast.makeText(loginC.this, "Silahkan Isi Password Anda !", Toast.LENGTH_LONG).show();
                }
                else{
                    RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
                    JsonObjectRequest jor =new JsonObjectRequest(Request.Method.GET,
                            urlcrud.selectCUser(userlgn, passlgn), null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int berhasil = response.getInt("sukses");
                                if (berhasil == 1) {
                                    JSONArray ja = response.getJSONArray("data");
                                    for (int i = 0; i < ja.length(); i++) {
                                        JSONObject jo = ja.getJSONObject(i);
                                        String username = jo.getString("username");
                                        String password = jo.getString("password");

                                        Intent ii = new Intent(loginC.this, mainMenu.class);
                                        startActivity(ii);
                                    }
                                } else {
                                    String pesan = response.getString("pesan");
                                    Toast.makeText(loginC.this, pesan, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException jsonException) {
                                jsonException.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Error.ResponsePricelist", error.toString());
                            Toast.makeText(loginC.this, "Periksa Koneksi Internet !", Toast.LENGTH_LONG).show();
                        }
                    });
                    rq.add(jor);
                }
            }
        });
        newAcctC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iii = new Intent(loginC.this,registerC.class);
                startActivity(iii);
                finish();
            }
        });
    }
}