package io.github.kathyyliang.tulyp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class DoctorSettings extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_settings);
        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById(R.id.doctorsettings);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (String) ((TextView) view).getText();
                if (text.equalsIgnoreCase("Account")) {
                    Intent intent = new Intent(getBaseContext(), AccountInfo.class);
                    startActivity(intent);
                } else if (text.equalsIgnoreCase("Log Out")) {
                    logout();
                } else if (text.equalsIgnoreCase("About")) {
                    new AlertDialog.Builder(context)
                            .setMessage("About\n" +
                                    "\n" +
                                    "2016 Tulyp Inc. \n" +
                                    "\n" +
                                    "Version 4.6\n" +
                                    "\n" +
                                    "A Parkinson’s data collection application to improve diagnostics capabilities and patient-physician coordination.")
                            .show();

                } else if (text.equalsIgnoreCase("Help")) {
                    new AlertDialog.Builder(context)
                            .setMessage("Help\n" +
                                    "\n" +
                                    "FAQ\n" +
                                    "\n" +
                                    "Q: Why isn’t my sensor collecting data? \n" +
                                    "\n" +
                                    "A: Make sure your smart watch and smartphone are paired together. Otherwise the data will not register on the app!\n" +
                                    "\n" +
                                    "Q: How do I connect to my physician’s account? \n" +
                                    "\n" +
                                    "A: We’ll send your physician a secure e-mail with access to your account and information!\n" +
                                    "\n" +
                                    "Q: How is my privacy managed in the application? \n" +
                                    "\n" +
                                    "A: We take your privacy as a patient very seriously! No one but your primary care physician will have access to your account.  \n" +
                                    "\n" +
                                    "For further questions and inquiries please e-mail tulyp@berkeley.edu")
                            .show();
                }
            }
        });
    }

    private void logout() {
        TulypApplication.mFirebase.logout();
        TulypApplication.mUser = null;
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
