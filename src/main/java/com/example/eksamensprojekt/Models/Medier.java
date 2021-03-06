package com.example.eksamensprojekt.Models;

import java.util.Arrays;

public abstract class Medier {
    protected String name;
    protected int year;
    protected String[] genre;
    protected float rating;
    protected String typeMedia;

    public Medier(String name, int year, String[] genre, float rating, String typeMedia)
    {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        this.typeMedia = typeMedia;
    }

    //henter thumbnail/plakat for enten Series eller Film
    public String getImage() {
        if(typeMedia == "film")
        {
            return "/com/example/eksamensprojekt/Images/filmplakater/" + name + ".jpg";
        } else if(typeMedia == "series") {
            return "/com/example/eksamensprojekt/Images/serieforsider/" + name + ".jpg";
        }
        System.out.println("du har gjort noget forkert");
        return "";
    }

    public String genreToString()
    {
        return Arrays.toString(genre);
    }

    public String getName() { return name; }

    public int getYear() { return year; }

    public String[] getGenre() { return genre; }

    public float getRating() { return rating; }

}
