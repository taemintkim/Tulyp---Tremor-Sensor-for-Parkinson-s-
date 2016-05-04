package io.github.kathyyliang.tulyp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

//todo: There are a lot of buttons that do nothing!
public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

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
                mfirebase.login(email.getText().toString(), password.getText().toString());
                TulypApplication.mFirebase.getFirebaseRef().addAuthStateListener(new Firebase.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(AuthData authData) {
                        MyFirebase mfirebase = TulypApplication.mFirebase;
                        if (authData != null) {
                            EditText email = (EditText) findViewById(R.id.joinnowemail);
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
                            //TODO: kick off user and go back to register activity
                        }
                    }
                });

            }
        });
    }
}
