package com.gaurav.nyaay_vect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FIR extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fir);
    }

    public void verifyclick(View view) {

        Intent intent = new Intent(this, FIRVerify.class);
        startActivity(intent);
    }
}
