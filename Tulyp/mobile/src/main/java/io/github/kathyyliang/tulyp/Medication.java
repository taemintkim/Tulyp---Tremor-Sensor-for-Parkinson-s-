package io.github.kathyyliang.tulyp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.HashMap;

//todo: this whole activity.
public class Medication extends AppCompatActivity {
    User user = TulypApplication.mUser;
    HashMap<String, Drug> meds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        meds = user.getMedications();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Medication");
        setSupportActionBar(toolbar);
    }
}
