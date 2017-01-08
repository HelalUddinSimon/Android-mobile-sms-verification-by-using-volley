package com.example.solshare.test;

import android.app.Application;
import android.content.Context;

/**
 * Created by lenovo on 1/8/2017.
 */

public class MyApplication extends Application {

    private static MyApplication sInstance ;



    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getsInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
}
