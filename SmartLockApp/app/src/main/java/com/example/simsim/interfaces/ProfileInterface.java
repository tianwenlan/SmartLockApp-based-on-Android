package com.example.simsim.interfaces;

/**
 * Created by Steven on 7/23/15.
 */
public interface ProfileInterface {

     void updateProfile(String dataOfBirth, String gender, String emailAddress,
                        int zipCode, String icon) throws Exception;
     String getUserState();
     String getDateOfBirth();
     String getGender();
     String getEmailAddress();
     int getZipCode();
     String getIcon();

}
