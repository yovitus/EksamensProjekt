package com.example.eksamensprojekt.Models;

public class Medier {
    String name;
    int year;
    String[] genre;
    double rating;

    public Medier(String name, int year, String[] genre, double rating)
    {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }
}
