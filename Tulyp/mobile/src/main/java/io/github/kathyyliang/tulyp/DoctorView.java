package io.github.kathyyliang.tulyp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorView extends AppCompatActivity {
    MyFirebase mfirebase = TulypApplication.mFirebase;
    User mUser = TulypApplication.mUser;
    ArrayList<User> patients = new ArrayList<>();
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> medication = new ArrayList<String>();
    ArrayList<String> age = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view);

        // check if we have data.
        if (mUser == null) {
            //todo load user data from online
            Log.d("DoctorView", "No User data loaded.");
        }
        ArrayList<String> patientIDs = mUser.getPatientIDs();
        fetchPatientsData(patientIDs);



        final ListView patientList = (android.widget.ListView) findViewById(R.id.patientList);

        final PatientArrayAdapter adapter = new PatientArrayAdapter(this, names, medication, patientIDs, age);
        patientList.setAdapter(adapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Patients");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddPatient.class);
                startActivity(intent);
            }
        });

    }

    /**
     * THIS IS ASYNCHRONOUS meaning you don't know when and the order this method will finish getting patient data.
     * @param uids
     */
    public void fetchPatientsData(ArrayList<String> uids) {
        if (uids == null) { //this doctor has no patients.
            return;
        }
        for (String id : uids) {
            mfirebase.getFirebaseRef().child("Users").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    patients.add(snapshot.getValue(User.class));

                    //todo: update View to show patient information
                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    Log.d("DoctorView", "Failed to retrieve User data\n" + firebaseError);
                }
            });
        }
        for (User u: patients) {
            names.add(u.getName());
//            medication.add();
            age.add(u.getBirthdate());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Intent intent = new Intent(getBaseContext(), DoctorSettings.class);
                startActivity(intent);

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
