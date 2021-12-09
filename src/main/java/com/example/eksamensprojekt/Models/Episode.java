package com.example.eksamensprojekt.Models;

public class Episode {
    public String[] episodes;

    public Episode(String[] episodes) {
        this.episodes = new String[episodes.length];
    }

    public void addEpisode(String episode) {
        int index = 0;
        episode = episodes[index];
        index++;
    }

    public int getLength() {
        return episodes.length;
    }

}
