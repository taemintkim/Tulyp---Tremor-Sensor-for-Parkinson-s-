package io.github.kathyyliang.tulyp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MedicalProfile extends AppCompatActivity {
    User user = TulypApplication.mUser;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_profile);
        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Medical Profile");
        setSupportActionBar(toolbar);

        EditText fullName = (EditText) findViewById(R.id.fullname);
        EditText dateOfBirth = (EditText) findViewById(R.id.dateofbirth);
        EditText feet = (EditText) findViewById(R.id.feet);
        EditText inches = (EditText) findViewById(R.id.inches);
        EditText pounds = (EditText) findViewById(R.id.pounds);
        if (user.getName() != null) {
            fullName.setText(user.getName());
        }
        if (user.getBirthdate()!= null) {
            dateOfBirth.setText(user.getBirthdate());
        }
        if (user.getHeight() != null) {
            feet.setText(user.getHeight().get(0));
        }
        if (user.getHeight() != null) {

            inches.setText(user.getHeight().get(1));
        }
        if (user.getWeight() != null) {

            pounds.setText(user.getWeight());
        }
            Spinner spinner = (Spinner) findViewById(R.id.spinner);
            spinner.setSelection(user.getGender());

        Button button = (Button) findViewById(R.id.savebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fullName = (EditText) findViewById(R.id.fullname);
                EditText dateOfBirth = (EditText) findViewById(R.id.dateofbirth);
                EditText feet = (EditText) findViewById(R.id.feet);
                EditText inches = (EditText) findViewById(R.id.inches);
                EditText pounds = (EditText) findViewById(R.id.pounds);
                ArrayList<String> height = new ArrayList<String>();
                height.add(feet.getText().toString());
                height.add(inches.getText().toString());
                // spinner entries: 0 - male, 1 - female, 2 - other
                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                int gender = spinner.getSelectedItemPosition();
                user.setWeight(pounds.getText().toString());
                user.setHeight(height);
                user.setBirthdate(dateOfBirth.getText().toString());
                user.setName(fullName.getText().toString());
                user.setGender(gender);
                TulypApplication.mFirebase.setNewUserInfo(user);
                CharSequence text = "Information Updated!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration); //not sure if this way of passing in context is correct.
                toast.show();
            }
        });
    }


}
