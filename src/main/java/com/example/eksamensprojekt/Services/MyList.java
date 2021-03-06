package com.example.eksamensprojekt.Services;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Models.Series;

import java.util.ArrayList;

public class MyList {
    BufferedWriter bufferedWriter;
    public ArrayList<Film> mylistFilm;
    public ArrayList<Series> mylistSeries;
    String fileMyList = "MyLists.txt";
    String cUsernameList = "CurrentUsername.txt"; //current username txt-fil
    private Scanner s;
    String currentUsername;
    int counter;
    int counterD;

    public MyList() {
            mylistFilm = new ArrayList<>();
            mylistSeries = new ArrayList<>();
            counter = 0;
            counterD = 0;

        try {
            //scan CurrentUsername.txt og indlæs username for nuværende bruger
            s = new Scanner(new File(cUsernameList));
            this.currentUsername = s.nextLine();
        } catch (FileNotFoundException e){
            System.out.println("The file could not be found!");
        }
    }

    //Indlæs MyList film og serier for nuværende bruger til arrayL
    public void findLoadListMedie(ArrayList<Film> AListF, ArrayList<Series> AListS) {
        try{
        s = new Scanner(new File(fileMyList)); //Scanner der skal scanne txt-filen, "MyLists.txt
        s.useDelimiter("[;\n]"); //efter ; skift linje
        counterD = 0;


        //Loader specifik mylistFilm for bruger
        while (s.hasNext()) {
            if(s.nextLine().equals(currentUsername + ";")) {
                //System.out.println(("username found!"));
                while (s.hasNext()){
                    String nextRead = s.nextLine();
                    if(nextRead.equals("Stop;")){
                        //System.out.println("End of list reached!");
                        if(counterD == 0 || counter == 0){
                            //System.out.println("List is empty!");
                        } break;
                     } else if (nextRead.equals("Deleted;")){
                            counterD++;
                    }else if (!nextRead.equals("Deleted;") && !nextRead.contains("Series;")) {
                        String oneString = nextRead; //tjekker hver linje
                        String[] line = oneString.split(" *: *"); //splitter ved :
                        String name = line[0];
                        int year = Integer.parseInt(line[1]);
                        String genre1 = line[2].replace("[", ""); //fjerner [
                        String genre2 = genre1.replace("]", ""); //fjerner ]
                        String[] genre = genre2.split(", ");
                        float rating = Float.parseFloat(line[3]);
                        AListF.add(new Film(name, year, genre, rating, "film")); //tilføj elementer til arrayliste fra txt-fil
                        //System.out.println("One element has been loaded to FilmList!");
                        counter++;
                     } else if (!nextRead.equals("Deleted;") && !nextRead.contains("Film;")){
                        String oneString = nextRead;
                        String[] line = oneString.split(" *: *"); //splitter ved ;
                        String name = line[0];
                        String releaseYear = line[1].substring(0, 4);
                        int rYear = Integer.parseInt(releaseYear);
                        String endYear = line[1].substring(4);
                        //Deler genre op i et array
                        String genre1 = line[2].replace("[", ""); //fjerner [
                        String genre2 = genre1.replace("]", ""); //fjerner ]
                        String[] genre = genre2.split(", ");
                        //laver String om til float
                        float rating = Float.parseFloat(line[3]);

                        //deler season op i array og tilføjer episoder til en ArrayList<String>
                        String[] seasonsArray = line[4].split(", "); //antal sæsoner
                        String[] episodesReader; //tom array
                        String[] episodesArray = new String[seasonsArray.length]; //længde af antal sæsoner
                        for(int i = 0; i < seasonsArray.length; i++) {
                            episodesReader = seasonsArray[i].split("-"); //får [s, e] osv.
                            episodesArray[i] = episodesReader[1]; //får hver s' antal ep gemt
                        }
                        AListS.add(new Series(name, rYear, genre, rating, "series", endYear, seasonsArray, episodesArray));
                        //System.out.println("One element has been loaded to SeriesList!");
                        counter++;
                    }
                }
                break;
            }}} catch (IOException e){
        System.out.println("List could not be loaded!");
        e.printStackTrace();
    }}


