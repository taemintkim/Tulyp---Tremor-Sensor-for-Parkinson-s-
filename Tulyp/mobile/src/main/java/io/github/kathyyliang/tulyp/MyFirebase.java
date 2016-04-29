package io.github.kathyyliang.tulyp;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

/**
 * This class handles all authentication and calls to this app's Firebase backend
 * Created by TK on 4/28/16.
 */
public class MyFirebase {
    private Firebase mfirebase;
    private String uid;

    public MyFirebase() {
        mfirebase = new Firebase("https://tulyp.firebaseio.com");
    }

    public void createUser(String email, String password) {
        mfirebase.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
            }
        });
    }

    /**
     * Using email and password, authenticate to Firebase.
     * @param email: String email from user input.
     * @param password: String password from user input.
     */
    public void login(String email, String password, final Context context) {
        //todo this Firebase instance will have to be moved to a global application context class so other activities can use it.
        mfirebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                uid = authData.getUid();
                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                CharSequence text = "Login Successful!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration); //not sure if this way of passing in context is correct.
                toast.show();
            }

            @Override
            public void onAuthenticationError(FirebaseError error) {
                // there was an error
                switch (error.getCode()) {
                    case FirebaseError.USER_DOES_NOT_EXIST:
                        // todo handle a non existing user. Show alert dialog? This is up to Frontend
                        new AlertDialog.Builder(context)
                                .setMessage("Woops! An account under this email does not exist.\nCheck your email address or sign up to Tulyp.")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                        break;
                    case FirebaseError.INVALID_PASSWORD:
                        // handle an invalid password
                        CharSequence text = "Incorrect password";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
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
        uid = null;
        if (isAuthenticated()) {
            mfirebase.unauth();
            return !isAuthenticated();
        }
        return true;
    }

    public String getUID() {
        return uid;
    }
}
