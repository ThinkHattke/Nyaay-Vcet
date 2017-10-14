package com.gaurav.nyaay_vect;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class fileRti extends AppCompatActivity {

    private EditText courtName;

    private EditText type;

    private EditText appNo;

    private String Court;

    private String CaseType;

    private String AppNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_rti);

        courtName = (EditText) findViewById(R.id.courtedit);

        type = (EditText) findViewById(R.id.typeedit);

        appNo = (EditText) findViewById(R.id.applicationno);
    }


    public void checkstatus(View view) {

        Boolean checking = check();

        if (checking) {

            Intent intent = new Intent(this, MyCases.class);
            intent.putExtra("Court", Court);
            intent.putExtra("Case Type", CaseType);
            intent.putExtra("Application Id", AppNo);
            startActivity(intent);
        }
    }

    private Boolean check() {

        set();

        if (courtName.equals(null) || TextUtils.isEmpty(Court)) {

            Toast.makeText(this, "Enter the Court Name", Toast.LENGTH_SHORT).show();
            return false;

        } else if (type.equals(null) || TextUtils.isEmpty(CaseType)) {

            Toast.makeText(this, "Enter the type of case", Toast.LENGTH_SHORT).show();
            return false;

        } else if (appNo.equals(null) || TextUtils.isEmpty(AppNo)) {

            Toast.makeText(this, "Enter a valid Application no", Toast.LENGTH_SHORT).show();
            return false;
        } else {

            return true;
        }
    }



    private void set() {

        Court = courtName.getText().toString().trim();

        CaseType = type.getText().toString().trim();

        AppNo = appNo.getText().toString().trim();


    }

    }



