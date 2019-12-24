package com.example.marvelapp.config;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;


public class FirebaseConfig {
    private FirebaseRemoteConfig mFirebaseConfig;

    public FirebaseConfig() {
        fbRemoteConfig();
        fetchConfig();
    }

    private void fbRemoteConfig() {
        mFirebaseConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
        mFirebaseConfig.setConfigSettingsAsync(configSettings);
    }

    private void fetchConfig() {
        mFirebaseConfig.fetchAndActivate().addOnSuccessListener(new OnSuccessListener<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                mFirebaseConfig.activate();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public String getKey(String key){
        return mFirebaseConfig.getString(key);
    }
}