package com.example.simsim.ui;

/**
 * Created by Qiuge on 2015/7/24.
 */
public interface UIConstantInterface {

    String MESSAGE_LOGIN_EXCEPTION = "Login failed. Please check your network and try again.";
    String MESSAGE_LOGIN_WRONG_PASSWORD = "Login failed. Please check your number and password.";

    String MESSAGE_REGISTRATION_EXCEPTION
            = "Registration failed. Please check your network and try again.";
    String MESSAGE_REGISTRATION_WRONG_CODE = "Registration failed. Wrong verification code.";
    String MESSAGE_REGISTRATION_SUCCESS = "Congratulation! You became a Smart Lock user.";
    String MESSAGE_REGISTRATION_FAILURE = "Registration failed.";

    String MESSAGE_PROFILE_LOAD_AVATAR_EXCEPTION = "Error. Cannot get your avatar image.";
    String MESSAGE_PROFILE_UPDATE_SUCCESS = "Profile update succeed";
    String MESSAGE_PROFILE_UPDATE_EXCEPTION
            = "Profile update failed. Please check your network and try again.";

    String MESSAGE_UPDATE_SUCCESS = "Update succeed.";
    String MESSAGE_UPDATE_EXCEPTION
            = "Update failed. Please check your network and try again.";

    String MESSAGE_DELETE_SUCCESS = "Delete succeed.";
    String MESSAGE_DELETE_EXCEPTION
            = "Delete failed. Please check your network and try again.";

}
