package com.example.busticketing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class rute extends AppCompatActivity {
    ArrayList<objectRute> ruteModels;
//    private item_list adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rute);
    }

    protected void listRute(String pasDepature, String pasArrival, String pasDate){
        String url = "https://overcautious-commis.000webhostapp.com/list.php";
//        String url = "https://10.0.2.2:80/rpl/list.php";
        ruteModels= new ArrayList<>();

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respObj = new JSONObject(response);
                    Log.d("rute.java", "onResponse: "+response.length());
                    for (int i = 0; i < response.length(); i++){
                        String depature = respObj.getString("depature");
                        String arrival = respObj.getString("arrival");
                        String time_depature = respObj.getString("time_depature");
                        String price = respObj.getString("price");
//                        ruteModels.add(depature, arrival, time_depature, price);
                    }
                }
                catch (JSONException e){
                    e.printStackTrace();
                    Log.d("rute.java", "onResponse: "+e);
                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Volley Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("depature", pasDepature);
                params.put("arrival", pasArrival);
                params.put("date", pasDate);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(MyStringRequest);
    }
}