    //Skriver nye Film ind på .txt filen
    public void writeMyListMedie(Film film, Series series) throws IOException {
        try{
        Scanner sc = new Scanner(new File(fileMyList)); //Scanner der skal scanne txt-filen
        sc.useDelimiter("[;\n]"); //ny linje
        bufferedWriter = new BufferedWriter(new FileWriter(new File(fileMyList), true));
        boolean found = false;
        boolean written = false;
        counter = 0;

        //finder mylist tilhørende nuværende bruger
        while (!found) {
            String lineRead = sc.nextLine();
            if(lineRead.equals(currentUsername + ";")) {
                //System.out.println("username match found!");
                found = true;
            } else {
                counter++;
            }}

        //tjekker om medie allerede er på brugerens liste
        boolean match = false;
        while (found && !match){
            String lineNewStart = Files.readAllLines(Paths.get(fileMyList)).get(counter);
            if(!lineNewStart.equals("Stop;")){
                counter++;
                if(film != null){
                if(lineNewStart.equals(getFilmInfo(film))) {
                    //System.out.println("Movie already on My List!");
                    match = true;
                }} else if (series != null){
                    if(lineNewStart.equals(getSeriesInfo((series)))){
                        //System.out.println("TV Show already on My List!");
                        match = true;
                    }
                }} else if (lineNewStart.equals("Stop;")){ //medie er IKKE allerede på listen
                //System.out.println("That media is currently NOT on My List!");
                break;
            }}

        //hvis match ikke fundet på liste, så tilføj til listen
        while(!written && found && !match){
            String lineNewStart = Files.readAllLines(Paths.get(fileMyList)).get(counter);
            if(film != null){ //hvis det er FILM, som tilføjes
            if(lineNewStart.equals("Deleted;")){
                ChangelineToMedie(film, null,"", "Deleted;", counter);
                bufferedWriter.close();
                written = true;
            } else if (lineNewStart.equals("Stop;")){
                ChangelineToMedie(film, null,"\n" + "Stop;", "Stop;", counter);
                bufferedWriter.close();
                written = true;
            } else {
                counter++;
            }} else if (series != null){ //hvis det er SERIE, som tilføjes
                if(lineNewStart.equals("Stop;")){
                    ChangelineToMedie(null, series,"\n" + "Stop;", "Stop;", counter);
                    bufferedWriter.close();
                    written = true;
                } else if (lineNewStart.equals("Deleted;")){
                    ChangelineToMedie(null, series, "", "Deleted;", counter);
                    bufferedWriter.close();
                    written = true;
            } else {
                counter++;
            }}}} catch (IOException e){
                System.out.println("Could not add media to My List!");
                e.printStackTrace();
        }}


    //Slet film fra mylists-txt
    public void removeMediaFromMyList(Film film, Series series) throws IOException {
       try {
           Scanner sc = new Scanner(new File(fileMyList)); //Scanner der skal scanne txt-filen
           sc.useDelimiter("[;\n]"); //ny linje
           bufferedWriter = new BufferedWriter(new FileWriter(new File(fileMyList), true));
           boolean found = false;

           while (!found) {
               String lineRead = sc.nextLine();
               if (film != null) {
                   if (lineRead.equals(getFilmInfo(film))) {
                       Changeline(lineRead, "Deleted;");
                       //System.out.println("Movie deleted from My List");
                       found = true;
                   }
               }
               if (series != null) {
                   if (lineRead.equals(getSeriesInfo(series))) {
                       Changeline(lineRead, "Deleted;");
                       //System.out.println("TV Show deleted from My List");
                       found = true;
                   }
               }
           }
           System.out.println("Process done!");
       } catch (IOException e){
           System.out.println("Could not remove media from list!");
           e.printStackTrace();
       }
    }

    //returnerer string med film-info
    public String getFilmInfo(Film film){
        String navn = film.getName();
        int year = film.getYear();
        String[] genre = film.getGenre(); //læs som string array
        String str = Arrays.toString(genre);
        float rating = (film.getRating());
        String typeM = "Film";
        return (navn + ": " + year + ": " + str + ": " + rating + ": " + typeM + ";");

    }

    public String getSeriesInfo(Series series){
        String navn = series.getName();
        String year = series.getYear() + series.getEndYear();
        String[] genre = series.getGenre(); //læs som string array
        String str = Arrays.toString(genre);

        float rating = series.getRating();
        String typeM = "Series";
        return (navn + ": " + year + ": " + str + ": " + rating + ": " + getSeasonAndEpisodes(series) + typeM + ";");
    }

    //Hjælpemetode til getSeriesInfo
    public String getSeasonAndEpisodes(Series series){
        String[] season = series.getSeasons(); //String[] af sæson-tal
        String line = "";
        for(int i = 0; i < season.length; i++){
            if (i == (season.length - 1)){
                line += season[i] + ": ";
            } else if(i < (season.length - 1)){
                line += season[i] + ", ";
        }}
        return line;
    }

    //Ændrer 'Stop' til info om film i txt-fil
    //https://newbedev.com/java-replace-line-in-text-file, 10. dec. 2021
    public void ChangelineToMedie(Film film, Series series, String newW, String old, int counter) throws IOException {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of(fileMyList)));
            for (int i = counter; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(old)) {
                    if (film != null) {
                        fileContent.set(i, (getFilmInfo(film) + newW));
                        break;
                    } else if (series != null) {
                        fileContent.set(i, (getSeriesInfo(series) + newW));
                        break;
                    }
                }}
            Files.write(Path.of(fileMyList), fileContent);
        }

    //Hjælpemetode til at fjerne film fra mylist-txt
    public void Changeline(String old, String newL) throws IOException {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of(fileMyList)));
        for (int i = 0; i < fileContent.size(); i++) {
            if (fileContent.get(i).equals(old)) {
                fileContent.set(i, newL);
                break;
            }
        }
        Files.write(Path.of(fileMyList), fileContent);
    }
}