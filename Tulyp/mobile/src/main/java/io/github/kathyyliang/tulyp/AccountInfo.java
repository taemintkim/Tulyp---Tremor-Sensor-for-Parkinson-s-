package io.github.kathyyliang.tulyp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class AccountInfo extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        context = this;
        TextView email = (TextView) findViewById(R.id.email);
        if (TulypApplication.mUser != null && TulypApplication.mUser.getEmail() != null) {
            email.setText(TulypApplication.mUser.getEmail());
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Account Info");
        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.savebutton1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText oldp = (EditText) findViewById(R.id.currentpassword);
                EditText newp = (EditText) findViewById(R.id.newpassword);
                EditText newp2 = (EditText) findViewById(R.id.confirmnewpassword);
                changepassword(TulypApplication.mUser.getEmail(), newp.getText().toString(), newp2.getText().toString());
            }
        });
    }

    private void changepassword(String email, String oldpw, String newpw) {
        TulypApplication.mFirebase.getFirebaseRef().changePassword(email, oldpw, newpw, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                // password changed
                CharSequence text = "Password Changed!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration); //not sure if this way of passing in context is correct.
                toast.show();
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                // error encountered
                CharSequence text = "Error: Try again.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration); //not sure if this way of passing in context is correct.
                toast.show();
            }
        });
    }
}
