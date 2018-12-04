package com.test.verification.verificationapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.PersistableBundle;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.camera.CameraSettings;
import com.kenai.jffi.Main;

import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

public class MainActivity extends AppCompatActivity {

    public static TextView name,age,sex,major,gpa,universityName,nationalID,dateOfBirth,placeOfBirth,switchLang,studentID;
    FloatingActionButton scan;
    HelperClass helperClass;
    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helperClass=new HelperClass(MainActivity.this);
        switchLang=(TextView)findViewById(R.id.switchLang);
        studentID=(TextView)findViewById(R.id.studentID);
        name=(TextView)findViewById(R.id.name);
        age=(TextView)findViewById(R.id.age);
        sex=(TextView)findViewById(R.id.sex);
        major=(TextView)findViewById(R.id.major);
        gpa=(TextView)findViewById(R.id.gpa);
        universityName=(TextView)findViewById(R.id.universityName);
        nationalID=(TextView)findViewById(R.id.nationalID);
        dateOfBirth=(TextView)findViewById(R.id.dateOfBirth);
        placeOfBirth=(TextView)findViewById(R.id.placeOfBirth);
        scan=(FloatingActionButton)findViewById(R.id.scan);

        if (switchLang.getText().toString().equals("English"))
        {
            name.setGravity(Gravity.LEFT);
            studentID.setGravity(Gravity.LEFT);
            age.setGravity(Gravity.LEFT);
            sex.setGravity(Gravity.LEFT);
            major.setGravity(Gravity.LEFT);
            gpa.setGravity(Gravity.LEFT);
            universityName.setGravity(Gravity.LEFT);
            nationalID.setGravity(Gravity.LEFT);
            dateOfBirth.setGravity(Gravity.LEFT);
            placeOfBirth.setGravity(Gravity.LEFT);
        }
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                studentID.setText("");
                age.setText("");
                sex.setText("");
                major.setText("");
                gpa.setText("");
                universityName.setText("");
                nationalID.setText("");
                dateOfBirth.setText("");
                placeOfBirth.setText("");
                startActivity(new Intent(MainActivity.this,QRScannerActivity.class));

            }
        });
        switchLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchLang.getText().toString().equals("English"))
                    helperClass.switchLang("en");
                else
                    helperClass.switchLang("ar");

                startActivity(new Intent(MainActivity.this,MainActivity.class));
                    finish();

            }
        });



        }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("name",name.getText().toString());
        outState.putString("studentID",studentID.getText().toString());

        outState.putString("age",age.getText().toString());
        outState.putString("sex",sex.getText().toString());
        outState.putString("major",major.getText().toString());
        outState.putString("gpa",gpa.getText().toString());
        outState.putString("universityName",universityName.getText().toString());
        outState.putString("nationalID",nationalID.getText().toString());
        outState.putString("dateOfBirth",dateOfBirth.getText().toString());
        outState.putString("placeOfBirth",placeOfBirth.getText().toString());
        outState.putString("switchLang",switchLang.getText().toString());
        super.onSaveInstanceState(outState);

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        name.setText(savedInstanceState.getString("name"));
        studentID.setText(savedInstanceState.getString("studentID"));
        age.setText(savedInstanceState.getString("age"));
        sex.setText(savedInstanceState.getString("sex"));
        major.setText(savedInstanceState.getString("major"));
        gpa.setText(savedInstanceState.getString("gpa"));
        universityName.setText(savedInstanceState.getString("universityName"));
        nationalID.setText(savedInstanceState.getString("nationalID"));
        dateOfBirth.setText(savedInstanceState.getString("dateOfBirth"));
        placeOfBirth.setText(savedInstanceState.getString("placeOfBirth"));
    }



}
