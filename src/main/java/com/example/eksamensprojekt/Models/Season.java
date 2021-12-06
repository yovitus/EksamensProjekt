package com.example.eksamensprojekt.Models;

public class Season extends Series
{
    public Season(String name, int year, String[] genre, float rating, String typeMedia) {


        super(name, year, genre, rating, typeMedia);
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
