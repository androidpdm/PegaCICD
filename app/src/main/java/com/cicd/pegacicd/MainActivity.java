package com.cicd.pegacicd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.Pega_Login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String URL="https://aravindapp.eu.ngrok.io/prweb/api/v1/casetypes";
                String URL="https://13519204.ngrok.io/prweb/api/v1/authenticate";

                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest objectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        URL,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.e("success response", response.toString());
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
                        String credentials = "ravi:rules";
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
