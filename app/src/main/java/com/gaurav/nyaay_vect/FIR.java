package com.gaurav.nyaay_vect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FIR extends AppCompatActivity {

    EditText stationet,typeet,nameet,comet,sunjectet;
    String station,type,name,com,subjet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fir);

        stationet = (EditText) findViewById(R.id.policestn);
        typeet = (EditText) findViewById(R.id.type);
        nameet = (EditText) findViewById(R.id.name);
        comet = (EditText) findViewById(R.id.complaint);
        sunjectet = (EditText) findViewById(R.id.subject);

    }

    public void verifyclick(View view) {

        Boolean checking = check();

        if (checking == true) {

            Intent intent = new Intent(this, FIRVerify.class);
            intent.putExtra("stn",station);
            intent.putExtra("type",type);
            intent.putExtra("com",com);
            intent.putExtra("subject",subjet);
            intent.putExtra("name",name);
            startActivity(intent);
        }
    }

    public void set(){

        station = stationet.getText().toString().trim();
        type = typeet.getText().toString().trim();
        name = nameet.getText().toString().trim();
        com = comet.getText().toString().trim();
        subjet = sunjectet.getText().toString().trim();

    }

    public boolean check() {

        set();

        if (station.equals(null) || TextUtils.isEmpty(station)) {

            Toast.makeText(this,"Enter Police Station name to continue",Toast.LENGTH_SHORT).show();
            return false;

        } else if (subjet.equals(null) || TextUtils.isEmpty(subjet)) {

            Toast.makeText(this,"Enter Subject to continue",Toast.LENGTH_SHORT).show();
            return false;

        } else if (type.equals(null) || TextUtils.isEmpty(type)) {

            Toast.makeText(this,"Enter Type to continue",Toast.LENGTH_SHORT).show();
            return false;

        } else if (name.equals(null) || TextUtils.isEmpty(name)) {

            Toast.makeText(this,"Enter your Name to continue",Toast.LENGTH_SHORT).show();
            return false;

        } else if (com.equals(null) || TextUtils.isEmpty(com)) {

            Toast.makeText(this,"Enter Compliant to continue",Toast.LENGTH_SHORT).show();
            return false;

        } else {

            return true;

        }


    }

}
