package io.github.kathyyliang.tulyp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CompleteProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        EditText fullName = (EditText) findViewById(R.id.fullname);
        EditText dateOfBirth = (EditText) findViewById(R.id.dateofbirth);

        Button button = (Button) findViewById(R.id.finishprofile);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), PatientView.class);
                startActivity(intent);
            }
        });
    }
}
