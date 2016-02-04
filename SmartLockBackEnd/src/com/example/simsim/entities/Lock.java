package com.example.simsim.entities;


import java.io.Serializable;

public class Lock implements Serializable {

    private int lockId;
    private int propertyId;
    private String description;
    private boolean isLocked;
    private double lockPower;
    private double lockStartAngle;
    private double lockEndAngle;
    private String rotationDirection;
    private double rotationEndPoints;

    public int getLockId() {
        return lockId;
    }

    public void setLockId(int lockId) {
        this.lockId = lockId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public double getLockPower() {
        return lockPower;
    }

    public void setLockPower(double lockPower) {
        this.lockPower = lockPower;
    }

    public double getLockStartAngle() {
        return lockStartAngle;
    }

    public void setLockStartAngle(double lockStartAngle) {
        this.lockStartAngle = lockStartAngle;
    }

    public double getLockEndAngle() {
        return lockEndAngle;
    }

    public void setLockEndAngle(double lockEndAngle) {
        this.lockEndAngle = lockEndAngle;
    }

    public String getRotationDirection() {
        return rotationDirection;
    }

    public void setRotationDirection(String rotationDirection) {
        this.rotationDirection = rotationDirection;
    }

    public double getRotationEndPoints() {
        return rotationEndPoints;
    }

    public void setRotationEndPoints(double rotationEndPoints) {
        this.rotationEndPoints = rotationEndPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lock lock = (Lock) o;
        if (lockId != lock.getLockId()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return ((Integer)lockId).hashCode();
    }
}