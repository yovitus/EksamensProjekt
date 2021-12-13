package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Models.Genre;
import com.example.eksamensprojekt.Models.Series;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest2 {

    ArrayList<Film> film;
    ArrayList<Series> series;
    LoadingFilm lf= new LoadingFilm();
    LoadingSeries ls= new LoadingSeries();
    MyList ml  = new MyList();
    Search se = new Search();
    List<Film> lFilm = lf.openFile();
    List<Series> lSeries= ls.openFile();


    public SearchTest2() throws FileNotFoundException {
    }


    //Test for search only
    @Test
    public void testGetSearchedFilm() {
/*Søger igennem film-liste for at finde match, og
            putter match ind i ny liste 'value' */
        List<Film> value= se.getSearchedFilm("Raging Bull", lFilm);
        /*Vi anvender en metode fra MyList.java (som er blevet testet i
         * MyListTest.java), for at tjekke, om navnet på den tilføjede film
         * matcher det, som det skal være */
        System.out.println(ml.getFilmInfo(value.get(0)));

        assertTrue(ml.getFilmInfo(value.get(0)).contains("Raging Bull"));

    }


    @Test
    public void testGetSearchedSeries() {

        /*Søger igennem serie-liste for at finde match, og
            putter match ind i ny liste 'value' */
        var value = se.getSearchedSeries("Twin Peaks", lSeries);

        /*Vi anvender en metode fra MyList.java (som er blevet testet i
         * MyListTest.java), for at tjekke, om navnet på den tilføjede film
         * matcher det, som det skal være */
        System.out.println(ml.getSeriesInfo(value.get(0)));

        assertTrue(ml.getSeriesInfo(value.get(0)).contains("Twin Peaks"));


    }

        //Test for searched genre only
        @Test
        public void testGetSearchedFilmGenre () {

        /*Søger gennem genrene for film, finder et match og sætter
         den i en ny liste "value"*/
            var value = se.getSearchedFilmGenre("Action", lFilm);

            /*Der tjekkes gennem listen for alle genre og der udvælges
             dem der matcher den genre der søges efter. Der bliver samt
             lavet en System.out.print for at tjekke om vi får det vi skal*/

            for (int i = 0; i < value.size(); i++) {
                System.out.println(ml.getFilmInfo(value.get(i)));
                assertTrue(ml.getFilmInfo(value.get(i)).contains("Action"));
            }

        }

        @Test
        public void testGetSearchedSeriesGenre ()
        {
            /*Søger gennem genrene for serier, finder et match og sætter
         den i en ny liste "value"*/
            var value = se.getSearchedSeriesGenre("Comedy", lSeries);

             /*Der tjekkes gennem listen for alle genre og der udvælges
             dem der matcher den genre der søges efter. Der bliver samt
             lavet en System.out.print for at tjekke om vi får det vi skal*/

            for (int i = 0; i < value.size(); i++) {
                System.out.println(ml.getSeriesInfo(value.get(i)));
                assertTrue(ml.getSeriesInfo(value.get(i)).contains("Comedy"));

            }
        }
    }



