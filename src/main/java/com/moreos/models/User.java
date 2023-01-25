package com.moreos.models;

public class User {
    String firstname;
    String lastname;
    Double weight;

    public User(String firstname, String lastname, Double weight) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.weight = weight;
    }

    public User() {
        this.firstname = "null";
        this.lastname = "null";
        this.weight = 0.0;
    }

    public Double eatMac(Double value) {
        this.weight += value;
        return weight;
    }

    @Override
    public String toString() {
        return "{ firstname = '" + firstname +
                "', lastname = '" + lastname +
                "', weight = '" + weight + "' }";
    }
}
