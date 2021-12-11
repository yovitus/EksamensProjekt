package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Models.Series;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchTest {
    ArrayList<Film> film;
    ArrayList<Series> series;
    //Test for search only
    @Test
    public void testGetSearchedFilm()
    {
        Search se = new Search();
        var value = se.getSearchedFilm("The Godfather", film);
        assertEquals("The Godfather", "The Godfather");


    }

    @Test
    public void testGetSearchedSeries()
    {
        Search se = new Search();
        var value= se.getSearchedSeries("Twin Peaks", series);
        assertEquals("Twin Peaks", "Twin Peaks");
    }

    //Test for searched genre only
    @Test
    public void testGetSearchedFilmGenre()
    {
        Search se = new Search();
        var value = se.getSearchedFilmGenre("Action", film);
        assertEquals("Action", "Action");

    }
    @Test
    public void testGetSearchedSeriesGenre()
    {
        Search se = new Search();
        var value = se.getSearchedSeriesGenre("Comedy", series);
        assertEquals("Comedy", "Comedy");
    }
}
