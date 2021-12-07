package com.example.eksamensprojekt.Services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadingSeries {
    private Scanner s;
    List<String[]> series;

    public LoadingSeries() {
        series = new ArrayList<>();
    }

    //public List<String[]> openFile() {
            /*
            try

            {
                InputStream istream = LoadingSeries.class.getResourceAsStream("/com/Media/serier.txt");
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
                series.add(strings);
            }

            return series;
        }

        public List<String[]> getSeriesList()
        {

            return series;
        }

        public static void main(String[] args) {
            LoadingSeries ls = new LoadingSeries();
            ls.openFile();
            System.out.println("hej");
    }
    */

}
