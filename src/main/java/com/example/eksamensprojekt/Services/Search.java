package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Film;

import java.util.ArrayList;
import java.util.List;

public class Search {
    List<Film> searchedFilm;
    public Search() {

    }


    public List<Film> findFilm(String value)
    {
        List<Film> searchedFilm = new ArrayList<>();
        LoadingFilm lf = new LoadingFilm();
        for(Film film : lf.getFilm()) {
            if(film.getName().contains(value) || (film.getYear()+"").contains(value)){
            searchedFilm.add(film);
            }
            for(String filmGenre: film.getGenre()) {
                if(filmGenre.contains(value)) {

                }
            }
        }
        return searchedFilm;
    }
}
