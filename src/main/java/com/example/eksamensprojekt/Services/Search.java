package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Models.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Search {
    List<Genre> genre;
    public Scanner s;

    public Search() {
        genre = new ArrayList<>();
    }


    public List<Film> getSearchedFilm(String value, List<Film> film) {
        //film med value kommer ud som stream, men med collect så samles det til en liste
        return film
                .stream()
                .filter(e -> e.getName().toLowerCase().contains(value.toLowerCase()) || (e.getYear() + "").equals(value))
                .collect(Collectors.toList());
    }
    /*
    public List<Genre> openGenre() {
        s = new Scanner(Search.class.getResourceAsStream("/com/example/eksamensprojekt/Media/film.txt"), "UTF-8");
        System.out.println("");
        while (s.hasNextLine()) {
            String oneString = s.nextLine(); //tjekker hver linje, som 1 film
            String[] line = oneString.split(" *; *"); //splitter ved ;
            String[] genreName = line[2].split(", ");
            genre.add(new Genre(genreName));
        }
    }
    public List<Film> getSearchedGenre (String value, List<Genre> genre)
        {
           return genre.stream().filter(e -> e.getGenreName().toLowerCase().equals(value.toLowerCase());
        } */
    }

