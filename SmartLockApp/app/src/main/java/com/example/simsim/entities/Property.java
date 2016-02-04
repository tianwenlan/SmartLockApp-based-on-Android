package com.example.simsim.entities;


import java.io.Serializable;

public class Property implements Serializable {

    private int propertyId;
    private int userId;
    private String description;
    private String address;
    private int zipCode;
    private String city;
    private String state;
    private String country;
    private String ownership;

    public Property(){
        description = "";
        address = "";
        city = "";
        state = "";
        country = "";
        ownership = "";
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        if (propertyId != property.getPropertyId()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return ((Integer)propertyId).hashCode();
    }
}
