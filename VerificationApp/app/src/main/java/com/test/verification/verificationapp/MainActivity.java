package com.test.verification.verificationapp;


import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

public class MainActivity extends AppCompatActivity {

    TextView name,age,sex,major,gpa,universityName,nationalID,dateOfBirth,placeOfBirth;
    FloatingActionButton scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                age.setText("");
                sex.setText("");
                major.setText("");
                gpa.setText("");
                universityName.setText("");
                nationalID.setText("");
                dateOfBirth.setText("");
                placeOfBirth.setText("");
                openCameraQr();
            }
        });




    }

    public  void openCameraQr() {
        IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
            } else {
                Vibrator vibrator=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(100);
                Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_SHORT).show();
               // result.getContents();

            }
        }


    }

}
