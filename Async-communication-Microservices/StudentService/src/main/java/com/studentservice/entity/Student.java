package com.studentservice.entity;



public class Student {
    private String id;
    private String name;
    private Address address;

    // Constructors, getters, and setters

    public Student(String id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

