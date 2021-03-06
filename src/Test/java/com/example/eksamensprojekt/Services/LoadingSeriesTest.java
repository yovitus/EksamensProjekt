package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Series;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LoadingSeriesTest {
    private LoadingSeries ls = new LoadingSeries();

    @Test
    void testTryParseToFloat() {
        //tjekker om metoden fungere som den skal, hvor den genererer en string om til float
        LoadingFilm lf = new LoadingFilm();
        assertEquals(8.3, lf.tryParseToFloat("8.3"));
    }

    @Test
    void testOpenFile() {
        ls.series = ls.openFile();
        Series liste = ls.series.get(5);
        assertNotNull(liste);
    }

    @Test
    //Tester om indlæste navn, er det samme som vi forventer
    void testName() {
        ls.series = ls.openFile();
        Series liste = ls.series.get(0);
        String nameTest = liste.getName();
        String name = "Twin Peaks";
        String name2 = "The Sopranos";
        assertEquals(name, nameTest);
    }

    @Test
    //Tester om indlæste year, er det samme som vi forventer
    void testYear() {
        ls.series = ls.openFile();
        Series liste = ls.series.get(1);
        int yearTest = liste.getYear();
        int year1 = 1990;
        int year2 = 1999;
        assertEquals(year1, yearTest);
    }

    @Test
    //Tester om indlæste year, er det samme som vi forventer
    void testGenre() {
        ls.series = ls.openFile();
        Series liste = ls.series.get(0);
        String[] genreTest = liste.getGenre();
        String genreStringTest = Arrays.toString(genreTest);

        String genre1 = "[Crime, Drama, Mystery]";
        String genre2 = "[Crime, Drama]";
        assertEquals(genre1, genreStringTest);
    }

    @Test
    //Tester om indlæste rating, er det samme som vi forventer
    void testRating() {
        ls.series = ls.openFile();
        Series liste = ls.series.get(1);
        float ratingTest = liste.getRating();
        float rating1 = 8.8f;
        float rating2 = 9.2f;
        assertEquals(rating1, ratingTest);
    }

    @Test
    //Tester om indlæste path på billedet, er det samme som vi forventer
    void testImage() {
        ls.series = ls.openFile();
        Series liste = ls.series.get(0);
        String imageTest = liste.getImage();
        String image1 = "/com/example/eksamensprojekt/Images/serieforsider/Twin Peaks.jpg";
        String image2 = "/com/example/eksamensprojekt/Images/serieforsider/The Sopranos.jpg";
        assertEquals(image1, imageTest);
    }

    @Test
    //Tester om indlæste endYear, er det samme som vi forventer
    void testEndYear() {
        ls.series = ls.openFile();
        Series liste = ls.series.get(0);
        String endYearTest = liste.getEndYear();
        String endYear1 = "-1991";
        String endYear2 = "-2007";
        assertEquals(endYear1, endYearTest);
    }

    @Test
    //Tester om indlæste season, er det samme som vi forventer
    void testSeason() {
        ls.series = ls.openFile();
        Series liste = ls.series.get(0);
        String[] seasonTest = liste.getSeasons();
        String seasonStringTest = Arrays.toString(seasonTest);

        String season1 = "[1-8, 2-22]";
        String season2 = "[1-13, 2-13, 3-13, 4-13, 5-13, 6-21]";
        assertEquals(season1, seasonStringTest);
    }

    @Test
    //Tester om indlæste episode, er det samme som vi forventer
    void testEpisode() {
        ls.series = ls.openFile();
        Series liste = ls.series.get(0);
        String[] episodeTest = liste.getEpisodes();
        String episodeStringTest = Arrays.toString(episodeTest);

        String episode1 = "[8, 22]";
        String episode2 = "[13, 13, 13, 13, 13, 21]";
        assertEquals(episode1, episodeStringTest);
    }
}