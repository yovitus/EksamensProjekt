package com.example.eksamensprojekt.Models;

import java.util.ArrayList;

public class Series extends Medier {
    public String endYear;
    public String[] seasons;
    public String[] episodes;

    public Series(String name, int year, String[] genre, float rating, String typeMedia, String endYear,
                  String[] seasons, String[] episodes) {
        super(name, year, genre, rating, typeMedia);
        this.endYear = endYear;
        this.seasons = seasons;
        this.episodes = episodes;
    }

    public String getEndYear() {
        if (endYear.equals("")) {
            return "";
        } else {
            return "" + endYear;
        }
    }

    public String[] getSeasons() {return seasons;}
    public int getSeasonLength() {
        return seasons.length;
    }

    public String[] getEpisodes() {
        return episodes;
    }




    //https://stackoverflow.com/questions/18838781/converting-string-array-to-an-integer-array
    //11.12.21 kl. 15:50
    //burde adde try-catch
    public Integer[] getIntegerArray(String[] stringArray) {
        Integer[] intArray = new Integer[stringArray.length];
        for(int i = 0; i < stringArray.length ; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        return intArray;
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