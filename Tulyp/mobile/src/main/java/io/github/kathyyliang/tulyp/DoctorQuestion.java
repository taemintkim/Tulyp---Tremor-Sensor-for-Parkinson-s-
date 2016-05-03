package io.github.kathyyliang.tulyp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_question);

        Button yesButton = (Button) findViewById(R.id.yesbutton);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TulypApplication.mUser.setIsDoctor(true);
                Intent intent = new Intent(getBaseContext(), DoctorView.class);
                startActivity(intent);
            }
        });

        Button noButton = (Button) findViewById(R.id.nobutton);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TulypApplication.mUser.setIsDoctor(false);
                Intent intent = new Intent(getBaseContext(), CompleteProfile.class);
                startActivity(intent);
            }
        });
    }

    void testUsers() {
        User user = new User();
        user.setEmail("testuser");
        user.setBirthdate("12/32/43");
        user.setIsDoctor(true);
        user.setName("test name");
        HashMap<String, Drug> meds = new HashMap<>();
        meds.put("heroin", new Drug("take daily", "you may die", "1234", "3456"));
        meds.put("cocaine", new Drug("take daildsafsday", "you mfdsafdsay die", "1234", "3456"));
        user.setMedications(meds);
        user.setWeight("fat as shit");
        ArrayList<User> patients = new ArrayList<>();
        patients.add(new User("pat1"));
        patients.add(new User("pat2"));
    }

}
