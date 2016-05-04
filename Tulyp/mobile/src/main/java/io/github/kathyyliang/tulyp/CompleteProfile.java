package io.github.kathyyliang.tulyp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CompleteProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        EditText fullName = (EditText) findViewById(R.id.fullname);
        EditText dateOfBirth = (EditText) findViewById(R.id.dateofbirth);
        EditText feet = (EditText) findViewById(R.id.feet);
        EditText inches = (EditText) findViewById(R.id.inches);
        EditText pounds = (EditText) findViewById(R.id.pounds);
        // spinner entries: 0 - male, 1 - female, 2 - other
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        int gender = spinner.getSelectedItemPosition();

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
