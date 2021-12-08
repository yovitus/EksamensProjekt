package com.example.eksamensprojekt.Models;

public class Series extends Medier
{
    public String[] season;
    public String endYear;

    public Series(String name, int year, String[] genre, float rating, String typeMedia, String[] season, String endYear) {
        super(name, year, genre, rating, typeMedia);
        this.season = season;
        this.endYear = endYear;
    }

    public String getEndYear() {
        if(endYear.equals("")) {
            return "";
        }else {
            return "" + endYear;
        }

    }

}
