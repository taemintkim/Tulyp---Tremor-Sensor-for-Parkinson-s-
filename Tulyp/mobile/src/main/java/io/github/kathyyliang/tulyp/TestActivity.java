package io.github.kathyyliang.tulyp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.client.Firebase;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Firebase.setAndroidContext(this);

        Firebase myFirebaseRef = new Firebase("https://intense-torch-7280.firebaseio.com/");
        double[] testarray = {0.0, 0.2, 0.3, 0.5};
        myFirebaseRef.child("sensordata").setValue(testarray);
    }

}
