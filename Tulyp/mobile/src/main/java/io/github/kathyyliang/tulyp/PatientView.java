package io.github.kathyyliang.tulyp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;

public class PatientView extends AppCompatActivity {
    private MyFirebase myFirebase = TulypApplication.mFirebase;
    private double[] todaysYData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view);

        pullSensorData(myFirebase.getUID()); //getting today's data.

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Medical Data");
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager(), PatientView.this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
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
                Intent intent = new Intent(getBaseContext(), PatientSettings.class);
                startActivity(intent);

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Fills in todaysYData with the averaged data points for today's tremor sensor data.
     * todaysYData should be size 24 with index corresponding to hour of the day.
     * @param uid of the user whose data we fetch
     */
    private void pullSensorData(String uid) {
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
    }
}
