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
 * Created by jenny0322 on 5/4/16.
 */
public class MedicalProfileAdapter extends BaseAdapter {
    protected ArrayList<String> height;
    protected String weight;
    protected String gender;
    protected String contact;
    protected String medication;
    protected String[] headings;
    Context context;
    protected String age;

    public MedicalProfileAdapter(Context context, String[] headings, ArrayList<String> height, String weight, String age, String gender, String contact,String medication ){
        this.headings = headings;
        this.context = context;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.contact = contact;
        this.medication = medication;
        this.age = age;

    }



    @Override
    public int getCount() {
        return headings.length;
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

        TextView heading = (TextView)v.findViewById(R.id.heading);
        heading.setText(headings[position]);

        TextView detail = (TextView)v.findViewById(R.id.detailrow);
        if (headings[position].equals("Date of Birth")) {
            detail.setText(age);
        }else if (headings[position].equals("Gender")) {
            if (gender.equals("0")) {
                detail.setText("Male");
            }else if (gender.equals("1")){
                detail.setText("Female");
            }else{
                detail.setText("Others");
            }
        }else if (headings[position].equals("Weight")) {
            detail.setText(weight + " lbs");
        }else if (headings[position].equals("Height")) {
            detail.setText(height.get(0) + " in" + height.get(1) + " ft" );
        }else if (headings[position].equals("Contact")) {
            detail.setText(contact);
        }else if (headings[position].equals("Medication")) {
            detail.setText(medication);
            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent detail = new Intent(context, DoctorMedication.class);

                    context.startActivity(detail);
                }
            });

        }


        return v;
    }
}
