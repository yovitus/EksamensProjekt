package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Film;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

public class LoadingFilm {
    List<Film> film;

    public LoadingFilm() {

        film = new ArrayList<>();
    }
    public float tryParseToFloat(String value)
    {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number = null;
        try {
            number = format.parse(value);
        } catch (ParseException e) {
            System.out.println("Fuark brah, didn't float well");
        }
        return number.floatValue();
    }

    public List<Film> openFile () {
        try {
            Scanner s = new Scanner(LoadingFilm.class.getResourceAsStream("/com/example/eksamensprojekt/Media/film.txt"), "UTF-8");
            System.out.println("");
            while (s.hasNextLine()) {
                String oneString = s.nextLine(); //tjekker hver linje, som 1 film
                String[] line = oneString.split(" *; *"); //splitter ved ;
                String name = line[0];
                int year = Integer.parseInt(line[1]);
                String[] genre = line[2].split(", ");
                float rating = tryParseToFloat(line[3]);
                film.add(new Film(name, year, genre, rating, "film"));
            }
        }
        catch (Exception e)
        {
            System.out.println("Could not find file");
        }
        return film;
    }
    public List<Film> getFilm() {
        return film;
    }
}