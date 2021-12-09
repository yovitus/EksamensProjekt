package com.example.eksamensprojekt;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Services.LoadingFilm;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static javafx.application.Application.launch;

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
        this.filmGridPane=filmGridPane;
        LoadingFilm lf = new LoadingFilm();
        List<Film> film = lf.openFile();
        int i = 0;
        for (Film f : film) {
            Label titleLabel = new Label(f.getName());
            Label yearLabel = new Label(f.getYear() + "");
            Label genreToStringLabel = new Label(f.genreToString()+ "");
            Label ratingLabel = new Label(f.getRating()+ "");
            Button btn = new Button("Play Film");


            URL url = FilmListController.class.getResource(f.getImage());
            Image image = new Image(String.valueOf(url));
            ImageView thumbnailImageView = new ImageView(image);

            ratingLabel.setPadding(new Insets(0,0,1,0));

            VBox box = new VBox(titleLabel,yearLabel,genreToStringLabel,ratingLabel,thumbnailImageView,btn);
            box.setAlignment(Pos.BASELINE_CENTER);
            box.setPadding(new Insets(12,12,12,12));


            filmGridPane.add(box, i % 3, Math.floorDiv(i, 3)); //gør at hver række går fra index 0 til 2 og floor gør at der divideres med 3 fra listen, tager den heltal lavest
            i++;


            btn.setOnMouseClicked((event)-> {
                    try {
                        FXMLLoader fxmlLoader= new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("Playwindow.fxml"));

                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                        Stage stage = new Stage();
                        stage.setTitle("Movie playing");
                        stage.setScene(scene);
                        stage.show();
                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);


                    }
                });
                            }
        }
    }





       /* @FXML
        public void pressbtn(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("Playwindow.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        }

      /*  @FXML
        public void btnPressed(MouseEvent event){
        if(event.getButton().equals(MouseButton.PRIMARY)){
            EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent) {()


                }
/*@FXML
        public void btnPressed(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("Playwindow.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
        }


    /*public void displayImage(){
        LoadingFilm lf = new LoadingFilm();
        List<Film> film = lf.openFile();
        int i = 0;
        for (Film f: film){
        URL url = FilmListController.class.getResource(f.getImage());
        Image image = new Image(String.valueOf(url));
        ImageView thumbnailImageView = new ImageView(image);
        ImageView.setImage(image); */


/*filmGridPane.getChildren().forEach(item -> {
                btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() == 1) {
                            Parent root = null;
                            try {
                                root = FXMLLoader.load(getClass().getResource("Playwindow.fxml"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();;
                        }


                    }

                });
            });

            Parent root = FMXLLoader.load(getClass().getResource("Playwindow.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();*/