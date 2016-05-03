package io.github.kathyyliang.tulyp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
}
