package com.example.eksamensprojekt.Models;


public class Season extends Series
{
    //public String episode;
    public Integer[] episodes;

    public Season(String name, int year, String[] genre, float rating, String typeMedia, String[] season, String endYear,
                  Integer[] episodes) {
        super(name, year, genre, rating, typeMedia, season, endYear);
        //this.episode = episode;
        this.episodes = episodes;
    }
/*
    public void addEpisode(Integer episode) {
        int index = 0;
        episode = episodes[index];
        index++;
    }

 */
}
