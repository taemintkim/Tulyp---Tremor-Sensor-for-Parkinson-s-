package io.github.kathyyliang.tulyp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;

public class DoctorPatientView extends AppCompatActivity {
    private double[] todaysYData;
    private MyFirebase myFirebase = TulypApplication.mFirebase;
    private String patientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_patient_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Medical Data");
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager(), DoctorPatientView.this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }
    /**
     * @param uid
     */
    /*public void pullSensorData(String uid) {
        todaysYData = new double[24];
        Firebase mfirebase = myFirebase.getFirebaseRef();
        Firebase userRef = mfirebase.child("SensorData").child(uid);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                HashMap<String, Object> data = (HashMap<String, Object>) snapshot.getValue();
                if (data == null) {
                    Log.d("Firebase", "No Sensor data for this user");
                    todaysYData = new double[24];
                    return;
                }
                TulypApplication.setTremordata(data);
                todaysYData = MyFirebase.makeDayDataPoints(data);
                if (todaysYData == null) {
                    todaysYData = new double[24];
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("Firebase", "Failed to retrieve sensor data\n" + firebaseError);
            }
        });
    }*/
}
