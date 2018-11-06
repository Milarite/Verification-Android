package com.test.verification.verificationapp;

import android.app.Activity;
import android.app.ProgressDialog;

import com.test.verification.verificationapp.Sol.Verification_sol_Verification;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;

public class HelperClass {
    Activity activity;
    ProgressDialog progressDialog;
    public static Web3j web3 = Web3jFactory.build(new HttpService("https://rinkeby.infura.io/v3/203f4b27aa6a41c6958b6c8ff6f4d729"));// defaults to http://localhost:8545/
    public static String privateKey="4274b048585600a5732d24d055e5ca2ed6df5311b895d4ed6c1aea0019881021"
            ,verificationAddress="0x07a4a97335ddce2951ecb9cc387e237c0785f3fc";
    public static Credentials credentials = Credentials.create(privateKey);
    public static Verification_sol_Verification verification_sol_verification=null;


    public HelperClass(Activity act)
    {
        activity=act;
    }
    public ProgressDialog getProgressDialog(String title,String message) {
        if(progressDialog==null)
        progressDialog=new ProgressDialog(activity);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
