package io.github.kathyyliang.tulyp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class JoinNow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_now);
        Firebase.setAndroidContext(this);

        Button joinNow = (Button) findViewById(R.id.joinnowbutton);
        joinNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = (EditText) findViewById(R.id.joinnowemail);
                EditText password = (EditText) findViewById(R.id.joinnowpassword);
                MyFirebase firebase = new MyFirebase();
                firebase.createUser(email.getText().toString(), password.getText().toString());
                firebase.login(email.getText().toString(), password.getText().toString(), getApplicationContext());
                Intent intent = new Intent(getBaseContext(), DoctorQuestion.class);
                startActivity(intent);
            }
        });
    }


}
