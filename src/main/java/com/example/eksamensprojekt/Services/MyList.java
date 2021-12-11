package com.example.eksamensprojekt.Services;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Models.Medier;
import com.example.eksamensprojekt.Models.Series;
/*
import com.example.eksamensprojekt.Models.User;
import com.example.eksamensprojekt.SeriesListController;
import com.example.eksamensprojekt.StartsideController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox; */

import java.util.ArrayList;

public class MyList {
    BufferedWriter bufferedWriter;
    FileWriter fileWriter;
    public ArrayList mylistFilm;
    public ArrayList mylistSeries;
    String fileMyList = "MyLists.txt";
    String cUsernameList = "CurrentUsername.txt"; //current username txt-fil
    private Scanner s;
    String currentUsername;
    int counter;

    public MyList() throws FileNotFoundException {
        mylistFilm = new ArrayList<Film>();
        mylistSeries = new ArrayList<Series>();

        //scan CurrentUsername.txt og indlæs usn
        s = new Scanner(new File(cUsernameList));
        this.currentUsername = s.nextLine();
        counter = 0;
    }

    //Indlæs MyListFilm for nuværende bruger
    public void findLoadListFilm(ArrayList AList) throws IOException {
        s = new Scanner(new File(fileMyList)); //Scanner der skal scanne txt-filen, "MyLists.txt
        s.useDelimiter("[;\n]"); //efter ; skift linje

        //Loader specifik mylistFilm for bruger
        while (s.hasNext()) {
            if(s.nextLine().equals(currentUsername + ";")) {
                while (s.hasNext()){
                    String nextRead = s.nextLine();
                    if(nextRead.equals("Stop;")){
                        System.out.println("End of list reached!");
                        break;
                    } else if (!nextRead.equals("Deleted;")) {
                        String oneString = nextRead; //tjekker hver linje
                        String[] line = oneString.split(" *: *"); //splitter ved :
                        String name = line[0];
                        int year = Integer.parseInt(line[1]);
                        String genre1 = line[2].replace("[", ""); //fjerner [
                        String genre2 = genre1.replace("]", ""); //fjerner ]
                        String[] genre = genre2.split(", ");
                        float rating = Float.parseFloat(line[3]);
                        AList.add(new Film(name, year, genre, rating, "film")); //tilføj elementer til arrayliste fra txt-fil
                        System.out.println("One element has been loaded!");
                    }}
                break;
            }}}


    //Skriver nye Film ind på .txt filen
    public void writeMyListFilm(Film film) throws IOException {
        Scanner sc = new Scanner(new File(fileMyList)); //Scanner der skal scanne txt-filen
        sc.useDelimiter("[;\n]"); //ny linje
        bufferedWriter = new BufferedWriter(new FileWriter(new File(fileMyList), true));
        boolean found = false;
        boolean written = false;

        //finder mylist tilhørende nuværende bruger
        while (!found) {
            String lineRead = sc.nextLine();
            if(lineRead.equals(currentUsername + ";")) {
                System.out.println("username match fundet!");
                found = true;
            } else {
                System.out.println("username match ikke fundet endnu");
                counter++;
            }}

        //tjekker om film allerede er på brugerens liste
        boolean match = false;
        while (found && !match){
            String lineNewStart = Files.readAllLines(Paths.get(fileMyList)).get(counter);
            if(!lineNewStart.equals("Stop;")){
                counter++;
                if(lineNewStart.equals(getFilmInfo(film))) {
                    System.out.println("Film already on list!");
                    match = true;
                }
            } else if (lineNewStart.equals("Stop;")){ //film er IKKE allerede på listen
                break;
            }}

        //hvis film-match ikke fundet på liste, så tilføj til listen
        while(!written && found && !match){
            String lineNewStart = Files.readAllLines(Paths.get(fileMyList)).get(counter);
            System.out.println((lineNewStart));
            if(found == true && lineNewStart.equals("Stop;")){
                ChangelineToFilm(film, "\n" + "Stop;", "Stop;", counter);
                //bufferedWriter.write( "\n" + "Stop;"); //Skriver nederst pt - RET!
                bufferedWriter.close();
                written = true;
            } else if (lineNewStart.equals("Deleted;")){
                ChangelineToFilm(film, "", "Deleted;", counter);
                bufferedWriter.close();
                written = true;
            }
            counter++; }}

    //Slet film fra mylists-txt
    public void removeFilmFromMyList(Film film) throws IOException {
        Scanner sc = new Scanner(new File(fileMyList)); //Scanner der skal scanne txt-filen
        sc.useDelimiter("[;\n]"); //ny linje
        bufferedWriter = new BufferedWriter(new FileWriter(new File(fileMyList), true));
        boolean found = false;

        while (!found) {
            String lineRead = sc.nextLine();
            if(lineRead.equals(getFilmInfo(film))){
                Changeline(lineRead, "Deleted;");
                System.out.println("Film fundet i liste");
                found = true;
            }
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
    //Ændrer 'Stop' til info om film i txt-fil
    public void ChangelineToFilm(Film film, String newW, String old, int counter) throws IOException {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of(fileMyList)));
        for (int i = counter; i < fileContent.size(); i++) {
            if (fileContent.get(i).equals(old)) {
                fileContent.set(i, (getFilmInfo(film) + newW));
                break;
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

    public void findLoadListSeries(ArrayList AList) throws IOException {
        //load serier
    }
}