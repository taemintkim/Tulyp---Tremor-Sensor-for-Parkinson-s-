package io.github.kathyyliang.tulyp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

//todo: this whole activity.
public class Medication extends AppCompatActivity {
    User user = TulypApplication.mUser;
    HashMap<String, Drug> meds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Medication");
        setSupportActionBar(toolbar);

        meds = user.getMedications();
        if (meds == null || meds.isEmpty()) { return; }
        String medication = (String) meds.keySet().toArray()[0];
        Drug medData = meds.get(medication);
        String[] medDataArray = medData.toArray();
        medDataArray[0] = medication;

        ArrayAdapter<String> drugsAdapter = new ArrayAdapter<>(
          this, android.R.layout.simple_list_item_1, medDataArray
        );

        ListView l = (ListView) findViewById(R.id.medication);
        l.setAdapter(drugsAdapter);
    }
}
