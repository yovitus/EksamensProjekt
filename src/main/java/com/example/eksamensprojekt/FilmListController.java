package com.example.eksamensprojekt;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Services.LoadingFilm;
//import com.example.eksamensprojekt.Services.Search;
import com.example.eksamensprojekt.Services.Search;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FilmListController {
    List<Film> film;

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
    private GridPane filmGridPane;

    @FXML
    private TextField searchField;

    @FXML
    public void renderFilm(List<Film> film)
    {
    int i = 0;
        for (Film f : film) {
        Label titleLabel = new Label(f.getName());
        Label yearLabel = new Label(f.getYear() + "");
        Label genreToStringLabel = new Label(f.genreToString()+ "");
        Label ratingLabel = new Label(f.getRating()+ "");

        URL url = FilmListController.class.getResource(f.getImage());
        Image image = new Image(String.valueOf(url));
        ImageView thumbnailImageView = new ImageView(image);

        ratingLabel.setPadding(new Insets(0,0,1,0));

        VBox box = new VBox(titleLabel,yearLabel,genreToStringLabel,ratingLabel,thumbnailImageView);
        box.setAlignment(Pos.BASELINE_CENTER);
        box.setPadding(new Insets(12,12,12,12));
        //tryk på film
        //box.setOnMouseClicked(event -> );

        filmGridPane.add(box, i % 3, Math.floorDiv(i, 3)); //gør at hver række går fra index 0 til 2 og floor gør at der divideres med 3 fra listen, tager den heltal lavest
        i++;
    }
}
    @FXML
    public void clearView()
    {
        filmGridPane.getChildren().clear();
    }

    @FXML
    public void search(KeyEvent event)
    {
        Search se = new Search();
        List<Film> filterFilm = se.getSearchedFilm(searchField.getText(), this.film);
        clearView();
        renderFilm(filterFilm);
        System.out.println(event);
    }


    @FXML
    public void initialize()
    {
        LoadingFilm lf = new LoadingFilm();
        film = lf.openFile();
        renderFilm(film);
    }
}

