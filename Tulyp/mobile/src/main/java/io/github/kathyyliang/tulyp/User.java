package io.github.kathyyliang.tulyp;

import android.graphics.Bitmap;
import android.util.Log;

import com.firebase.client.Firebase;

import java.util.HashMap;

/**
 * Created by TK on 5/1/16.
 */
public class User {
    private int birthdate;
    private String name;
    private String email;
    private Bitmap profilepic;
    private String phonenumber;
    private boolean isDoctor;
    private String gender;
    private String weight;
    private String height;
    private HashMap<String, Drug> medications;
    private String tremordata;
    private HashMap<String, User> patients;


    public User() {}

    public User(String email) {
        this.email = email;
    }

    public void setPatients(HashMap<String, User> pats) {
        if (isDoctor) {
            patients = pats;
        } else {
            Log.d("User", "Trying to add patients to a non-Doctor");
        }
    }

    public HashMap<String, User> getPatients() {
        return patients;
    }

    public User(String fullName, int birthdate, String email) {
        this.name = fullName;
        this.birthdate = birthdate;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String number) {
        phonenumber = number;
    }

    public String getPhoneNumber() {
        return phonenumber;
    }
    public void setBirthdate(int birthdate) {
        this.birthdate = birthdate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfilepic(Bitmap profilepic) {
        this.profilepic = profilepic;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setIsDoctor(boolean isDoctor) {
        this.isDoctor = isDoctor;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setMedications(HashMap<String, Drug> medications) {
        this.medications = medications;
    }

    public void setTremordata(String tremordata) {
        this.tremordata = tremordata;
    }

    public boolean isDoctor() {
        return isDoctor;
    }
    public int getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public Bitmap getProfilepic() {
        return profilepic;
    }

    public String getGender() {
        return gender;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public HashMap<String, Drug> getMedications() {
        return medications;
    }

    public String getTremordata() {
        return tremordata;
    }


    /**
     * Data structure to store medication information.
     */
    public class Drug {
        private String instructions;
        private String warnings;
        private String start_date;
        private String end_date;
        public Drug(){}
        public Drug(String inst, String warnings, String start_date, String end_date) {
            this.instructions = inst;
            this.warnings = warnings;
            this.start_date = start_date;
            this.end_date = end_date;
        }
    }
}
