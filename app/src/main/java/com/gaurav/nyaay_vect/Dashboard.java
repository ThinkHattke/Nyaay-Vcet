package com.gaurav.nyaay_vect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void fileFIR(View view) {

        Intent FIRIntent = new Intent(this, FIR.class);
        startActivity(FIRIntent);
    }
}
