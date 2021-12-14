package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Models.Series;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest2 {

        ArrayList<Film> film;
        ArrayList<Series> series;
        LoadingFilm lf = new LoadingFilm();
        LoadingSeries ls = new LoadingSeries();
        MyList ml = new MyList();
        List<Film> lFilm = lf.openFile(); //loader liste af alle film
        List<Series> lSeries = ls.openFile();

    public SearchTest2() throws FileNotFoundException {
    }

    //Test for search only
        @Test
        public void testGetSearchedFilm() {
            Search se = new Search();
            /*Søger igennem film-liste for at finde match, og
            putter match ind i ny liste 'value' */
            List<Film> value = se.getSearchedFilm("Raging Bull", lFilm);

            /*Vi anvender en metode fra MyList.java (som er blevet testet i
            * MyListTest.java), for at tjekke, om navnet på den tilføjede film
            * matcher det, som det skal være */
            System.out.println(ml.getFilmInfo(value.get(0)));
            assertTrue(ml.getFilmInfo(value.get(0)).contains("Raging Bull"));


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
            var value = se.getSearchedFilmGenre("Action", lFilm);

            for(int i = 0; i < value.size(); i++) {
                //System.out.println(ml.getFilmInfo(value.get(i)));
                assertTrue((ml.getFilmInfo(value.get(i)).contains("Action")));
            }
        }

        @Test
        public void testGetSearchedSeriesGenre ()
        {
            Search se = new Search();
            var value = se.getSearchedSeriesGenre("Comedy", series);
            assertTrue(Arrays.stream(value.get(0).getGenre()).anyMatch("Comedy"::equals));
        }
    }


