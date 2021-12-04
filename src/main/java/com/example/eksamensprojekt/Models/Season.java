package com.example.eksamensprojekt.Models;

public class Season extends Series
{
    public Season(String name, int year, String[] genre, double rating) {


        super(name, year, genre, rating);
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
