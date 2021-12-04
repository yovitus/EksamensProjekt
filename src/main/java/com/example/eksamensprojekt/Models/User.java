package com.example.eksamensprojekt.Models;

public class User {

    protected String firstName;
    protected String lastName;
    protected int age;
    protected String email;
    public String username;
    public String password;
    protected int bankDetails;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, int age, String email, String username, String password, int bankDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
        this.bankDetails = bankDetails;

    }
}
