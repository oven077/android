package ua.com.service.kubik.it.retrofitexample.metadata;

import android.app.Application;

public class App extends Application {
    public static App instance;
    private static final String CONSTANT_BASE_URL = "http://192.168.1.196";
    public static  String CONSTANT_ID_PARTNER = "";
    private static final String CONSTANT_SECOND_URL = "/InfoBase4/hs/ex";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }


    public static String getBaseURL() {
        return CONSTANT_BASE_URL;
    }

    // временно, надо придумать, как возвращать полный адрес для подключения к серверу
    public static String getDefaultSecondURL() {
        return CONSTANT_SECOND_URL;
}




}
