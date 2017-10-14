package com.gaurav.nyaay_vect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CaseSchedule extends AppCompatActivity {

    EditText catet, typeet, dateet, locet, stateet, diset;
    String cat, type, date, loc, state, dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_schedule);

        catet = (EditText) findViewById(R.id.whenedit);
        typeet = (EditText) findViewById(R.id.whereedit);
        dateet = (EditText) findViewById(R.id.date);
        locet = (EditText) findViewById(R.id.location);
        stateet = (EditText) findViewById(R.id.state);
        diset = (EditText) findViewById(R.id.district);

    }

    public void set() {

        cat = catet.getText().toString().trim();
        type = typeet.getText().toString().trim();
        date = dateet.getText().toString().trim();
        state = stateet.getText().toString().trim();
        loc = locet.getText().toString().trim();
        dis = diset.getText().toString().trim();

    }

    public boolean check() {

        set();

        if (cat.equals(null) || TextUtils.isEmpty(cat)) {

            Toast.makeText(this, "Enter Police Station name to continue", Toast.LENGTH_SHORT).show();
            return false;

        } else if (type.equals(null) || TextUtils.isEmpty(type)) {

            Toast.makeText(this, "Enter Subject to continue", Toast.LENGTH_SHORT).show();
            return false;

        } else if (date.equals(null) || TextUtils.isEmpty(date)) {

            Toast.makeText(this, "Enter Type to continue", Toast.LENGTH_SHORT).show();
            return false;

        } else if (loc.equals(null) || TextUtils.isEmpty(loc)) {

            Toast.makeText(this, "Enter your Name to continue", Toast.LENGTH_SHORT).show();
            return false;

        } else if (state.equals(null) || TextUtils.isEmpty(state)) {

            Toast.makeText(this, "Enter Compliant to continue", Toast.LENGTH_SHORT).show();
            return false;

        } else if (dis.equals(null) || TextUtils.isEmpty(dis)) {

            Toast.makeText(this, "Enter Compliant to continue", Toast.LENGTH_SHORT).show();
            return false;

        } else {

            return true;

        }

    }

    public void schedule(View view){


            RequestQueue queue = Volley.newRequestQueue(this);
            final String url = "http://www.sastimasti.me/nyaay/adddata.php?cat="+cat+"&type="+type+"&date="+date+"&loc="+loc+"&state="+state+"&place="+dis;
            String goodurl = url.replace(" ", "%20");
            StringRequest postRequest = new StringRequest(Request.Method.POST, goodurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {



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

