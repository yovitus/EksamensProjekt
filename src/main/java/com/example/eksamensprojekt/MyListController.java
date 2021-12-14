package com.example.eksamensprojekt;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Models.Series;
import com.example.eksamensprojekt.Services.MyList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyListController {
    MyList ml = new MyList(); //instantierer mylist
    ArrayList<Film> AListF = ml.mylistFilm; //tom mylistfilm
    ArrayList<Series> AListS = ml.mylistSeries; //tom mylistseries
    private Button seasonsButton;

    public MyListController() throws FileNotFoundException {
    }

    @FXML
    public void goToFilmList(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("FilmList.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    public void goToSeriesList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SeriesList.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToStartPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Startside.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private GridPane MyListfilmGridPane;

    @FXML
    private GridPane seriesGridPane;

    @FXML
    Label deleteMediaMessage;

    @FXML
    public void clearView()
    {
        seriesGridPane.getChildren().clear();
    }

    @FXML
    public void renderMyListMedia(List<Film> film, List<Series> series) {
        if(film != null){
        int i = 0;
        for (Film f : film) {
            //Laver parametrer til labels
            Label titleLabel = new Label(f.getName());
            Label yearLabel = new Label(f.getYear() + "");
            Label genreToStringLabel = new Label(f.genreToString() + "");
            Label ratingLabel = new Label(f.getRating() + "");
            Button btn = new Button("Play Movie");
            Button RemoveFilmbtn = new Button("Remove from My List");

            //Henter image/thumbnail
            URL url = MyListController.class.getResource(f.getImage());
            Image image = new Image(String.valueOf(url));
            ImageView thumbnailImageView = new ImageView(image);

            ratingLabel.setPadding(new Insets(0, 0, 1, 0));

            //Laver en virtuel box i hvert rum i gridpane, som smider alle labels ind i rækkefølge
            VBox box = new VBox(titleLabel, yearLabel, genreToStringLabel, ratingLabel, thumbnailImageView, btn, RemoveFilmbtn);
            box.setAlignment(Pos.BASELINE_CENTER);
            box.setPadding(new Insets(12, 12, 12, 12));

            MyListfilmGridPane.add(box, i % 2, Math.floorDiv(i, 2)); //gør at hver række går fra index 0 til 2 og floor gør at der divideres med 3 fra listen, tager den heltal lavest
            i++;

            btn.setOnMouseClicked((event) -> {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Playwindow.fxml"));

                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    Stage stage = new Stage();
                    stage.setTitle(f.getName() + " is playing..");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            RemoveFilmbtn.setOnMouseClicked((event) -> {
                try {
                    ml.removeMediaFromMyList(f, null);
                    deleteMediaMessage.setText("Movie will be removed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }}
        if(series != null){
        int l = 0;
        for (Series s : series) {
            //Laver parametrer til labels
            Label titleLabel = new Label(s.getName());
            Label yearLabel = new Label(s.getYear() + s.getEndYear());
            Label seasonLabel = new Label(s.getSeasonLength() + " seasons");
            Label episodeLabel = new Label(Arrays.toString(s.getEpisodes()) + " episodes");
            Label genreToStringLabel = new Label(s.genreToString() + "");
            Label ratingLabel = new Label(s.getRating() + "");
            Button playButton = new Button("Play");
            Button RemoveSeriesbtn = new Button("Remove from My List");

            //Henter image/thumbnail
            URL url = MyListController.class.getResource(s.getImage());
            Image image = new Image(String.valueOf(url));
            ImageView thumbnailImageView = new ImageView(image);

            ratingLabel.setPadding(new Insets(0, 0, 1, 0));

            //Laver en virtuel box i hvert rum i GridPane, som smider alle labels ind i rækkefølge
            VBox box = new VBox(titleLabel, yearLabel, genreToStringLabel, ratingLabel, thumbnailImageView, playButton, RemoveSeriesbtn);
            box.setAlignment(Pos.BASELINE_CENTER);
            box.setPadding(new Insets(12, 12, 12, 12));

            seriesGridPane.add(box, l % 2, Math.floorDiv(l, 2)); //gør at hver række går fra index 0 til 2 og floor gør at der divideres med 3 fra listen, tager den heltal lavest
            l++;

            //viser valgte serie og dens seasons
            //finder det rigtige billede
            playButton.setOnMouseClicked((event)-> {
                clearView();
                VBox imageView = new VBox(thumbnailImageView);
                imageView.setAlignment(Pos.BASELINE_CENTER);
                imageView.setPadding(new Insets(12, 12, 12, 12));
                seriesGridPane.add(imageView, 0, 0);

                //laver knapper til de forskellige seasons og tilføjer til GridPane
                VBox seasonList = new VBox(10);
                VBox backBtn = new VBox(10);
                seasonList.setAlignment(Pos.BASELINE_CENTER);
                backBtn.setAlignment(Pos.TOP_LEFT);
                seasonList.setPadding(new Insets(12, 12, 12, 12));
                backBtn.setPadding(new Insets(10,10, 10, 10));
                Button seasonOne = new Button("Season 1");
                Button goBackBtn = new Button("Go Back");
                backBtn.getChildren().add(goBackBtn);
                seasonList.getChildren().addAll(titleLabel, yearLabel, seasonLabel, episodeLabel, seasonOne);
                for (int j = 1; j < s.getSeasonLength(); j++) {
                    seasonsButton = new Button("Season " + (int) (j + 1));
                    seasonList.getChildren().add(seasonsButton);
                }
                seriesGridPane.add(seasonList, 1, 0);
                seriesGridPane.add(backBtn, 1, 0);


                //ændrer String Array til Integer Array af episoder
                Integer[] episodesIntArray = s.getIntegerArray(s.getEpisodes());
                VBox episodeList = new VBox(10);
                episodeList.setAlignment(Pos.BASELINE_CENTER);
                episodeList.setPadding(new Insets(12, 12, 12, 12));
                Button episodeOne = new Button("Episode 1");
                episodeList.getChildren().add(episodeOne);

                //viser episoderne for season 1
                //laver knapper til de forskellige episoder og tilføjer til GridPane
                seasonOne.setOnMouseClicked((event2) -> {

                    //måske bruge en if statement her - der fortæller den ikke kan display det mere end 1 gang
                    for (int k = 0; k < 1; k++) {
                        for(int p = 1; p < episodesIntArray[k]; p++) {
                            Button episodesButton = new Button("Episode " + (int) (p + 1));
                            episodeList.getChildren().add(episodesButton);
                        }
                    }
                    seriesGridPane.add(episodeList, 2, 0);

                    //afspiller episode 1 i nyt vindue
                    episodeOne.setOnMouseClicked((event3) -> {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("Playwindow.fxml"));

                            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                            Stage stage = new Stage();
                            stage.setTitle(s.getName() + " is playing..");
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                });
                //Knap til at gå tilbage fra oversigt over sæsoner
                goBackBtn.setOnMouseClicked((event4) -> {
                    seriesGridPane.getChildren().clear();
                    renderMyListMedia(null, series);
                });
            });
            RemoveSeriesbtn.setOnMouseClicked((event) -> {
                try {
                    ml.removeMediaFromMyList(null, s);
                    deleteMediaMessage.setText("TV Show will be removed");

                } catch (IOException e) {
                    e.printStackTrace();
                }});}}}

        @FXML
        public void initialize() throws IOException {
            ml.findLoadListMedie(AListF, AListS); //Loader medier fra txt-fil
            renderMyListMedia(AListF, null); //displayer medier for nuværende user
            renderMyListMedia(null, AListS);

        }
    }
