package com.gaurav.nyaay_vect;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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

public class fileRti extends AppCompatActivity {

    ListView listView;

    ArrayList<String> id = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<String> place = new ArrayList<String>();
    ArrayList<String> cat = new ArrayList<String>();
    ArrayList<String> priority = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_rti);

        listView = (ListView) findViewById(R.id.scheledulecases);

        request();

    }

    public void request() {

        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://www.sastimasti.me/nyaay/schedule.php";
        StringRequest getRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i=0; i<jsonArray.length(); i++){

                        JSONObject post = jsonArray.getJSONObject(i);

                        id.add(post.getString("Application number"));
                        priority.add(post.getString("Priority"));
                        cat.add(post.getString("cat"));
                        date.add(post.getString("Date"));
                        type.add(post.getString("type"));
                        place.add(post.getString("Place"));

                    }

                    List<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();

                    for (int i=0; i<jsonArray.length(); i++){

                        HashMap<String,String> hm = new HashMap<String,String>();
                        hm.put("Application number","Application Id Number: "+id.get(i));
                        hm.put("Priority","Priority: "+priority.get(i));
                        hm.put("cat","Category: "+cat.get(i));
                        hm.put("Date","Date: "+date.get(i));
                        hm.put("type","Type: "+type.get(i));
                        hm.put("Place","Place: "+place.get(i));
                        list.add(hm);

                    }

                    String[] from ={"Application number","Priority","cat","Date","type","Place"};
                    int[] to = {R.id.id,R.id.priority,R.id.judge,R.id.date,R.id.court,R.id.oppo};

                    SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(),list,R.layout.mycases_element_layout,from,to);
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

        queue.add(getRequest);

    }


}



