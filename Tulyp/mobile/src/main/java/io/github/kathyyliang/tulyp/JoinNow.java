package io.github.kathyyliang.tulyp;

import android.app.Activity;
import android.app.ProgressDialog;
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

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

//todo: what if email and password are empty?
public class JoinNow extends AppCompatActivity {
    private int counter = 0; //to make sure this activity only triggers next activity once.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_now);

        TulypApplication.mFirebase.logout(); //make sure no one is already logged in already.

        Button joinNow = (Button) findViewById(R.id.joinnowbutton);
        assert joinNow != null;
        joinNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                MyFirebase mfirebase = TulypApplication.mFirebase;
                EditText email = (EditText) findViewById(R.id.joinnowemail);
                EditText password = (EditText) findViewById(R.id.joinnowpassword);
                mfirebase.createUser(email.getText().toString(), password.getText().toString());

                TulypApplication.mFirebase.getFirebaseRef().addAuthStateListener(new Firebase.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(AuthData authData) {
                        MyFirebase mfirebase = TulypApplication.mFirebase;
                        if (authData != null && counter < 1) {
                            EditText email = (EditText) findViewById(R.id.joinnowemail);
                            String uid = authData.getUid();
                            Log.d("##UID JOINNOW##", uid);
                            counter++;
                            TulypApplication.mFirebase.setNewUserInfo(TulypApplication.mUser);
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
        String text = (String) signIn.getText();
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        signIn.setText(spannableString);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SignIn.class);
                startActivity(intent);
            }
        });

    }


}
