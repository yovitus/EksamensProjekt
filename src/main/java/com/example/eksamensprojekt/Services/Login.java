package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
            sc.useDelimiter(",\n"); //Hvis ikke der rykkes ned på næste linje, splitter den ved komma
            boolean accepted = false; //Hvis login stemmer overens med det nognes brugerID i txt-filen, ændres den til true
            while (found && sc.hasNext()) {
                //tjekker efter ID i txt-filen
                tmpUser = sc.next();
                tmpPswd = sc.next();

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

    public String signUp(String username, String password)
    {

        return username + "," + password;
    }

    public void makeProfile (String username, String password)
    {
        try {
            File file = new File(nameFile);
            FileWriter fileWriter = new FileWriter(Login.class.getResource(nameFile), true); //den overskriver ikke i txt filen hvis true
            fileWriter.write(signUp(username, password)); //tilføjer username og password til txt-filen
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("ID not accepted");
        }

    }

}

