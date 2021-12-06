package com.example.eksamensprojekt.Models;

public class Series extends Medier
{

    public Series(String name, int year, String[] category, float rating, String typeMedia) {
        super(name, year, category, rating, typeMedia);
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
