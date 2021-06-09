package in.samuel.sds_kariyavilai;


import android.app.Application;

import com.onesignal.OneSignal;

public class MainApp extends Application {
    private static final String ONESIGNAL_APP_ID = "f8abe79c-a404-473e-8a8a-7d93743fac0d";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }
}