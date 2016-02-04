package com.example.simsim.entities;


import java.io.Serializable;

public class LockActivity implements Serializable {

    private int lockActicityId;
    private int hostId;
    private int guestId;
    private int lockId;
    private String accessStartTime;
    private String accessEndTime;
    private String requestAccessTimestamp;
    private String requestStatus;
    private int alert;

    public int getLockActicityId() {
        return lockActicityId;
    }

    public void setLockActicityId(int lockActicityId) {
        this.lockActicityId = lockActicityId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getLockId() {
        return lockId;
    }

    public void setLockId(int lockId) {
        this.lockId = lockId;
    }

    public String getAccessStartTime() {
        return accessStartTime;
    }

    public void setAccessStartTime(String accessStartTime) {
        this.accessStartTime = accessStartTime;
    }

    public String getAccessEndTime() {
        return accessEndTime;
    }

    public void setAccessEndTime(String accessEndTime) {
        this.accessEndTime = accessEndTime;
    }

    public String getRequestAccessTimestamp() {
        return requestAccessTimestamp;
    }

    public void setRequestAccessTimestamp(String requestAccessTimestamp) {
        this.requestAccessTimestamp = requestAccessTimestamp;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getAlert() {
        return alert;
    }

    public void setAlert(int alert) {
        this.alert = alert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LockActivity lockActivity = (LockActivity) o;
        if (lockActicityId != lockActivity.getLockActicityId()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return ((Integer)lockActicityId).hashCode();
    }
}
