package io.github.kathyyliang.tulyp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.HashMap;

public class DoctorMedication extends AppCompatActivity {
    User user = TulypApplication.mUser;
    HashMap<String, Drug> drugs = user.getMedications();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_medication);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Medication");
        setSupportActionBar(toolbar);
    }
}
