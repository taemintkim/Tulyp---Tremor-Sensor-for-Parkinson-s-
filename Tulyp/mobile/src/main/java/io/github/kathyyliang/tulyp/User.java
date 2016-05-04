package io.github.kathyyliang.tulyp;

import android.graphics.Bitmap;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by TK on 5/1/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String birthdate;
    private String name;
    private String email;
    private Bitmap profilepic;
    private String phonenumber;
    private boolean isDoctor;
    private int gender;
    private String weight;
    private ArrayList<String> height;
    private HashMap<String, Drug> medications;
    private String tremordata;
    private ArrayList<User> patients;
    private ArrayList<String> patientIDs;


    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public User(String fullName, String birthdate, String email) {
        this.name = fullName;
        this.birthdate = birthdate;
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

    public void setPatientIDs(ArrayList<String> ids) {
        patientIDs = ids;
    }

    public void addPatientID(String id) {
        if (patientIDs == null) {
            patientIDs = new ArrayList<>();
        }
        patientIDs.add(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phonenumber;
    }

    public void setPhoneNumber(String number) {
        phonenumber = number;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setIsDoctor(boolean isDoctor) {
        this.isDoctor = isDoctor;
    }

    public void setTremordata(String tremordata) {
        this.tremordata = tremordata;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public boolean getIsDoctor() {
        return isDoctor;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Bitmap getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(Bitmap profilepic) {
        this.profilepic = profilepic;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public ArrayList<String> getHeight() {
        return height;
    }

    public void setHeight(ArrayList<String> height) {
        this.height = height;
    }

    public HashMap<String, Drug> getMedications() {
        return medications;
    }

    public void setMedications(HashMap<String, Drug> medications) {
        this.medications = medications;
    }

//    public String getTremordata() {
//        return tremordata;
//    }

//    void testUsers() {
//        User user = new User();
//        user.setEmail("testuser");
//        user.setBirthdate("12/32/43");
//        user.setIsDoctor(true);
//        user.setName("test name");
//        HashMap<String, Drug> meds = new HashMap<>();
//        meds.put("heroin", new Drug("take dieaily", "you may die", "1234", "3456"));
//        meds.put("cocaine", new Drug("take daildsafsday", "you mfdsafdsay die", "1234", "3456"));
//        user.setMedications(meds);
//        user.setWeight("fat as shit");
//        ArrayList<String> patients = new ArrayList<>();
//        patients.add("pat1");
//        patients.add("pat2");
//        user.setPatientIDs(patients);
//        TulypApplication.mFirebase.setNewUserInfo(user);
//    }
}
