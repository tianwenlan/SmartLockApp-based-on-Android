package com.example.simsim.interfaces;

/**
 * Created by Steven on 7/23/15.
 */
public interface RegistrationInterface {

     void setBasicUserInfo(String name, String password, String country, String primaryPhoneNumber,
                           String userState);
     boolean insertUser() throws Exception; // Insert user to DB, set userId to User object in Information.
     String getUserState();

}
