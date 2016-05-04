package io.github.kathyyliang.tulyp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class SignIn extends AppCompatActivity {
    private Context context;
    private MyFirebase mfirebase = TulypApplication.mFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        context = this;
        TulypApplication.mFirebase.logout(); //make sure no one is already logged in already.

        Button signIn = (Button) findViewById(R.id.signinbutton);
        assert signIn != null;
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // currently defaults to patient view, later we have to check if doctor
                MyFirebase mfirebase = TulypApplication.mFirebase;
                EditText email = (EditText) findViewById(R.id.signinemail);
                EditText password = (EditText) findViewById(R.id.signinpassword);
                login(email.getText().toString(), password.getText().toString());
                TulypApplication.mFirebase.getFirebaseRef().addAuthStateListener(new Firebase.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(AuthData authData) {
                        MyFirebase mfirebase = TulypApplication.mFirebase;
                        if (authData != null) {
                            EditText email = (EditText) findViewById(R.id.email);
                            String uid = authData.getUid();
                            Log.d("##UID##", uid);
                            mfirebase.getFirebaseRef().child("Users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    TulypApplication.mUser = snapshot.getValue(User.class);
                                    if (TulypApplication.mUser.isDoctor()) {
                                        Intent intent = new Intent(getBaseContext(), DoctorView.class);
                                        startActivity(intent);
                                    } else {
                                        Intent intent = new Intent(getBaseContext(), PatientView.class);
                                        startActivity(intent);
                                    }
                                }
                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                    Log.d("Firebase", "Failed to retrieve User data\n" + firebaseError);
                                }
                            });
                        } else {
                            // user is not logged in
                        }
                    }
                });

            }
        });

        TextView joinNow = (TextView) findViewById(R.id.joinnowlink);
        String text = (String) joinNow.getText();
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        joinNow.setText(spannableString);

        joinNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), JoinNow.class);
                startActivity(intent);
            }
        });

        TextView forgotPassword = (TextView) findViewById(R.id.forgotpassword);
        String str = (String) forgotPassword.getText();
        SpannableString sStr = new SpannableString(str);
        sStr.setSpan(new UnderlineSpan(), 0, str.length(), 0);
        forgotPassword.setText(sStr);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText inputemail = new EditText(context);
                new AlertDialog.Builder(context)
                        .setTitle("Reset your password")
                        .setMessage("Enter your email to receive a link to reset your password.")
                        .setView(inputemail)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                resetPassword(inputemail.getText().toString());
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .show();
            }
        });

    }


    private void login(String email, String password) {
        mfirebase.getFirebaseRef().authWithPassword(email, password, new Firebase.AuthResultHandler() {
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
                        new AlertDialog.Builder(context)
                                .setMessage("Woops! An account under this email does not exist.\nCheck your email address or sign up to Tulyp.")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                        break;
                    case FirebaseError.INVALID_PASSWORD:
                        // handle an invalid password
                        new AlertDialog.Builder(context)
                                .setMessage("Invalid Password")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                        break;
                    case FirebaseError.INVALID_EMAIL:
                        new AlertDialog.Builder(context)
                                .setMessage("Invalid Email")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    default:
                        // handle other errors
                        break;
                }
            }
        });
    }

    // sends an email to the user to reset their password. The user account must exist.
    private void resetPassword(String email) {
        mfirebase.getFirebaseRef().resetPassword(email, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                CharSequence text = "Email Sent!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration); //not sure if this way of passing in context is correct.
                toast.show();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // error encountered
                Log.d("Sign in", "forgot password error: " + firebaseError);
                new AlertDialog.Builder(context)
                        .setMessage("Something went wrong. Try again.")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
}
