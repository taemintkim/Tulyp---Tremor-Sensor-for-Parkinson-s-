package io.github.kathyyliang.tulyp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class JoinNow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_now);

        Button joinNow = (Button) findViewById(R.id.joinnowbutton);
        assert joinNow != null;
        joinNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFirebase mfirebase = TulypApplication.mFirebase;
                EditText email = (EditText) findViewById(R.id.joinnowemail);
                EditText password = (EditText) findViewById(R.id.joinnowpassword);
                mfirebase.createUser(email.getText().toString(), password.getText().toString());

                TulypApplication.mFirebase.getFirebaseRef().addAuthStateListener(new Firebase.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(AuthData authData) {
                        MyFirebase mfirebase = TulypApplication.mFirebase;
                        if (authData != null) {
                            EditText email = (EditText) findViewById(R.id.joinnowemail);
                            String uid = authData.getUid();
                            mfirebase.setUID(uid);
                            TulypApplication.mFirebase.setNewUserInfo(new User(email.getText().toString()));
                            Intent intent = new Intent(getBaseContext(), DoctorQuestion.class);
                            startActivity(intent);
                        } else {
                            // user is not logged in
                            //TODO: kick off user and go back to register activity
                        }
                    }
                });
            }
        });

        TextView signIn = (TextView) findViewById(R.id.signinlink);

    }


}
