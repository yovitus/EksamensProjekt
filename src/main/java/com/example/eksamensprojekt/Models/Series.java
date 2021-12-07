package com.example.eksamensprojekt.Models;

public class Series extends Medier
{
    public String season;
    public Series(String name, int year, String[] genre, float rating, String typeMedia, String season) {
        super(name, year, genre, rating, typeMedia);
        this.season = season;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
