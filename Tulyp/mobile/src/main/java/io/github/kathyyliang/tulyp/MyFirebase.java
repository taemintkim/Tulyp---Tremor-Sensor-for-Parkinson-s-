package io.github.kathyyliang.tulyp;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * This class handles all authentication and calls to this app's Firebase backend
 * Created by TK on 4/28/16.
 */
public class MyFirebase {
    private Firebase mfirebase;
//    private String uid;
    private String email;
    public MyFirebase() {
        mfirebase = new Firebase("https://tulyp.firebaseio.com");
    }

    public void createUser(final String email, final String password) {
        mfirebase.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.d("Firebase", "Create User successful!");
                TulypApplication.mUser = new User(email);
                login(email, password);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
                Log.d("Firebase", "Create User unsuccessful. >:(\n" + firebaseError);
                Intent intent = new Intent(TulypApplication.getAppContext(), JoinNow.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                TulypApplication.getAppContext().startActivity(intent);
            }
        });
    }

    public Firebase getFirebaseRef() {
        return mfirebase;
    }

    /**
     * Using email and password, authenticate to Firebase. If login is successful, fetch user data.
     *
     * @param email:    String email from user input.
     * @param password: String password from user input.
     */
    public void login(String email, String password) {
        mfirebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                String uid = authData.getUid();
                Log.d("Firebase", "Login successful! " + uid);
//                mfirebase.child("Users").child(uid).keepSynced(true); //keep local data synced even when offline.
//                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
//                CharSequence text = "Login Successful!";
//                int duration = Toast.LENGTH_SHORT;
//                Toast toast = Toast.makeText(context, text, duration); //not sure if this way of passing in context is correct.
//                toast.show();
            }

            @Override
            public void onAuthenticationError(FirebaseError error) {
                // there was an error
                switch (error.getCode()) {
                    case FirebaseError.USER_DOES_NOT_EXIST:
                        // todo handle a non existing user. Show alert dialog? This is up to Frontend
//                        new AlertDialog.Builder(SignIn.class)
//                                .setMessage("Woops! An account under this email does not exist.\nCheck your email address or sign up to Tulyp.")
//                                .setIcon(android.R.drawable.ic_dialog_alert)
//                                .show();
//                        break;
                    case FirebaseError.INVALID_PASSWORD:
                        // handle an invalid password
                        CharSequence text = "Incorrect password";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(TulypApplication.getAppContext(), text, duration);
                        toast.show();
                        break;
                    default:
                        // handle other errors
                        break;
                }
            }
        });
    }

    /**
     * Monitors for change in authentication.
     */
    public void authListener() {
        mfirebase.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    // user is logged in
                    fetchUserData();
                } else {
                    // user is not logged in
                    //TODO: kick off user and go back to sign in activity
                }
            }
        });
    }

    public boolean isAuthenticated() {
        AuthData authData = mfirebase.getAuth();
        if (authData != null) {
            // user authenticated
            return true;
        } else {
            // no user authenticated
            return false;
        }
    }

    /**
     * @return true if successfully unauthenticated from Firebase
     */
    public boolean logout() {
        if (isAuthenticated()) {
            mfirebase.unauth();
            return !isAuthenticated();
        }
        return true;
    }

    public String getUID() {
        if (isAuthenticated()) {
            return mfirebase.getAuth().getUid();
        } else {
            return null;
        }
    }


    /**
     * Stores user data to Firebase.
     * Warning: This will overwrite and potentially delete data!
     * @param user: a User class with user information
     */
    public void setNewUserInfo(User user) {
        if (user == null) {
            Log.d("Firebase", "Trying to set a null user");
            return;
        }
        AuthData authData = mfirebase.getAuth();
        if (authData != null) {
            Firebase userRef = mfirebase.child("Users").child(authData.getUid());
            userRef.setValue(user);
        } else {
            Log.d("Firebase", "Not authenticated");
        }
    }

    /**
     * Adds info to current authenticated User's database.
     *
     * @param info a Map with each entry containing information title as key and user specific information as value.
     *             You can put multiple entries in the map.
     *             ex. "name" as key; "McDonald" as value.
     */
    public void addUserInfo(HashMap<String, Object> info) {

        Firebase userRef = mfirebase.child("Users").child(getUID());
        userRef.updateChildren(info);
    }

    /**
     * fetches the currently authenticated user's profile data.
     */
    public void fetchUserData() {
        mfirebase.child("Users").child(getUID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                TulypApplication.mUser = snapshot.getValue(User.class);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("Firebase", "Failed to retrieve User data\n" + firebaseError);
            }
        });
    }

    /**
     * Save sensor data to Firebase.
     * @param data feel free to change this fit your need.
     */
    public void pushSensorData(Map<String, Object> data) {
        if (data == null) {
            Log.d("Firebase", "Trying to pass a null object");
            return;
        }
        AuthData authData = mfirebase.getAuth();
        if (authData != null) {
            Firebase userRef = mfirebase.child("SensorData").child(authData.getUid());
            userRef.updateChildren(data);
        } else {
            Log.d("Firebase", "Not authenticated");
        }
    }


    /**
     * @param tremordata
     * @return Map with hour as key, averaged tremor data for the hour as value.
     */
    public static double[] makeDayDataPoints(Map<String, Object> tremordata) {
        long curTime = System.currentTimeMillis();
        Calendar curDate = Calendar.getInstance();
        curDate.setTimeInMillis(curTime);
        Locale locale = Locale.getDefault();
        Integer hourint = curDate.get(Calendar.HOUR_OF_DAY) - 3;
        curDate.add(Calendar.HOUR, -1);

        String[] keys = tremordata.keySet().toArray(new String[tremordata.size()]);
        HashMap<Integer, ArrayList<Double>> hourlyData = new HashMap<>(); //hour of day : averaged tremor speed
        ArrayList<Long> timestamps = new ArrayList<>();
        Calendar keyCal = Calendar.getInstance();
        for (String key : keys) {
            long keytime = Long.parseLong(key);
            timestamps.add(keytime);
        }
        Collections.sort(timestamps, Collections.reverseOrder());
        for (Long stamp: timestamps) {
            keyCal.setTimeInMillis(stamp);
            if (keyCal.after(curDate)) {
                HashMap<String, String> curdata = (HashMap<String, String>) tremordata.get(String.valueOf(stamp));
                Double curdouble = Double.parseDouble(curdata.get("speed"));
                if (hourlyData.containsKey(hourint)) {
                    hourlyData.get(hourint).add(curdouble);
                } else {
                    ArrayList<Double> ad = new ArrayList<Double>();
                    ad.add(curdouble);
                    hourlyData.put(hourint, ad);
                }
            } else {
                curDate.add(Calendar.HOUR, -1);
                hourint -= 1;
            }
            if (hourint < 0) {
                break;
            }
        }
        double[] result = new double[24];
        Integer[] hkeys = hourlyData.keySet().toArray(new Integer[hourlyData.size()]);
        for (Integer key : hkeys) {
            ArrayList<Double> lst = hourlyData.get(key);
            Double sum = 0.0;
            for (Double x : lst) {
                sum += x;
            }
            result[key] = sum/lst.size();
        }
        return result;
    }
}
