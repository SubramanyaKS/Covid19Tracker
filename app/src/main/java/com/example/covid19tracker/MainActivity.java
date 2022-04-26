package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tvCases, tvRecovered, tvCritical, tvActive, tvTodayCases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCases = findViewById(R.id.tvcases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        fetchdata();

    }
    private void fetchdata(){
        // Create a String request using Volley Library

        String url = "https://corona.lmao.ninja/v2/all";

        StringRequest request
                = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        try {
                            // Creating object of JSONObject
                            JSONObject jsonObject
                                    = new JSONObject(
                                    response.toString());


                            tvCases.setText(
                                    jsonObject.getString("cases"));
                            tvRecovered.setText(
                                    jsonObject.getString("recovered"));
                            tvCritical.setText(
                                    jsonObject.getString("critical"));
                            tvActive.setText(
                                    jsonObject.getString("active"));
                            tvTodayCases.setText(
                                    jsonObject.getString("todayCases"));

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(
                            VolleyError error)
                    {
                        Toast.makeText(MainActivity.this,
                                error.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        RequestQueue requestQueue
                = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
    
}