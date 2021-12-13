package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Models.Series;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest2 {

        ArrayList<Film> film;
        ArrayList<Series> series;

        //Test for search only
        @Test
        public void testGetSearchedFilm() {
            Search se = new Search();
            var value = se.getSearchedFilm("Raging Bull", film);
            //assertTrue(value.get(0).getName().contains("Raging Bull"));

            boolean passes=false;
            if(value.contains("Raging Bull")){
                passes=true;
            }
            assertTrue(passes);

      /*  boolean passes = false;
        for (Film f : film) {
            if (value.get(1).getName().contains("Godfather")) {
                passes = true;
            }

            assertTrue(passes);
        }*/
        }


        @Test
        public void testGetSearchedSeries() {
            Search se = new Search();
            var value = se.getSearchedSeries("Twin Peaks", series);
            //assertTrue(value.get(0).getName().contains("Twin Peaks"));

            boolean passes=false;
            if(value.contains("Twin Peaks")){
                passes=true;
            }
            assertTrue(passes);

      /*  boolean passes = false;
        for (Series s : series) {
            if (value.get(1).getName().contains("Twin Peaks")) {
                passes = true;
            }

            assertTrue(passes);
        }*/
        }

        //Test for searched genre only
        @Test
        public void testGetSearchedFilmGenre ()
        {
            Search se = new Search();
            var value = se.getSearchedFilmGenre("Action", film);
            assertTrue(Arrays.stream(value.get(0).getGenre()).anyMatch("Action"::equals));

        }
        @Test
        public void testGetSearchedSeriesGenre ()
        {
            Search se = new Search();
            var value = se.getSearchedSeriesGenre("Comedy", series);
            assertTrue(Arrays.stream(value.get(0).getGenre()).anyMatch("Comedy"::equals));
        }
    }


