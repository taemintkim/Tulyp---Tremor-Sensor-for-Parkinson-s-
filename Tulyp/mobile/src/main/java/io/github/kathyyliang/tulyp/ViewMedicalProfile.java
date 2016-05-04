package io.github.kathyyliang.tulyp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class ViewMedicalProfile extends AppCompatActivity {
    ArrayList<User> patients = TulypApplication.patients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medical_profile);
    }
}
