package com.example.eksamensprojekt;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Services.LoadingFilm;
import com.example.eksamensprojekt.Services.MyList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyListController {

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
    public void testAddFilm() throws IOException {
        MyList ml = new MyList(); //instantierer mylist
        LoadingFilm lf = new LoadingFilm();  //-II- loadingfilm
        List<Film> film = lf.openFile(); //fylder film arrayliste
        ArrayList mlFilm = ml.mylistFilm;
        //mlFilm.add(film.get(3));
        //mlFilm.add(film.get(4));
        //System.out.println(mlFilm.get(0).toString()); //tjek om tilføjet
        //ml.writeMyListFilm(film.get(3), "testl");
        ml.findLoadListFilm("testl", mlFilm); //loader film fra txt-fil til arrayL
        //ml.writeMyListFilm(film.get(7), "annetest"); //tilføj ny film til txt-fil
    }

    @FXML
    public void renderFilm(List<Film> film) {
        int i = 0;
        for (Film f : film) {
            
        }
    }

   /* @FXML
    public void initialize(){

    } */



}
