package com.gaurav.nyaay_vect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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

public class Search extends AppCompatActivity {

    ListView listView;
    EditText editText;

    ArrayList<String> title = new ArrayList<String>();
    ArrayList<String> link = new ArrayList<String>();
    ArrayList<String> details = new ArrayList<String>();
    String input,type,pgnum="0";
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editText = (EditText) findViewById(R.id.serach);

        Intent intent = getIntent();
        listView = (ListView) findViewById(R.id.listview);
        input = intent.getExtras().getString("input");
        type = intent.getExtras().getString("type");
        num = 0;

        if (type=="B"){

            String edit;

            edit = input.replace("Search ","");

            input = edit;

        }

        request();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                finish();
                Intent intentl = new Intent(Search.this,Webview.class);
                intentl.putExtra("url",link.get(i));
                startActivity(intentl);

            }
        });

    }

    public void reload(View view){

        String result = editText.getText().toString().trim();

        if (result.isEmpty()){

            Toast.makeText(this,"Enter a keyword to search",Toast.LENGTH_SHORT);

        } else {

            finish();
            Intent i = new Intent(Search.this, Search.class);
            i.putExtra("input", result);
            startActivity(i);

        }
    }

    public void request() {

        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://api.indiankanoon.org/search/?formInput="+input+"&pagenum="+pgnum+"&maxpage=4";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("docs");

                    for (int i=0; i <jsonArray.length(); i++){

                        JSONObject post = jsonArray.getJSONObject(i);

                        title.add(String.valueOf(Html.fromHtml(post.getString("title"))));
                        details.add(String.valueOf((Html.fromHtml(post.getString("headline")))));
                        link.add(post.getString("url"));


                    }

                    List<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();

                    for (int i=0; i<jsonObject.length(); i++){

                        HashMap<String,String> hm = new HashMap<String,String>();
                        hm.put("title",title.get(i));
                        hm.put("details",details.get(i));
                        list.add(hm);

                    }

                    String[] from ={"title","details"};
                    int[] to = {R.id.title,R.id.details};

                    SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(),list,R.layout.search_listview_layout,from,to);
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

        ) {

            public Map<String,String> getHeaders() throws AuthFailureError {

                Map<String,String> params = new HashMap<String, String>();
                params.put("Authorization","Token 3ee55dbc751245319bd3d4b58c7509fb5d99613d");

                return params;

            }

        };

        queue.add(postRequest);

    }

}
