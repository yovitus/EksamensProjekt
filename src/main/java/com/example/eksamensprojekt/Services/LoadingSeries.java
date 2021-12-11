package com.example.eksamensprojekt.Services;

import com.example.eksamensprojekt.Models.Series;
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
                //deler season op i array og tilføjer episoder til en ArrayList
                String[] seasonsArray = line[4].split(", ");
                String[] episodesArray = null;
                ArrayList<String> episodesList = new ArrayList<>();
                for(int i = 0; i < seasonsArray.length; i++) {
                    episodesArray = seasonsArray[i].split("-");
                    episodesList.add(episodesArray[1]);
                }

                series.add(new Series(name, rYear, genre, rating, "series", endYear, seasonsArray, episodesList));
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

/*
Dette virker for season:
    String[] seasonsArray = line[4].split(", ");
    Season[] seasonList = new Season[seasonsArray.length];

Med double array
 String[] seasonsArray = line[4].split(", ");
                String[] episodesArray = null;
                String gem = null;
                String[] gemListe = null;
                for(int i = 0; i < seasonsArray.length; i++) {
                    episodesArray = seasonsArray[i].split("-");
                    gem = episodesArray[1];
                    gem = gemListe[i];
                }

                //String episodesString = episodesArray[1];
                //for(int j = 0; j < ) {

                //}
                //int episodesInt = Integer.parseInt(episodesString);
                Season[][] seasonList = new Season[seasonsArray.length][gemListe.length];



                Container[][] cargoShip = new Container[coloumns][rowsPerColoumn];
                for (int i = 0; i < cargoShip.length; i++) {
                    for (int j = 0; j < cargoShip[i].length; j++) {
                        cargoShip[i][j] = new Container(i, j);
                    }

 */
//-----------------------------------------------------
    //Andet
    /*
                String[] seasonsArray = line[4].split(", ");
                String[] episodesArray = null;
                for(int i = 0; i < seasonsArray.length; i++) {
                    episodesArray = seasonsArray[i].split("-");
                }
                 */




    //finder episode antal og opretter Episode object
                /*String[] oneRecord = null;
                String episodeList3 = "";
                for(int i = 0; i < seasonsArray.length; i++) {
                    oneRecord = seasonsArray[i].split("-");
                }
                for(int j = 1; j < oneRecord.length; j=+2) {
                    episodeList3 = oneRecord[j];
                }
                int episodeInt = Integer.parseInt(episodeList3);

                //String episodes = seasons.substring(seasons.indexOf("-")+1, seasons.indexOf(","));
                //int episodesInt = Integer.parseInt(episodes);
                //int counter = 0;
                //for(int i = 0; i < episodesInt; i++) {
                    //counter++;
                //}
                Episode[] episodeList = new Episode[episodeInt];
                 */




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


    /* Allar tær ymisku royndirnar við at fáa árstølini inn

                int yearRelease = 0;
                int yearEnd = 0;
                if(line[1].contains("-")) {
                    String[] yearArray = line[1].split("-");
                    yearRelease = Integer.parseInt(yearArray[0]);
                    yearEnd = Integer.parseInt(yearArray[1]);
                } else {
                     yearRelease = Integer.parseInt(line[1]);
                }

                 //line[1].indexOf("-") - viðmerking
//hvis der er to årstal - splitter dem i et array og assigner hver deres variabel med indlæst årstal.
//else assigner kun releaseYear med indlæst årstal

                if(line[1].contains("-1") || line[1].contains("-2")) {
                    String[] yearArray = line[1].split("-");
                    yearRelease = Integer.parseInt(yearArray[0]);
                    yearEnd = Integer.parseInt(yearArray[1]);
                } else {
                    yearRelease = Integer.parseInt(line[1].replace("- ", ""));
                }
                    endsWith("- ")

    //String yearString = Arrays.toString(year);
                //int yearInt = Integer.parseInt(yearString);
                //int[] yearIntArray = year.length;
// hvussu lesi eg árstølini rætt inn?
// Omanfyri havi eg fyrst deilt upp í array, síðan gjørt um til String og síðan til int.
                //int year = Integer.parseInt(line[1]); //ændrer type fra String til int og assignes variable navn


    Hetta riggar
                    String[] yearArray = line[1].split("-");
                    int yearRelease = Integer.parseInt(yearArray[0]);
                    int yearEnd = Integer.parseInt(yearArray[1]);


     Måske deler op i flere steder (tror ikke det virker)
            //String[] episode = line[4].split("-|, ");
     */
}

