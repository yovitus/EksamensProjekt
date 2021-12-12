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
    //FileWriter fileWriter;
    public ArrayList<Film> mylistFilm;
    public ArrayList<Series> mylistSeries;
    String fileMyList = "MyLists.txt";
    String cUsernameList = "CurrentUsername.txt"; //current username txt-fil
    private Scanner s;
    String currentUsername;
    int counter;
    int counterD;

    public MyList() throws FileNotFoundException {
        mylistFilm = new ArrayList<Film>();
        mylistSeries = new ArrayList<Series>();
        counter = 0;
        counterD = 0;

        //scan CurrentUsername.txt og indlæs username for nuværende bruger
        s = new Scanner(new File(cUsernameList));
        this.currentUsername = s.nextLine();
    }

    //Indlæs MyList film og serier for nuværende bruger til arrayL
    public void findLoadListMedie(ArrayList<Film> AListF, ArrayList<Series> AListS) throws IOException {
        s = new Scanner(new File(fileMyList)); //Scanner der skal scanne txt-filen, "MyLists.txt
        s.useDelimiter("[;\n]"); //efter ; skift linje
        counterD = 0;


        //Loader specifik mylistFilm for bruger
        while (s.hasNext()) {
            if(s.nextLine().equals(currentUsername + ";")) {
                System.out.println(("username fundet!"));

                while (s.hasNext()){
                    String nextRead = s.nextLine();
                    //System.out.println((nextRead));
                    if(nextRead.equals("Stop;")){
                        System.out.println("End of list reached!");
                        if(counterD == 0 || counter == 0){
                            System.out.println("List is empty!");
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
                        System.out.println("One element has been loaded to FilmList!");
                        counter++;
                     } else if (!nextRead.equals("Deleted;") && !nextRead.contains("Film;")){
                        String oneString = nextRead;
                        String[] line = oneString.split(" *: *"); //splitter ved ;
                        String name = line[0];
                        String releaseYear = line[1].substring(0, 4);
                        int rYear = Integer.parseInt(releaseYear);
                        String endYear = line[1].substring(4);
                        //Deler genre op i et array
                        //String[] genre = line[2].split(", ");
                        String genre1 = line[2].replace("[", ""); //fjerner [
                        String genre2 = genre1.replace("]", ""); //fjerner ]
                        String[] genre = genre2.split(", ");

                        //laver String om til float
                        float rating = Float.parseFloat(line[3]);
                        AListS.add(new Series(name, rYear, genre, rating, "series", endYear, null, null));
                        System.out.println("One element has been loaded to SeriesList!");
                        counter++;
                    }
                }
                break;
            }}}


    //Skriver nye Film ind på .txt filen
    public void writeMyListMedie(Film film, Series series) throws IOException {
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
                System.out.println("username match fundet!");
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
                    System.out.println("Film already on list!");
                    match = true;
                }} else if (series != null){
                    if(lineNewStart.equals(getSeriesInfo((series)))){
                        System.out.println("Series already on list!");
                        match = true;
                    }
                }} else if (lineNewStart.equals("Stop;")){ //medie er IKKE allerede på listen
                System.out.println("That media is currently NOT on the list!");
                break;
            }}

        //hvis match ikke fundet på liste, så tilføj til listen
        while(!written && found && !match){
            String lineNewStart = Files.readAllLines(Paths.get(fileMyList)).get(counter);
            System.out.println((lineNewStart));
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
            }}}}

    //Slet film fra mylists-txt
    public void removeMediaFromMyList(Film film, Series series) throws IOException {
        Scanner sc = new Scanner(new File(fileMyList)); //Scanner der skal scanne txt-filen
        sc.useDelimiter("[;\n]"); //ny linje
        bufferedWriter = new BufferedWriter(new FileWriter(new File(fileMyList), true));
        boolean found = false;

        while (!found) {
            String lineRead = sc.nextLine();
            if(film != null){
                if(lineRead.equals(getFilmInfo(film))) {
                    Changeline(lineRead, "Deleted;");
                    System.out.println("Film slettet fra liste");
                    found = true;
            }}
            if(series != null){
                if (lineRead.equals(getSeriesInfo(series))){
                    Changeline(lineRead, "Deleted;");
                    System.out.println("Serie slettet fra liste");
                    found = true;
            }}
        } System.out.println("Process færdig!");
    }

    //returnerer string med film-info
    public String getFilmInfo(Film film){
        String navn = film.getName();
        int år = film.getYear();
        String[] genre = film.getGenre(); //læs som string array
        String str = Arrays.toString(genre);
        float bedøm = (film.getRating());
        String typeM = "Film";
        return (navn + ": " + år + ": " + str + ": " + bedøm + ": " + typeM + ";");

    }

    public String getSeriesInfo(Series series){
        String navn = series.getName();
        String år = series.getYear() + series.getEndYear();
        String[] genre = series.getGenre(); //læs som string array
        String str = Arrays.toString(genre);

        float rating = series.getRating();
        String typeM = "Series";
        return (navn + ": " + år + ": " + str + ": " + rating + ": " + typeM + ";");
    }

    //Ændrer 'Stop' til info om film i txt-fil
    public void ChangelineToMedie(Film film, Series series, String newW, String old, int counter) throws IOException {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of(fileMyList)));
        for (int i = counter; i < fileContent.size(); i++) {
            if (fileContent.get(i).equals(old)) {
                if(film != null) {
                    fileContent.set(i, (getFilmInfo(film) + newW));
                    break;
                } else if (series != null){
                    fileContent.set(i, (getSeriesInfo(series) + newW));
                    break;
                }
            }
        }
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