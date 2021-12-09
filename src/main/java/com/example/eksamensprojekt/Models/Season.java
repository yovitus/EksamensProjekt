package com.example.eksamensprojekt.Models;


public class Season
{
    public String[] seasons;

    public Season(String[] seasons) {
        this.seasons = new String[seasons.length];
    }

    public void addSeason(String season) {
        int index = 0;
        season = seasons[index];
        index++;
    }

    public int getLength() {
        return seasons.length;
    }

}
