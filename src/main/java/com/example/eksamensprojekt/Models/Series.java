package com.example.eksamensprojekt.Models;

import java.util.ArrayList;

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

    public ArrayList<String> getEpisodes() {
        return episodes;
    }

    //Har fundet denne metode her:   (ved ikke om det er nødvendigt at kildehenvise her)
    //https://stackoverflow.com/questions/7708698/convert-arrayliststring-to-an-arraylistinteger-or-integer-array
    //11.12.21 - kl. 10:45
    public ArrayList<Integer> getIntegerArrayList(ArrayList<String> stringArrayList) {
        ArrayList<Integer> numberList = new ArrayList<>();

        for (int i = 0; i < stringArrayList.size(); i++) {
            numberList.add(Integer.parseInt(stringArrayList.get(i)));
        }
        return numberList;
    }
}