package com.example.eksamensprojekt.Models;

public class Series extends Medier
{
    public String endYear;
    public Season[] seasons;
    public Episode[] episodes;

    public Series(String name, int year, String[] genre, float rating, String typeMedia, String endYear, Season[] seasons, Episode[] episodes) {
        super(name, year, genre, rating, typeMedia);
        this.endYear = endYear;
        this.seasons = seasons;
        this.episodes = episodes;
    }

    public String getEndYear() {
        if(endYear.equals("")) {
            return "";
        }else {
            return "" + endYear;
        }
    }
/*
    public int getSeason() {
        return seasons.length();
    }

 */


}
