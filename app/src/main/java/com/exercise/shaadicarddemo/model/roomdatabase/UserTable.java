package com.exercise.shaadicarddemo.model.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "user_add_table")
public class UserTable implements Serializable {


    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getFullNameAge() {
        return fullNameAge;
    }

    public void setFullNameAge(String fullNameAge) {
        this.fullNameAge = fullNameAge;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistrationPeriod() {
        return registrationPeriod;
    }

    public void setRegistrationPeriod(String registrationPeriod) {
        this.registrationPeriod = registrationPeriod;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "u_id")
    private int u_id;

    @ColumnInfo(name = "fullNameAge")
    private String fullNameAge;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "registrationPeriod")
    private String registrationPeriod;

    @ColumnInfo(name = "picture")
    private String picture;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ColumnInfo(name = "status")
    private String status;

}

