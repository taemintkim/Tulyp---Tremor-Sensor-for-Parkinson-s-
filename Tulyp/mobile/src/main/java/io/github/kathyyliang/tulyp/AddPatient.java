package io.github.kathyyliang.tulyp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;

//todo: get user input and call addPatient. check if input is empty.
public class AddPatient extends AppCompatActivity {
    User user = TulypApplication.mUser;
    MyFirebase myfirebase = TulypApplication.mFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Add Patient");
        setSupportActionBar(toolbar);
        addPatient("nonexistent@csa.com");
        addPatient("thomas3@f.com");
    }

    public void addPatient(String email) {
        Query queryRef = myfirebase.getFirebaseRef().child("Users").limitToFirst(2).orderByChild("email").equalTo(email);
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                HashMap<String, Object> map = (HashMap<String, Object>) snapshot.getValue();
                String patID;
                if (map == null) {
                    patID = null;
                } else {
                    String[] ids = map.keySet().toArray(new String[1]);
                    patID = ids[0];
                }
                if (patID == null || patID.equals("")) {
                    //todo: no patient with that email found.
                    Log.d("AddPatient", "No patient found for input email");
                    return;
                }
                user.addPatientID(patID);
                Log.d("AddPatient", "Successfully found patient's ID" + patID);
                myfirebase.setNewUserInfo(user);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("Firebase", "Failed to retrieve User data\n" + firebaseError);
            }
        });
    }
}
