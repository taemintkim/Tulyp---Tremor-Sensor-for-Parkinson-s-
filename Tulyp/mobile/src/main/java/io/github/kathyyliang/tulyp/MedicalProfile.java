package io.github.kathyyliang.tulyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MedicalProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Medical Profile");
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById(R.id.medicalprofile);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (String) ((TextView) view).getText();
                if (text.equalsIgnoreCase("Medication")) {
                    Intent intent = new Intent(getBaseContext(), Medication.class);
                    startActivity(intent);
                }
            }
        });
    }
}
