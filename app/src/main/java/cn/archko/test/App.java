package cn.archko.test;

import android.app.Application;
import android.util.DisplayMetrics;

public class App extends Application {

    private final String appkey = "5c15f639f1f556978b0009c8";
    private static App mInstance = null;

    public int screenHeight = 720;
    public int screenWidth = 1080;
    public static Thread uiThread;

    public static App getInstance() {
        return mInstance;
    }

    public void onCreate() {
        super.onCreate();
        mInstance = this;
        uiThread = Thread.currentThread();

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
    }

}
