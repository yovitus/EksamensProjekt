package com.example.eksamensprojekt;

import com.example.eksamensprojekt.Models.Series;
import com.example.eksamensprojekt.Services.LoadingSeries;
import com.example.eksamensprojekt.Services.Search;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SeasonController {

    private List<Series> series;

    @FXML
    public void goToSeriesList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SeriesList.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToStartside(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Startside.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
    public void goToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToMyList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MyList.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private GridPane seasonGridPane;

    public ImageView imageFound() {
        ImageView thumbnailImageView = null;
        for (Series s : series) {
            URL url = SeriesListController.class.getResource(s.getImage());
            Image image = new Image(String.valueOf(url));

            if (s.getName().equals(String.valueOf(image))) {
                thumbnailImageView = new ImageView(image);
            } else {
                System.out.println("There is no correlating image");
            }
        }
        return thumbnailImageView;
    }

    public void renderSeries(List<Series> series) {
        //int i = 0;

        for (Series s : series) {

            //Laver parametrer til labels
            Label titleLabel = new Label(s.getName());
            //Label yearLabel = new Label(s.getYear() + s.getEndYear());
            Label seasonLabel = new Label(s.getSeasonLength() + " seasons");
            //Label episodeLabel = new Label(s.getEpisodeLength() + " episodes");
            //Label genreToStringLabel = new Label(s.genreToString() + "");
            //Label ratingLabel = new Label(s.getRating() + "");
            //Button btn = new Button("Play Film");

            //Henter image/thumbnail
            //URL url = SeriesListController.class.getResource(s.getImage());
            //Image image = new Image(String.valueOf(url));
            //ImageView thumbnailImageView = new ImageView(image);

            //ratingLabel.setPadding(new Insets(0, 0, 1, 0));

            //Laver en virtuel box i hvert rum i gridpane, som smider alle labels ind i rækkefølge
                    //VBox boxImage = new VBox(imageFound());
            //VBox box = new VBox(titleLabel, yearLabel, seasonLabel, episodeLabel, genreToStringLabel, ratingLabel, thumbnailImageView, btn);
                    //boxImage.setAlignment(Pos.BASELINE_CENTER);
                    //boxImage.setPadding(new Insets(12, 12, 12, 12));

                    //VBox boxSeason = new VBox();

            //seasonGridPane.add(box, i % 3, Math.floorDiv(i, 3)); //gør at hver række går fra index 0 til 2 og floor gør at der divideres med 3 fra listen, tager den heltal lavest
            //i++;
                    //seasonGridPane.add(boxImage, 0, 0);
                    //seasonGridPane.add(seasonLabel, 1, 0);
            //i++;
        }

            // Royna at seta hetta uttanfyri foreach loop
        //Laver en virtuel box i hvert rum i gridpane, som smider alle labels ind i rækkefølge
        VBox boxImage = new VBox(imageFound());
        boxImage.setAlignment(Pos.BASELINE_CENTER);
        boxImage.setPadding(new Insets(12, 12, 12, 12));

        //Gera ein aðra boks til seasons label
        //Mát vera VBox -- Box box = new Box(BoxLayout. X_AXIS); box. add(button1); box
        //VBox boxSeason = new VBox();

        seasonGridPane.add(boxImage, 0, 0);
        //seasonGridPane.add(seasonLabel, 1, 0);


    }

    @FXML
    public void initialize() {
        LoadingSeries ls = new LoadingSeries();
        series = ls.openFile();
        renderSeries(series);
    }

    public void loadSeasonFxml(Event event) throws IOException {
        //Load new FXML and assign it to scene
        Parent root = FXMLLoader.load(getClass().getResource("Season.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
