package com.address.entity;


public class Address {
    private String studentId;
    private String street;
    private String city;
    private String state;

    // Constructors, getters, and setters

    public Address(String studentId, String street, String city, String state) {
        this.studentId = studentId;
        this.street = street;
        this.city = city;
        this.state = state;
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
}
