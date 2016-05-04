package io.github.kathyyliang.tulyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class DoctorMedication extends AppCompatActivity {
    User user = TulypApplication.mUser;
    MyFirebase mfirebase = TulypApplication.mFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_medication);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Medication");
        setSupportActionBar(toolbar);
    }

    public void updateMedicationView(View view) {
        EditText medication = (EditText) findViewById(R.id.editMedication);
        EditText instructions = (EditText) findViewById(R.id.editDosage);
        EditText warnings = (EditText) findViewById(R.id.editWarnings);
        EditText start_date = (EditText) findViewById(R.id.editFirstDay);
        EditText end_date = (EditText) findViewById(R.id.editLastDay);

        String medName = medication.getText().toString();
        String instr = instructions.getText().toString();
        String warns = warnings.getText().toString();
        String start = start_date.getText().toString();
        String end = end_date.getText().toString();

        Drug d = new Drug(instr, warns, start, end);

        HashMap<String, Drug> drugs = new HashMap<>();
        drugs.put(medName, d);
        user.setMedications(drugs);
        mfirebase.setNewUserInfo(user);

        Intent intent = new Intent(getBaseContext(), ViewMedicalProfile.class);
        startActivity(intent);
    }
}
