package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.User;

import java.io.*;
import java.util.Scanner;


public class Login  {

    String nameFile = "Signup.txt"; //txt-fil med user ID (username, password)
    BufferedWriter bufferedWriter;
    FileWriter fileWriter;
    public User user;
    public boolean accepted = false;


    //tjekker om oplysninger (brugernavn og password) er korrekt, hvis  akeUser er lavet, eller sender den en exception
    public Login() {

    }

    public User login(String username, String password) {
        String tmpUser = "";
        String tmpPswd = "";
        boolean found = false; //når id er fundet, stopper den med at søge
        try {
            Scanner sc = new Scanner(new File(nameFile)); //Scanner der skal scanne txt-filen
            sc.useDelimiter("[,\n]"); //Læser noget før komma, efter komma ved den det er noget nyt, så læser den på næste linje
             //Hvis login stemmer overens med det nognes brugerID i txt-filen, ændres den til true
            while (!found && sc.hasNext()) {
                //tjekker efter ID i txt-filen
                tmpUser = sc.next();
                tmpPswd = sc.next();
                //trim så hvis de skriver mellemrum, så fjerner den det
                if (tmpUser.equals(username.trim()) && tmpPswd.equals(password.trim())) {
                    accepted = true;
                    sc.close();
                    user = new User(tmpUser.trim(), tmpPswd.trim());
                    break;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void makeProfile (String username, String password)
    {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(new File(nameFile), true)); //den overskriver ikke i txt filen hvis true
            if(!username.equals("") && !password.equals(""))
            bufferedWriter.write(username +"," +password + "\n"); //tilføjer username og password til txt-filen
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Could not store data");
        }

    }

}

