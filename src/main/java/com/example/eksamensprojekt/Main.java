package com.example.eksamensprojekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            //Kilde: David Hansson's error handler
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Sorry, a random error occurred. Try again \n\"" + e.getLocalizedMessage() + "\"");
            alert.showAndWait().ifPresent(res -> System.exit(0));
        }
    }

    public static void main(String[] args) {
        launch();
    }

}