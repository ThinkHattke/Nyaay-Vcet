package com.gaurav.nyaay_vect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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

public class policestn extends AppCompatActivity {

    ArrayList<String> police = new ArrayList<String>();
    ListView listView;

    ArrayList<String> id = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<String> judge = new ArrayList<String>();
    ArrayList<String> oppo = new ArrayList<String>();
    ArrayList<String> priority = new ArrayList<String>();
    ArrayList<String> court = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policestn);
    }



}
