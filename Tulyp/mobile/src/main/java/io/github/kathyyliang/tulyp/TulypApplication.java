package io.github.kathyyliang.tulyp;

import android.app.Application;
import android.content.Context;

import com.firebase.client.Firebase;

/**
 * We can use this to share methods and variables across activities.
 * Created by TK on 4/28/16.
 */
public class TulypApplication extends Application {
    public static MyFirebase mFirebase;
    public static User mUser;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        Firebase.getDefaultConfig().setPersistenceEnabled(true);
        Firebase.setAndroidContext(this);
        mFirebase = new MyFirebase();
    }

    public static Context getAppContext() {
        return context;
    }
}
