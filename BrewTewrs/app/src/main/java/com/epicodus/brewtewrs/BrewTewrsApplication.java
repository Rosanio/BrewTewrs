package com.epicodus.brewtewrs;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Guest on 5/10/16.
 */
public class BrewTewrsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
