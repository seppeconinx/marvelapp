package com.example.marvelapp.api;

import com.example.marvelapp.config.FirebaseConfig;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Authentication {

    /** Same for every object, and is a const var */
    private static final String KEY_PRIVATE = "KEY_PRIVATE";
    private static final String KEY_PUBLIC = "KEY_PUBLIC";

    /** API requires the md5 of (timestamp + public key + private key) to be added at the end of an URL */
    private String timeStamp;
    private String publicKey;
    private String privateKey;
    private String md5Key;


    //private FirebaseConfig config;

    public Authentication() {
        timeStamp = String.valueOf(System.currentTimeMillis());
        //config = new FirebaseConfig();
        publicKey = "6e1818d5f229df319d2c672e89a21e67";
        privateKey = "f5304518e599e95dbfefb45349ec939c9d1966ac";
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }


    /** Credits function: github.com/ribamarsantos/android-marvel-api/ */
    public String getMd5Key() {
        String hash = null;
        String sum = getTimeStamp() + getPrivateKey() + getPublicKey();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte [] md5Bytes = md.digest(sum.getBytes());

            StringBuilder md5 = new StringBuilder();
            for (int i = 0; i < md5Bytes.length; ++i)
            {
                md5.append(Integer.toHexString((md5Bytes[i] & 0xFF) | 0x100).substring(1, 3));
            }

            md5Key = md5.toString();

        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return md5Key;
    }
}
