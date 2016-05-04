package io.github.kathyyliang.tulyp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by jenny0322 on 5/3/16.
 */
public class PatientArrayAdapter extends BaseAdapter {
    protected ArrayList<String> names;
    protected ArrayList<String> medication;
    protected ArrayList<String> age;
    protected Context context;
    protected ArrayList<String> patientID;

    public PatientArrayAdapter(Context context, ArrayList<String> names, ArrayList<String> medication, ArrayList<String> patientID, ArrayList<String> age){
        this.context = context;
        this.medication = medication;
        this.age = age;
        this.names = names;
        this.patientID = patientID;

    }
        @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context

                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.patient_card, parent, false);
        TextView nameView = (TextView) v.findViewById(R.id.name);
        TextView ageView = (TextView) v.findViewById(R.id.age);
        TextView medicationView = (TextView) v.findViewById(R.id.medication);
        Button medicalProfile = (Button)v.findViewById(R.id.medicalprofilebutton);
        Button moreData = (Button)v.findViewById(R.id.moredatabutton);
        moreData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent detail = new Intent(context, DoctorPatientView.class);
                detail.putExtra("patientID", patientID.get(position));
                context.startActivity(detail);
            }
        });
        medicalProfile.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                //Send Toast or Launch Activity here
                Intent detail = new Intent(context, ViewMedicalProfile.class);
                detail.putExtra("patientID", patientID.get(position));
                context.startActivity(detail);


            }

        });




        nameView.setText(names.get(position));
        ageView.setText(age.get(position));
        medicationView.setText(medication.get(position));
        return v;
    }
}
