package com.MyTask.phonebook.model;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "PersonalDataModel")
public class PersonalDataModel {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String Latitude;
    private String longitude;
    private String code;
    private String phone;
    private String image;
    private String createdAt;
    private String updatedAt;

    public PersonalDataModel(){

    }

    public PersonalDataModel(String image,String firstName, String lastName, String email, String latitude, String longitude,String code, String phone, String createdAt, String updatedAt) {
        this.image = image;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        Latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.code = code ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    public boolean validateFirstName(){

        if(this.firstName.length()>=3){
            return  false;
        }{
            return  true;
        }
    }

    public boolean validatephone(){

        Log.d("dsmcds",this.phone.length()+"");
        if(this.phone.length()>=10){
            return  false;
        }{
            return  true;
        }

    }

    public boolean validateLatitude(){

        Double Latitude ;
        if(!this.Latitude.isEmpty()) {
            Latitude=Double.parseDouble(this.Latitude);
            if (Latitude >= -90 && Latitude <= 90) {
                return false;
            }
            {
                return true;
            }
        }

        return true;
    }

    public boolean validatelongitude(){

        Double longitude ;
        if(!this.longitude.isEmpty()) {
            longitude=Double.parseDouble(this.longitude);
            if (longitude >= -180 && longitude <= 180) {
                return false;
            }
            {
                return true;
            }
        }
        return true;
    }

    public  boolean validateEmail() {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}
