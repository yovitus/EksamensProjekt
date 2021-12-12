package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Models.Series;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {
    MyList ml  = new MyList();
    ArrayList<Film> AListF = ml.mylistFilm; //tom mylistfilm
    ArrayList<Series> AListS = ml.mylistSeries; //tom mylistseries
    Login login = new Login();
    LoadingSeries ls = new LoadingSeries();
    LoadingFilm lf = new LoadingFilm();

    MyListTest() throws FileNotFoundException {
    }

    @Test
    public void loadMediaTest() throws IOException {
        /* for username = test2. Tjekker om medier bliver loadet
        korrekt til ArrayLister.
         */
        login.login("test2", "test2l"); //logger ind som test2

        /* test2 har følgende medier på sin liste:
        The Wizard Of Oz - film
        The Sound Of Music - film
        The Walking Dead - serie
         */
        ml.findLoadListMedie(AListF, AListS); //load MyList til ArrayLister
        assertEquals("The Wizard Of Oz", (AListF.get(0)).getName()); //tjek film1
        assertEquals("The Sound Of Music", (AListF.get(1)).getName()); //tjek film2
        assertEquals("The Walking Dead", (AListS.get(0)).getName()); //tjek serie
    }

    @Test
    void writeMediaToMyListTest() throws IOException {
        /* Tjek om medier bliver skrevet ind korrekt på MyLists.txt for
        nuværende bruger (for username = test2)
        test2 har allerede ''Raging Bull''-filmen på sin MyList
        The Godfather og Twin Peaks BØR SLETTES fra MyLists.txt,
        før testen køres!
         */
        login.login("testml", "testml1"); //logger ind som testml - tom MyList
        List<Series> series = ls.openFile(); //loader serier
        List<Film> film = lf.openFile(); //loader film

        ml.writeMyListMedie(film.get(3), null); //forsøger at tilføje Raging Bull - film
        ml.writeMyListMedie(film.get(0), null); //tilføjer The Godfather - film
        ml.writeMyListMedie(null, series.get(0)); //tilføjer Twin Peaks - serie
        ml.findLoadListMedie(AListF, AListS); //loader skrevne medier til ArrayListe

        //System.out.println(ml.getFilmInfo((AListF.get(0))));
        //System.out.println(ml.getSeriesInfo(AListS.get(0)));

        assertEquals("Twin Peaks", (AListS.get(0)).getName()); //tjek serie
        assertEquals("The Godfather", (AListF.get(1)).getName()); //tjek film

    }

    //remove
    //getfilminfo
    //getseriesinfo
    //changelinetomedie
    //changeline

}