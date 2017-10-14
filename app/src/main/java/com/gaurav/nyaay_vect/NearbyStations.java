package com.gaurav.nyaay_vect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearbyStations extends AppCompatActivity {

    ArrayList<String> title = new ArrayList<String>();
    ArrayList<String> dis = new ArrayList<String>();
    ArrayList<String> add = new ArrayList<String>();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_stations);

        listView = (ListView) findViewById(R.id.listvieww);

        request();
    }

    public void request() {

        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://www.sastimasti.me/nyaay/police.php";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i=0; i <8; i++){

                        JSONObject post = jsonArray.getJSONObject(i);

                        title.add(post.getString("add"));
                        dis.add(post.getString("distance"));
                        add.add(post.getString("area"));

                    }

                    List<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();

                    for (int i=0; i<8; i++){

                        HashMap<String,String> hm = new HashMap<String,String>();
                        hm.put("name",title.get(i));
                        hm.put("distance","Distance: "+dis.get(i)+"ms/km");
                        hm.put("add","Address: "+add.get(i));
                        list.add(hm);

                    }

                    String[] from ={"name","distance","add"};
                    int[] to = {R.id.title,R.id.distancei,R.id.detailsi};

                    SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(),list,R.layout.policelist,from,to);
                    listView.setAdapter(simpleAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error","error"+error.toString());
                    }
                }

        );

        queue.add(postRequest);

    }


}
