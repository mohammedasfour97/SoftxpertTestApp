package com.mvvm.softxperttestapp;

import android.app.Application;

public class MyApplication extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApiComponent = DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule("http://demo1585915.mockable.io/api/v1/"))
                .build();
    }

    public ApiComponent getNetComponent() {
        return mApiComponent;
    }
}
