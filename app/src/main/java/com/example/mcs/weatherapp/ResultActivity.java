package com.example.mcs.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class ResultActivity extends AppCompatActivity {


    public static final String ZIP_VALUE = "ZipValue";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Intent intent = getIntent();
        int zipNumber = intent.getIntExtra(ZIP_VALUE, 0);


        downloadData();
    }


    public void downloadData() {


        if (!AppHelper.checkConn(this)) {
            Toast.makeText(this, "Check your Internet Conexion", Toast.LENGTH_LONG).show();
            finish();
            return;
        }


        String Url = getString(R.string.url) + "ZIP_VALUE=30030,us";//&AppID=293f015aecea8bb0e6fd9444d4420beb";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                Url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        try {

                            Log.i("Resultado!!!!!", response.toString());
                            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();


                            JSONArray jsonArray = response.getJSONArray("weather");

                            // jsonArray.get


//                            List<Embarcacion> embarcacionList = ParseModels.parseEmbarcaciones(jsonArray);
//
//                            new SaveEmbarcacionesTask(embarcacionList).execute();


                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }) {


        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1));
        VolleyRequestMgr.getInstance(this).getRequestQueue().add(jsonObjectRequest);


    }


}
