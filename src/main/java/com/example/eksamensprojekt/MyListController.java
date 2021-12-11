package com.example.eksamensprojekt;

import com.example.eksamensprojekt.Models.Film;
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
import java.util.List;

public class MyListController {
    MyList ml = new MyList(); //instantierer mylist
    ArrayList AList = ml.mylistFilm; //opretter en mylistfilm

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
    Label deleteFilmMessage;

    @FXML
    public void renderMyListFilm(List<Film> film) {
        int i = 0;
        for (Film f : film) {
            //Laver parametrer til labels
            Label titleLabel = new Label(f.getName());
            Label yearLabel = new Label(f.getYear() + "");
            Label genreToStringLabel = new Label(f.genreToString() + "");
            Label ratingLabel = new Label(f.getRating() + "");
            Button btn = new Button("Play Film");
            Button RemoveFilmbtn = new Button("Remove Film");

            //Henter image/thumbnail
            URL url = MyListController.class.getResource(f.getImage());
            Image image = new Image(String.valueOf(url));
            ImageView thumbnailImageView = new ImageView(image);

            ratingLabel.setPadding(new Insets(0, 0, 1, 0));

            //Laver en virtuel box i hvert rum i gridpane, som smider alle labels ind i rækkefølge
            VBox box = new VBox(titleLabel, yearLabel, genreToStringLabel, ratingLabel, thumbnailImageView, btn, RemoveFilmbtn);
            box.setAlignment(Pos.BASELINE_CENTER);
            box.setPadding(new Insets(12, 12, 12, 12));

            MyListfilmGridPane.add(box, i % 3, Math.floorDiv(i, 3)); //gør at hver række går fra index 0 til 2 og floor gør at der divideres med 3 fra listen, tager den heltal lavest
            i++;

            btn.setOnMouseClicked((event) -> {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Playwindow.fxml"));

                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    Stage stage = new Stage();
                    stage.setTitle("Movie playing");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            RemoveFilmbtn.setOnMouseClicked((event) -> {
                try {
                    ml.removeFilmFromMyList(f, AList);
                    deleteFilmMessage.setText("Film will be removed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }}

    @FXML
    public void initialize() throws IOException {
        ml.findLoadListFilm(AList); //Kører findloadlist med current username
        renderMyListFilm(AList);
    }
}
