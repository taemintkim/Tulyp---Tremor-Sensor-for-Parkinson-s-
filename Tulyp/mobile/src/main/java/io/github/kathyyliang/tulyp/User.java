package io.github.kathyyliang.tulyp;

import android.graphics.Bitmap;
import android.util.Log;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by TK on 5/1/16.
 */
public class User {
    private String birthdate;
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
    private ArrayList<User> patients;
    private ArrayList<String> patientIDs;


    public User() {}

    public User(String email) {
        this.email = email;
    }

    public void setPatients(ArrayList<User> pats) {
        if (isDoctor) {
            patients = pats;
        } else {
            Log.d("User", "Trying to add patients to a non-Doctor");
        }
    }

    public ArrayList<String> getPatientIDs() {
        return patientIDs;
    }

    public void addPatientID(String id) {
        patientIDs.add(id);
    }


    public User(String fullName, String birthdate, String email) {
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
    public void setBirthdate(String birthdate) {
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
    public String getBirthdate() {
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

//    public String getTremordata() {
//        return tremordata;
//    }
}
