package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Series;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {
    MyList ml  = new MyList();
    ArrayList AListF = ml.mylistFilm; //tom mylistfilm
    ArrayList<Series> AListS = ml.mylistSeries; //tom mylistseries

    MyListTest() throws FileNotFoundException {
    }

    @Test
    public void loadFilmAndSeriesTest() throws IOException {
        /* for username = test2. Tjekker om medier bliver loadet
        korrekt til ArrayLister.
         */
        Login login = new Login();
        login.login("test2", "test2l"); //logger ind som test2
        /* test2 har følgende medier på sin liste:
        The Wizard Of Oz - film
        The Sound Of Music - film
        The Walking Dead - serie
         */
        ml.findLoadListMedie(AListF, AListS);
    }

    @Test
    void writeMedieToMyListTest() {

    }

    //remove
    //getfilminfo
    //getseriesinfo
    //changelinetomedie
    //changeline

}