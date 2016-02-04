package com.example.simsim.interfaces;

import com.example.simsim.entities.LockActivity;
import com.example.simsim.entities.Property;

import java.util.List;

/**
 * Created by Steven on 7/23/15.
 */
public interface GuestEventInterface {//events

    List<LockActivity> getFutureLockActivity();
    void updateLockActivity(LockActivity lockActivity) throws Exception;
    boolean loadHostIdFromDB(String primaryPhoneNumber) throws Exception;
    int getGuestId();
    int getHostId();
    List<Property> getHostPropertyList(int hostId);
    List<Integer> getHostLockIdList(Property property);
    void setNewLockActivityHostId(int hostId);
    void setNewLockActivityGuestId(int guestId);
    void setNewLockActivityLockId(int lockId);
    void setNewLockActivityAccessStartTime(String accessStartTime);
    void setNewLockActivityAccessEndTime(String accessEndTime);
    void setNewLockActivityRequestAccessTimestamp(String requestAccessTimestamp);
    void setNewLockActivityRequestStatus(String requestStatus);
    void setNewLockActivityAlert(int alert);
    void insertNewLockActivity() throws Exception; // Insert new lockActivity to DB, set lockActivityId to LockActivity object in Information.

}
