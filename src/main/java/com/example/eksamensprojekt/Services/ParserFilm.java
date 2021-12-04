package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Film;

import java.util.List;

//Skal dele array op i de forskellige kategorier;
public class ParserFilm
{

    public ParserFilm(List<String[]> filmList)
    {
        for(String[] filmSplit : filmList)
        {
            String name = filmSplit[0];
            int year = Integer.parseInt(filmSplit[1]);
            String[] genre = filmSplit[2].split(", ");
            float rating = Float.parseFloat(filmSplit[3]);
            Film film = new Film(name, year, genre, rating);
        }

    }

    public static void main(String[] args)
    {
        LoadingFilm lf = new LoadingFilm();
        List<String[]> film = lf.openFile();
        ParserFilm pf = new ParserFilm(film);

    }

}
