package com.example.simsim.entities;


import java.io.Serializable;

public class User implements Serializable{

    private int userId;
    private String name;
    private String country;
    private String primaryPhoneNumber;
    private String isMobileVerified;
    private String password;
    private String userState; // "host" or "guest"
    private String dataOfBirth;
    private String gender;
    private String emailAddress;
    private int zipCode;
    private String icon;

    public User(){
        userId = 0;
        name = "";
        country = "";
        primaryPhoneNumber = "";
        isMobileVerified = "";
        password = "";
        userState = "";
        dataOfBirth = "";
        gender = "";
        emailAddress = "";
        zipCode = 0;
        icon = "";
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    public String getIsMobileVerified() {
        return isMobileVerified;
    }

    public void setIsMobileVerified(String isMobileVerified) {
        this.isMobileVerified = isMobileVerified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getDataOfBirth() {
        return dataOfBirth;
    }

    public void setDataOfBirth(String dataOfBirth) {
        this.dataOfBirth = dataOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

