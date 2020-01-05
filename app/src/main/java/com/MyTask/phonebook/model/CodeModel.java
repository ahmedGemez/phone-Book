package com.MyTask.phonebook.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Code")
public class CodeModel {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String code;
    private String country;
    private String capital ;



    public CodeModel(String code, String country, String capital) {
        this.code = code;
        this.country = country;
        this.capital = capital;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }


}
