package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Series;

import java.util.*;

public class LoadingSeries {
    List<Series> series;
    LoadingFilm lf = new LoadingFilm();

    public LoadingSeries()
    {
        series = new ArrayList<>();
    }

    //scanner txt-filen for series, og inddeler hver linje til én serie, og splitter til valgte parametrer af klassen Series
    public List<Series> openFile()
    {
        try {
            Scanner s = new Scanner(LoadingSeries.class.getResourceAsStream("/com/example/eksamensprojekt/Media/serier.txt"), "UTF-8");
            System.out.println("");
            while (s.hasNextLine()) {
                String oneString = s.nextLine(); //læser hver linje, som 1 serie
                String[] line = oneString.split(" *; *"); //splitter ved ;

                String name = line[0]; //name er første plads
                //splitter årstallene
                //endYear indeholder "-" + om der er et årstal bagefter
                String releaseYear = line[1].substring(0, 4);
                int rYear = Integer.parseInt(releaseYear);
                String endYear = line[1].substring(4);
                String[] genre = line[2].split(" *, *"); //Deler genre op i et array, på tredje plads
                float rating = lf.tryParseToFloat(line[3]); //rating er på fjerde plads, kalder på metode i LoadingFilm

                //deler season op i array og tilføjer episoder til en ArrayList<String>
                String[] seasonsArray = line[4].split(", "); //antal sæsoner
                String[] episodesReader; //tom array
                String[] episodesArray = new String[seasonsArray.length]; //længde af antal sæsoner
                for(int i = 0; i < seasonsArray.length; i++) {
                    episodesReader = seasonsArray[i].split("-"); //får [s, e] osv.
                    episodesArray[i] = episodesReader[1]; //får hver s' antal ep gemt

                }
                series.add(new Series(name, rYear, genre, rating, "series", endYear, seasonsArray, episodesArray));
            }
        }
        catch(Exception e) {
            System.out.println("Could not find file");
        }
        return series;
    }
}

