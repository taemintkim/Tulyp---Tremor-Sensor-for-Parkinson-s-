package io.github.kathyyliang.tulyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

//todo: this whole activity. User profile data is in mUser.
//todo: what if the patient wants to edit their info? Make an option to edit.
public class MedicalProfile extends AppCompatActivity {
    User user = TulypApplication.mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Medical Profile");
        setSupportActionBar(toolbar);

        EditText fullName = (EditText) findViewById(R.id.fullname);
        EditText dateOfBirth = (EditText) findViewById(R.id.dateofbirth);
        EditText feet = (EditText) findViewById(R.id.feet);
        EditText inches = (EditText) findViewById(R.id.inches);
        EditText pounds = (EditText) findViewById(R.id.pounds);
        ArrayList<String> height = new ArrayList<String>();
        height.add(feet.getText().toString());
        height.add(inches.getText().toString());
    }


}
