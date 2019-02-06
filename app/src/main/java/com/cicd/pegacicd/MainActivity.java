package com.cicd.pegacicd;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText UserId_Pega;
    EditText UserPwd_Pega;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pega_UserId = (EditText) findViewById
        UserId_Pega = (EditText) findViewById(R.id.UserId_Pega);
        UserPwd_Pega = (EditText) findViewById(R.id.UserPwd_Pega);

        Button button = findViewById(R.id.Pega_Login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String URL="http://fcb005d4.ngrok.io/prweb/PRRestService/cicd/v1/pipelines";
                //String URL="https://samples.openweathermap.org/data/2.5/weather?zip=94040,us&appid=b6907d289e10d714a6e88b30761fae22";
                final String UserName = UserId_Pega.getText().toString();
                final String Password = UserPwd_Pega.getText().toString();
                Log.e("Username entered ",UserName);
                Log.e("Password entered",Password);

                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest objectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        URL,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.e("success response", response.toString());
                                Intent intent = new Intent(MainActivity.this,LoginSuccess.class);
                                intent.putExtra("Login_UName",UserName);
                                intent.putExtra("Logn_UPwd",Password);


                                startActivity(intent);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("error response", error.toString());
                            }
                        }
                )
                {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        String credentials = UserName+":"+Password;
                        //String credentials = "ravi:rules";
                        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                        headers.put("Content-Type", "application/json");
                        headers.put("Authorization",auth);
                        return headers;
                    };
                };
//**********************************************************
                requestQueue.add(objectRequest);
            }
        });
    }
}
