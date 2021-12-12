package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Film;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoadingFilmTest {
    LoadingFilm lf = new LoadingFilm();

    @Test
    void parseToFloatTest() {
        /*Tjekker om det rigtige float-tal bliver
        skrevet */
        assertEquals(3.14f, lf.tryParseToFloat("3,14"));
        assertEquals(5f, lf.tryParseToFloat("5,0"));
    }

    @Test
    void openFileTest() {
        /* Tjekker om film loades korrekt til ArrayListe fra
        film.txt */
        List<Film> film = lf.openFile();

        //Tjek "The Godfather"
        Film f = film.get(0);
        assertEquals("The Godfather", f.getName());
        assertEquals(1972, f.getYear());
        assertEquals("[Crime, Drama]", f.genreToString());
        assertEquals(9.2f, f.getRating());
    }
}