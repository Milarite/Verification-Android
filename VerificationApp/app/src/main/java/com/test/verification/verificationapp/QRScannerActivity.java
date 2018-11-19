package com.test.verification.verificationapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

public class QRScannerActivity extends AppCompatActivity {
    String qrResultHash;
    ProgressDialog progressDialog;
    HelperClass helperClass;
    Intent intentSentParameters;
    IntentIntegrator integrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        intentSentParameters=new Intent(QRScannerActivity.this,MainActivity.class);
        helperClass=new HelperClass(QRScannerActivity.this);
        openCameraQr();
    }



    public  void openCameraQr() {
        integrator = new IntentIntegrator(QRScannerActivity.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt(getResources().getString(R.string.scan));
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false); // sound
        integrator.setOrientationLocked(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
           //     Toast.makeText(QRScannerActivity.this, getResources().getString(R.string.invalidQR), Toast.LENGTH_SHORT).show();
                finish();
            } else{
                Vibrator vibrator=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(100);
                qrResultHash=result.getContents();
                new LongOperationGetInfo().execute("");
            }
        }
    }

    public class LongOperationGetInfo extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if(HelperClass.verification_sol_verification ==null )
                HelperClass.verification_sol_verification = HelperClass.verification_sol_verification.load(HelperClass.verificationAddress, HelperClass.web3, HelperClass.credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
            progressDialog=helperClass.getProgressDialog(getResources().getString(R.string.pleaseWait),getResources().getString(R.string.gettingData));
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {

                MainActivity.age.setText(HelperClass.verification_sol_verification.getAge(qrResultHash).send());
                MainActivity.name.setText(HelperClass.verification_sol_verification.getFirstName(qrResultHash).send() +" "+HelperClass.verification_sol_verification.getLastName(qrResultHash).send());
                MainActivity.sex.setText(HelperClass.verification_sol_verification.getSex(qrResultHash).send());
                MainActivity.major.setText(HelperClass.verification_sol_verification.getMajor(qrResultHash).send());
                MainActivity.gpa.setText(HelperClass.verification_sol_verification.getGPA(qrResultHash).send());
                MainActivity.universityName.setText(HelperClass.verification_sol_verification.getUniversityName(qrResultHash).send());
                MainActivity.nationalID.setText(HelperClass.verification_sol_verification.getNationalID(qrResultHash).send());
                MainActivity.dateOfBirth.setText(HelperClass.verification_sol_verification.getDateOfBirth(qrResultHash).send());
                MainActivity. placeOfBirth.setText(HelperClass.verification_sol_verification.getPlaceOfBirth(qrResultHash).send());

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(progressDialog !=null && progressDialog.isShowing())
                progressDialog.dismiss();
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
