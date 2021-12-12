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
    String fileMyList = "MyLists.txt";
    List<Series> series = ls.openFile(); //loader serier
    List<Film> film = lf.openFile(); //loader film

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
        nuværende bruger (for username = testml)
        testml har allerede ''Raging Bull''-filmen på sin MyList
        The Godfather og Twin Peaks BØR SLETTES fra MyLists.txt,
        før testen køres!
         */
        login.login("testml", "testml1"); //logger ind som testml - tom MyList

        ml.writeMyListMedie(film.get(3), null); //forsøger at tilføje Raging Bull - film
        ml.writeMyListMedie(film.get(0), null); //tilføjer The Godfather - film
        ml.writeMyListMedie(null, series.get(0)); //tilføjer Twin Peaks - serie
        ml.findLoadListMedie(AListF, AListS); //loader skrevne medier til ArrayListe

        assertEquals("Twin Peaks", (AListS.get(0)).getName()); //tjek serie
        assertEquals("The Godfather", (AListF.get(1)).getName()); //tjek film
    }

    @Test
    void removeMediaFromMyListTest() throws IOException {
        /* Tjek om medier fjernes ordentligt fra MyLists.txt
        når bruger sletter medie (for username = testR)
        */
        login.login("testR" ,"testR1");

        //Medier tilføjes til Mylists.txt
        ml.writeMyListMedie(film.get(3), null);
        ml.writeMyListMedie(null, series.get(0));

        //Nu slettes tilføjede medier
        ml.removeMediaFromMyList(null, series.get(0)); //fjerner Twin Peaks
        ml.removeMediaFromMyList(film.get(3), null); //fjerner Raging Bull

        ml.findLoadListMedie(AListF, AListS);
        assertEquals(0, AListS.size()); //tjekker længde af serie ArrayL
        assertEquals(0, AListF.size()); //tjekker længde af Film ArrayL
    }

    @Test
    void getFilmInfoTest() {
        /* Skal tjekke om metoden indlæser korrekt nødvendig info fra
        specifikt film-objekt. */

        assertEquals("The Godfather: 1972: [Crime, Drama]: 9.2: Film;", ml.getFilmInfo(film.get(0)));
        assertEquals("Raging Bull: 1980: [Biography, Drama, Sport]: 8.2: Film;", ml.getFilmInfo(film.get(3)));
    }

    @Test
    void getSeriesInfoTest() {
        /* Skal tjekke om metoden indlæser korrekt nødvendig info fra
        specifikt serie-objekt. */

        assertEquals("The Sopranos: 1999-2007: [Crime, Drama]: 9.2: Series;", ml.getSeriesInfo(series.get(1)));
        assertEquals("Twin Peaks: 1990-1991: [Crime, Drama, Mystery]: 8.8: Series;", ml.getSeriesInfo(series.get(0)));
    }

    @Test
    void changeLineToMedieTest() throws IOException {
        /* Skal tjekke om metoden ændrer specific linje i MyLists.txt
         til korrekt medie-info.
          Husk at skrive "TestTilFilm;" og "TestTilSerie;" i txt-filen
          før test køres */
        ml.ChangelineToMedie(film.get(0), null, "", "TestTilFilm;", 0);
        ml.ChangelineToMedie(null, series.get(0), "", "TestTilSerie;", 0);

    }

    @Test
    void changeLineGeneralTest() throws IOException {
        /* Skal tjekke om metoden ændrer specific linje i MyLists.txt
         som ønsket.
          Husk at skrive "TestTilString;" i txt-filen før test køres */
        ml.Changeline("TestTilString;", "Method works!");
    }
}