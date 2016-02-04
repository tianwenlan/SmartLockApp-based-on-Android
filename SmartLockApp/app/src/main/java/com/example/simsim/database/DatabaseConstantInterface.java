package com.example.simsim.database;

/**
 * Created by Qiuge on 2015/7/24.
 */
public interface DatabaseConstantInterface {

    String USER_STATE_HOST = "host";
    String USER_STATE_GUSET = "guest";
    String USER_STATE_DEFAULT = USER_STATE_HOST;
    String USER_BIRTHDAY_DEFAULT = "1970-01-01";
    String USER_GENDER_MALE = "male";
    String USER_GENDER_FEMALE = "female";
    String USER_GENDER_DEFAULT = USER_GENDER_MALE;

    String LOCK_DIRECTION_CLOCKWISE = "clockwise";
    String LOCK_DIRECTION_COUNTERCLOCKWISE = "counterclockwise";

    String LOCK_ACTIVITY_REQUEST_STATUS_PENDING = "pending";
    String LOCK_ACTIVITY_REQUEST_STATUS_ACCEPT = "accepted";
    String LOCK_ACTIVITY_REQUEST_STATUS_REJECT = "rejected";
}
