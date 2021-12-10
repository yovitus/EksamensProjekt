package com.example.eksamensprojekt.Models;

import java.util.ArrayList;
import java.util.Arrays;

public class Series extends Medier
{
    public String endYear;
    public String[] seasons;
    public ArrayList<String> episodes;

    public Series(String name, int year, String[] genre, float rating, String typeMedia, String endYear,
                  String[] seasons, ArrayList<String> episodes) {
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


    public int getSeasonLength() {
        return seasons.length;
    }

    public ArrayList<String> getEpisodeLength() {
        return episodes;
    }

    /*
    public String getEpisodeLength() {
        String output = "";
        for(int i = 0; i < seasons[i].length; i++) {
            output = Arrays.toString(seasons[i]) + "episodes";
        }
        return output;
    }

    public int getEpisodeLength() {
        return episodes.length;
    }
     */


}
