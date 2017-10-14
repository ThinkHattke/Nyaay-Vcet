package com.gaurav.nyaay_vect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SuccessScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_screen);
    }

    public void CheckStatus(View view) {

        Intent intent = new Intent(this, MainDashBoard.class);
        startActivity(intent);
    }
}
