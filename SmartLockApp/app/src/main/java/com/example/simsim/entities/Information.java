package com.example.simsim.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Information {

    // Store the data get from database
    private User user;
    private List<Lock> guestLock;
    private Map<Property, List<Lock>> hostPropLockMap;
    private Map<Lock, List<LockActivity>> lockLockActivityMap;

    // Temporarily store the new object to be added
    private Lock newLock;
    private LockActivity newLockActivity;
    private int hostId;

    public Information(){
        user = new User();
        guestLock = new ArrayList<Lock>();
        hostPropLockMap = new HashMap<Property, List<Lock>>();
        lockLockActivityMap = new HashMap<Lock, List<LockActivity>>();

        newLock = new Lock();
        newLockActivity = new LockActivity();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Lock> getGuestLock() {
        return guestLock;
    }

    public void setGuestLock(List<Lock> guestLock) {
        this.guestLock = guestLock;
    }

    public Map<Property, List<Lock>> getHostPropLockMap() {
        return hostPropLockMap;
    }

    public void setHostPropLockMap(Map<Property, List<Lock>> hostPropLockMap) {
        this.hostPropLockMap = hostPropLockMap;
    }

    public Map<Lock, List<LockActivity>> getLockLockActivityMap() {
        return lockLockActivityMap;
    }

    public void setLockLockActivityMap(Map<Lock, List<LockActivity>> lockLockActivityMap) {
        this.lockLockActivityMap = lockLockActivityMap;
    }

    public Lock getNewLock() {
        return newLock;
    }

    public void setNewLock(Lock newLock) {
        this.newLock = newLock;
    }

    public LockActivity getNewLockActivity() {
        return newLockActivity;
    }

    public void setNewLockActivity(LockActivity newLockActivity) {
        this.newLockActivity = newLockActivity;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }
}
