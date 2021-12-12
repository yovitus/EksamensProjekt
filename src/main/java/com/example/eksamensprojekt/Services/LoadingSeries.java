package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Series;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

public class LoadingSeries {
    private Scanner s;
    List<Series> series;

    public LoadingSeries()
    {
        series = new ArrayList<>();
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

    public List<Series> openFile()
    {
        try {
            s = new Scanner(LoadingSeries.class.getResourceAsStream("/com/example/eksamensprojekt/Media/serier.txt"), "UTF-8");
            System.out.println("");
            while (s.hasNextLine()) {
                String oneString = s.nextLine(); //læser hver linje, som 1 serie
                String[] line = oneString.split(" *; *"); //splitter ved ;

                String name = line[0];
                //splitter årstallene
                //endYear indeholder "-" + om der er et årstal bagefter
                String releaseYear = line[1].substring(0, 4);
                int rYear = Integer.parseInt(releaseYear);
                String endYear = line[1].substring(4);
                //Deler genre op i et array
                String[] genre = line[2].split(" *, *");
                //laver String om til float
                float rating = tryParseToFloat(line[3]);
                //deler season op i array og tilføjer episoder til en ArrayList<String>
                String[] seasonsArray = line[4].split(", ");
                String[] episodesReader;
                String[] episodesArray = new String[seasonsArray.length];
                for(int i = 0; i < seasonsArray.length; i++) {
                    episodesReader = seasonsArray[i].split("-");
                    episodesArray[i] = episodesReader[1];
                }


                /*String[] seasonsArray = line[4].split(", ");
                String[] episodesArray;
                ArrayList<String> episodesList = new ArrayList<>();
                for(int i = 0; i < seasonsArray.length; i++) {
                    episodesArray = seasonsArray[i].split("-");
                    episodesList.add(episodesArray[1]);
                }*/

                series.add(new Series(name, rYear, genre, rating, "series", endYear, seasonsArray, episodesArray));
            }
        }

        catch(Exception e) {
            System.out.println("Could not find file");
        }
        return series;
    }

    public void closeFile()
    {
        s.close();
    }

}

