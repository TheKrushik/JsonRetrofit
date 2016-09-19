package info.krushik.android.jsonretrofit;

import android.app.Application;

import info.krushik.android.jsonretrofit.retrofit.RetrofitSingleton;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitSingleton.init();
    }
}