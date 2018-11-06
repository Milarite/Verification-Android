package com.test.verification.verificationapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.kenai.jffi.Main;

import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

public class MainActivity extends AppCompatActivity {

    TextView name,age,sex,major,gpa,universityName,nationalID,dateOfBirth,placeOfBirth;
    FloatingActionButton scan;
    String qrResultHash;
    ProgressDialog progressDialog;
    HelperClass helperClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helperClass=new HelperClass(MainActivity.this);


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
                qrResultHash=result.getContents();
                new LongOperationGetInfo().execute("");

            }
        }


    }

    class LongOperationGetInfo extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(HelperClass.verification_sol_verification ==null )
                HelperClass.verification_sol_verification = HelperClass.verification_sol_verification.load(HelperClass.verificationAddress, HelperClass.web3, HelperClass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
            progressDialog=helperClass.getProgressDialog("Please wait","Getting data");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                age.setText(HelperClass.verification_sol_verification.getAge(qrResultHash).send());
                name.setText(HelperClass.verification_sol_verification.getFirstName(qrResultHash).send() +" "+HelperClass.verification_sol_verification.getLastName(qrResultHash).send());
                sex.setText(HelperClass.verification_sol_verification.getSex(qrResultHash).send());
                major.setText(HelperClass.verification_sol_verification.getMajor(qrResultHash).send());
                gpa.setText(HelperClass.verification_sol_verification.getGPA(qrResultHash).send());
                universityName.setText(HelperClass.verification_sol_verification.getUniversityName(qrResultHash).send());
                nationalID.setText(HelperClass.verification_sol_verification.getNationalID(qrResultHash).send());
                dateOfBirth.setText(HelperClass.verification_sol_verification.getDateOfBirth(qrResultHash).send());
                placeOfBirth.setText(HelperClass.verification_sol_verification.getPlaceOfBirth(qrResultHash).send());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

        }

    }

}
