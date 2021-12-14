package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Models.Series;

import java.util.*;
import java.util.stream.Collectors;

public class Search {

    public Scanner s;

    public List<Film> getSearchedFilm(String value, List<Film> film) {
        //film med value kommer ud som stream, men med collect så samles det til en liste
        if(value.equals(""))
            return film;
        return film
                .stream()
                .filter(e -> e.getName().toLowerCase().contains(value.toLowerCase()) || (e.getYear() + "").equals(value))
                .collect(Collectors.toList());
    }

    public List<Series> getSearchedSeries(String value, List<Series> series) {
        if(value.equals(""))
            return series;
        return series
                .stream()
                .filter(e -> e.getName().toLowerCase().contains(value.toLowerCase()) || (e.getYear() + "").equals(value))
                .collect(Collectors.toList());
    }

   public List<Film> getSearchedFilmGenre(String value, List<Film> film) {
       //film med value kommer ud som stream, men med collect så samles det til en liste
       System.out.println("check "+value+"check");
       if(value.isEmpty() || value.equals("All") || value.equals("")) return film;
       var genreListe = film.stream()
               .filter(f -> Arrays.stream(f.getGenre()).anyMatch(value::equals)).collect(Collectors.toList());
       System.out.println(genreListe);
       return genreListe;
   }

   public List<String> getAllGenreFilm(List<Film> film) {
       HashSet<String> oneTypeGenre = new HashSet<>();
       for (Film f : film) {
           for(String s : f.getGenre())
           {
               oneTypeGenre.add(s);
           }
       }
       List<String> list = new ArrayList<>(oneTypeGenre);
       List<String> sortedList = list.stream().sorted().collect(Collectors.toList());
       sortedList.add(0,"All");
       System.out.println("Movies genre list:" + sortedList);
       return sortedList;
   }

    public List<Series> getSearchedSeriesGenre(String value, List<Series> series) {
        //film med value kommer ud som stream, men med collect så samles det til en liste
        System.out.println("check "+value+"check");
        if(value.isEmpty() || value.equals("All") || value.equals("")) return series;
        var genreListe = series.stream()
                .filter(f -> Arrays.stream(f.getGenre()).anyMatch(value::equals)).collect(Collectors.toList());
        System.out.println(genreListe);
        return genreListe;
    }

    public List<String> getAllGenreSeries(List<Series> series) {
        HashSet<String> oneTypeGenre = new HashSet<>();
        for (Series s : series) {
            for (String st : s.getGenre()) {
                oneTypeGenre.add(st);
            }
        }
            List<String> list = new ArrayList<>(oneTypeGenre);
            List<String> sortedList = list.stream().sorted().collect(Collectors.toList());
            sortedList.add(0, "All");
            System.out.println("TV Shows genre list" + sortedList);
            return sortedList;
    }
}




