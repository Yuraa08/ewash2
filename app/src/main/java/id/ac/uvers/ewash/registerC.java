package id.ac.uvers.ewash;

import static android.widget.Toast.LENGTH_LONG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class registerC extends AppCompatActivity {

    TextInputLayout usernameC, namaC, passwordC, nohpC, alamatC;
    Button register;
    String usernamerg, namarg, passrg, nohprg, alamatrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_c);

        usernameC = (TextInputLayout) findViewById(R.id.usernameCRg);
        namaC = (TextInputLayout) findViewById(R.id.namaCRg);
        passwordC = (TextInputLayout) findViewById(R.id.passwordCRg);
        nohpC = (TextInputLayout) findViewById(R.id.nohpCRg);
        alamatC = (TextInputLayout) findViewById(R.id.alamatCRg);
        register = (Button) findViewById(R.id.btnRegis);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernamerg = usernameC.getEditText().getText().toString().trim();
                namarg = namaC.getEditText().getText().toString().trim();
                passrg = passwordC.getEditText().getText().toString().trim();
                nohprg = nohpC.getEditText().getText().toString().trim();
                alamatrg = alamatC.getEditText().getText().toString().trim();

                if (usernamerg.equals("")) {
                    Toast.makeText(registerC.this, "Silahkan Isi Username Anda !", Toast.LENGTH_LONG).show();
                } else if (namarg.equals("")) {
                    Toast.makeText(registerC.this, "Silahkan Isi Nama Anda !", Toast.LENGTH_LONG).show();
                } else if (passrg.equals("")) {
                    Toast.makeText(registerC.this, "Silahkan Isi Password Anda !", Toast.LENGTH_LONG).show();
                } else if (nohprg.equals("")) {
                    Toast.makeText(registerC.this, "Silahkan Isi Nomor HP Anda !", Toast.LENGTH_LONG).show();
                } else if (alamatrg.equals("")) {
                    Toast.makeText(registerC.this, "Silahkan Isi Alamat Anda !", Toast.LENGTH_LONG).show();
                } else {

                    RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
                    JsonObjectRequest jar = new JsonObjectRequest(Request.Method.GET,
                            urlcrud.insertCUser(usernamerg, namarg, passrg, nohprg, alamatrg), null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int berhasil = response.getInt("sukses");
                                String pesan = response.getString("pesan");
                                Toast.makeText(registerC.this, pesan, LENGTH_LONG).show();
                                Intent i = new Intent(registerC.this, loginC.class);
                                startActivity(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Error.response", error.toString());
                            Toast.makeText(registerC.this, "Periksa Koneksi Internet Anda !", LENGTH_LONG).show();

                        }
                    });
                    rq.add(jar);
                }
            }
        });

    }
}