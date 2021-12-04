package com.example.eksamensprojekt.Models;

public class Signup extends StartPage{
    protected String firstName;
    protected String lastName;
    protected int age;
    protected String email;
    protected String password;
    protected int bankDetails;

    public Signup(String firstName, String lastName, int age, String email, String password, int bankDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.bankDetails = bankDetails;
    }

    /*
    Denne information skal gemmes i klassen 'BrugerData'

    Da alt er tastet ind og man trykker på knappen "Sign up", så videreføres man til siden 'Bruger'
     */



}
