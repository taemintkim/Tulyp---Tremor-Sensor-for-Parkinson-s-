package io.github.kathyyliang.tulyp;

import android.app.Application;
import android.content.Context;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * We can use this to share methods and variables across activities.
 * Created by TK on 4/28/16.
 */
public class TulypApplication extends Application {
    public static MyFirebase mFirebase;
    public static User mUser;
    private static Context context;
    private static HashMap<String, Object> tremordata;
    public static ArrayList<User> patients;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        Firebase.getDefaultConfig().setPersistenceEnabled(true);
        Firebase.setAndroidContext(this);
        mFirebase = new MyFirebase();
        patients = new ArrayList<>();
    }

    public static void setTremordata(HashMap<String, Object> tremord) {
        tremordata = tremord;
    }

    public static HashMap<String, Object> getTremordata() {
        return tremordata;
    }

    public static void addTremordata(String key, Object value) {
        if (tremordata == null) {
            tremordata = new HashMap<>();
        }
        tremordata.put(key, value);
    }

    public static Context getAppContext() {
        return context;
    }
}
