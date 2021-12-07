package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Film;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Search {


    public List<Film> getSearchedFilm(String value, List<Film> film) {
        //film med value kommer ud som stream, men med collect så samles det til en liste
       return film
               .stream()
               .filter(e -> e.getName().toLowerCase().contains(value.toLowerCase())  || (e.getYear()+"").equals(value))
               .collect(Collectors.toList());
    }
}
