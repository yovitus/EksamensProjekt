package com.example.eksamensprojekt.Services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadingFilm {
    private Scanner s;
    List<String[]> film;

    public LoadingFilm() {
        film = new ArrayList<>();
    }

    public List<String[]> openFile ()
        {
            try
            {
                InputStream istream = LoadingFilm.class.getResourceAsStream("/com/Media/film.txt");
                System.out.println(istream);
                s = new Scanner(istream);
            }
            catch (Exception e)
            {
                System.out.println("Could not find file");
            }

            while(s.hasNext()) {
                String oneString = s.nextLine();
                String[] strings = oneString.split(" *; *");
                strings[3] = strings[3].replace(",", ".");
                film.add(strings);
            }

            return film;
        }

        public List<String[]> getFilmList()
        {
            return film;
        }

        //public void readFile()
        //{
        //    while(s.hasNext());
        //     String name = s.next();
        //    String year = s.next();
        //    String genre = s.next();
        //    String rating = s.next();

        //    System.out.printf("%s %s %s %s\n", name, year, genre, rating );
        //}

        public void closeFile()
        {
            s.close();
        }

    public static void main(String[] args) {
        LoadingFilm loadingFilm = new LoadingFilm();
        loadingFilm.openFile();
        System.out.println("hej");
    }
    }