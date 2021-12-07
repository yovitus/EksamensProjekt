package com.example.eksamensprojekt.Models;

public class Season extends Series
{
    public String episode;
    public Season(String name, int year, String[] genre, float rating, String typeMedia, String season, String episode) {
        super(name, year, genre, rating, typeMedia, season);
        this.episode = episode;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
