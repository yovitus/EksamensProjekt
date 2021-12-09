package com.example.eksamensprojekt;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Models.Series;
import com.example.eksamensprojekt.Services.LoadingFilm;
import com.example.eksamensprojekt.Services.LoadingSeries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SeriesListController {
    @FXML
    private GridPane seriesGridPane;


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
    public void initialize() {
        LoadingSeries ls = new LoadingSeries();
        List<Series> series = ls.openFile();
        int i = 0;
        for (Series s : series) {
            Label titleLabel = new Label(s.getName());
            Label yearLabel = new Label(s.getYear() + s.getEndYear());
            Label genreToStringLabel = new Label(s.genreToString() + "");
            Label ratingLabel = new Label(s.getRating() + "");


            URL url = FilmListController.class.getResource(s.getImage());
            Image image = new Image(String.valueOf(url));
            ImageView thumbnailImageView = new ImageView(image);

            ratingLabel.setPadding(new Insets(0, 0, 1, 0));

            VBox box = new VBox(titleLabel, yearLabel, genreToStringLabel, ratingLabel, thumbnailImageView);
            box.setAlignment(Pos.BASELINE_CENTER);
            box.setPadding(new Insets(12, 12, 12, 12));

            seriesGridPane.add(box, i % 3, Math.floorDiv(i, 3)); //gør at hver række går fra index 0 til 2 og floor gør at der divideres med 3 fra listen, tager den heltal lavest
            i++;
        }
    }
}
