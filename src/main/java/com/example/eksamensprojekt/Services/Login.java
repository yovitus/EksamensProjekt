package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.User;

import java.io.*;
import java.util.Scanner;


public class Login  {

    String nameFile = "Signup.txt"; //txt-fil med user ID (username, password)
    FileWriter fileWriter;
    public User user;


    //tjekker om oplysninger (brugernavn og password) er korrekt, hvis signup er lavet, eller sender den en exception
    public Login() {

    }

    public User login(String username, String password) {
        boolean found = true; //når id er fundet, stopper den med at søge
        String tmpUser = "";
        String tmpPswd = "";
        try {
            Scanner sc = new Scanner(new File(nameFile)); //Scanner der skal scanne txt-filen
            sc.useDelimiter("[,\n]"); //Læser noget før komma, efter komma ved den det er noget nyt, så læser den på næste linje
             //Hvis login stemmer overens med det nognes brugerID i txt-filen, ændres den til true
            while (!found && sc.hasNext()) {
                //tjekker efter ID i txt-filen
                tmpUser = sc.next();
                tmpPswd = sc.next();
                //trim så hvis de skriver mellemrum, så fjerner den det
                if (tmpUser.trim().equals(username.trim()) && tmpPswd.trim().equals(password.trim())) {
                    accepted = true;
                    break;
                }
                sc.close();
                User user = new User(tmpUser.trim(), tmpPswd.trim());
                return user;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void makeProfile (String username, String password)
    {
        try {
            File file = new File(nameFile);
            FileWriter fileWriter = new FileWriter(new File(nameFile), true); //den overskriver ikke i txt filen hvis true
            fileWriter.write(signUp(username, password)); //tilføjer username og password til txt-filen
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("ID not accepted");
        }

    }

}

