package io.github.kathyyliang.tulyp;

import android.app.Application;
import com.firebase.client.Firebase;

/**
 * We can use this to share methods and variables across activities.
 * Created by TK on 4/28/16.
 */
public class TulypApplication extends Application {
    public static MyFirebase mFirebase;
    public static User mUser;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
        Firebase.setAndroidContext(this);
        mFirebase = new MyFirebase();
    }
}
