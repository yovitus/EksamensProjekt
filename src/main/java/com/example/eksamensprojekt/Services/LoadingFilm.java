package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Film;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

public class LoadingFilm {
    private Scanner s;
    List<Film> film;

    public LoadingFilm() {

        film = new ArrayList<>();
    }

    //Laver String rating til en float
    public float tryParseToFloat(String value)
    {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE); //local format for Frankrig, måden deres tal er skrevet på
        Number number = null;
        try {
            number = format.parse(value);
        } catch (ParseException e) {
            System.out.println("Fuark brah, didn't float well");
        }
        return number.floatValue();
    }

    //scanner txt-filen for film, og inddeler hver linje til én serie, og splitter til valgte parametrer af klassen Film
    public List<Film> openFile () {
        try {
            s = new Scanner(LoadingFilm.class.getResourceAsStream("/com/example/eksamensprojekt/Media/film.txt"), "UTF-8");
            System.out.println("");
            while (s.hasNextLine()) {
                String oneString = s.nextLine(); //tjekker hver linje, som 1 film
                String[] line = oneString.split(" *; *"); //splitter ved ;
                String name = line[0]; //name er først
                int year = Integer.parseInt(line[1]); //årstal er anden plads
                String[] genre = line[2].split(", "); //Deler genrer op i array, er tredje plads
                float rating = tryParseToFloat(line[3]); //rating er på fjerede plads
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