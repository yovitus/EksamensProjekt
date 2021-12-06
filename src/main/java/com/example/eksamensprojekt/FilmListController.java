package com.example.eksamensprojekt;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Services.LoadingFilm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FilmListController {
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
    private GridPane filmGridPane;

    @FXML
    public void initialize() {
        System.out.println("tjek tjek");
        LoadingFilm lf = new LoadingFilm();
        List<Film> film = lf.openFile();
        int index = 0;
        for (Film f : film) {
            Label titleLabel = new Label(f.getName());
            Label yearLabel = new Label(f.getYear() + "");
            Label genreToStringLabel = new Label(f.genreToString()+ "");
            Label ratingLabel = new Label(f.getRating()+ "");


            URL imageURL = FilmListController.class.getResource(f.getImage());
            Image image = new Image(String.valueOf(imageURL));
            ImageView thumbnailImageView = new ImageView(image);

            ratingLabel.setPadding(new Insets(0,0,1,0));

            VBox box = new VBox(titleLabel,yearLabel,genreToStringLabel,ratingLabel,thumbnailImageView);
            box.setAlignment(Pos.BASELINE_CENTER);
            box.setPadding(new Insets(12,12,12,12));

            filmGridPane.add(box, index % 3, Math.floorDiv(index, 3)); //gør at hver række går fra index 0 til 2 og floor gør at der divideres med 3 fra listen, tager den heltal lavest
            index++;
            System.out.println("try to insert" + f.getName());
        }
    }
}
