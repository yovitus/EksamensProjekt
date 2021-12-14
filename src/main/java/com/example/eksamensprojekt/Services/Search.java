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

   /*public List<Genre> openGenre() {
        try {
            s = new Scanner(Search.class.getResourceAsStream("/com/example/eksamensprojekt/Media/film.txt"), "UTF-8");
            System.out.println("");
            while (s.hasNextLine()) {
                String oneString = s.nextLine(); //tjekker hver linje, som 1 film
                String[] line = oneString.split(" *; *"); //splitter ved ;
                String[] genreName = line[2].toLowerCase().split(", ");
                genre.add(new Genre(genreName));
            }
        }   catch (Exception e)
            {
                System.out.println("Could not find file");
            }
        return genre;
    }*/
   public List<Film> getSearchedFilmGenre(String value, List<Film> film) {
       //film med value kommer ud som stream, men med collect så samles det til en liste
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




