package com.example.eksamensprojekt;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Services.LoadingFilm;
import com.example.eksamensprojekt.Services.LoadingSeries;
import com.example.eksamensprojekt.Services.Search;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    @Override

    public void start(Stage stage) throws IOException {
      /* LoadingFilm lf = new LoadingFilm();
       Search se = new Search();
       se.getAllGenreFilm(lf.openFile());
        LoadingSeries ls = new LoadingSeries();
        se.getAllGenreSeries(ls.openFile());*/
        try {
        //Scanner sc = new Scanner(Main.class.getResourceAsStream("/com/example/eksamensprojekt/Media/film.txt"),"UTF-8");
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            //David Hansson's error handler
       } catch (Exception e) {
          Alert alert = new Alert(Alert.AlertType.WARNING, "Sorry, a random error occurred. Try again \n\"" + e.getLocalizedMessage() + "\"");
          alert.showAndWait().ifPresent(res -> System.exit(0));
        }
    }

    public static void main(String[] args) {
        launch();
    }

}