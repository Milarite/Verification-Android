package com.test.verification.verificationapp;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;

public class HelperClass {
    public static Web3j web3 = Web3jFactory.build(new HttpService("https://rinkeby.infura.io/v3/203f4b27aa6a41c6958b6c8ff6f4d729"));// defaults to http://localhost:8545/
    public static String privateKey="4274b048585600a5732d24d055e5ca2ed6df5311b895d4ed6c1aea0019881021"
            ,mainAddress="0xf21fe27e0721423577c46cd649970a15358a9726"
            ,voterAddress="0xd1dcbeb004033102e5d3b5ba78f6cf75d0fe2590";
    public static Credentials credentials = Credentials.create(privateKey);

//    public static Voters voters;
//    public static Judgment judgment=null;
//    public static Candidates candidates=null;
//    public static MainContract mainContract=null;
}
