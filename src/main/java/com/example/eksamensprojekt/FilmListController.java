package com.example.eksamensprojekt;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Services.LoadingFilm;
//import com.example.eksamensprojekt.Services.Search;
import com.example.eksamensprojekt.Services.MyList;
import com.example.eksamensprojekt.Services.Search;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FilmListController {
    List<Film> film;
    List<Film> filterFilm;
    MyList ml = new MyList();

    public FilmListController() throws FileNotFoundException {
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
    public void goToMyList(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("MyListSide.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private ComboBox comboBox;

    @FXML
    private GridPane filmGridPane;

    @FXML
    private TextField searchField;

    @FXML
    public void renderFilm(List<Film> film)
    {
    int i = 0;
        for (Film f : film) {
            //Laver parametrer til labels
            Label titleLabel = new Label(f.getName());
            Label yearLabel = new Label(f.getYear() + "");
            Label genreToStringLabel = new Label(f.genreToString()+ "");
            Label ratingLabel = new Label(f.getRating()+ "");
            Button btn=new Button("Play Film");
            Button MyListbtn=new Button("Add to My List");

            //Henter image/thumbnail
            URL url = FilmListController.class.getResource(f.getImage());
            Image image = new Image(String.valueOf(url));
            ImageView thumbnailImageView = new ImageView(image);

            ratingLabel.setPadding(new Insets(0,0,1,0));

            //Laver en virtuel box i hvert rum i gridpane, som smider alle labels ind i rækkefølge
            VBox box = new VBox(titleLabel,yearLabel,genreToStringLabel,ratingLabel,thumbnailImageView,btn,MyListbtn);
            box.setAlignment(Pos.BASELINE_CENTER);
            box.setPadding(new Insets(12,12,12,12));
            //tryk på film
            //box.setOnMouseClicked(event -> );

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
            //Knap til at tilføje film til MyList
            MyListbtn.setOnMouseClicked((event)-> {
                ArrayList mlFilm = ml.mylistFilm;
                mlFilm.add(f);
                try {
                    ml.writeMyListFilm(f, mlFilm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
    }

    @FXML
    public void clearView()
    {
        filmGridPane.getChildren().clear();
    }

    @FXML
    public void searchFilm(KeyEvent event)
    {
        System.out.println("searchFilm");
        System.out.println(film.size());
        Search se = new Search();
        List<Film> filterFilm = se.getSearchedFilm(searchField.getText(), this.filterFilm);
        clearView();
        renderFilm(filterFilm);
        System.out.println(event);
    }

    @FXML
    public void initialize()
    {
        System.out.println("initialize");
        LoadingFilm lf = new LoadingFilm();
        film = lf.openFile();
        filterFilm = film;
        renderFilm(film);
        Search se = new Search();
        ObservableList<String> list = FXCollections.observableArrayList(se.getAllGenreFilm(film));
        comboBox.setItems(list);
    }

    @FXML
    public void select(ActionEvent event)
    {
        System.out.println("select");
        resetSelect();
        var combo = comboBox.getSelectionModel().getSelectedItem().toString();
        if(combo.equals("All"))
            return;
        Search se = new Search();
        List<Film> filter = se.getSearchedFilmGenre(combo, this.film);
        this.filterFilm = se.getSearchedFilm(searchField.getText(), filter);
        clearView();
        renderFilm(this.filterFilm);
    }



    private void resetSelect()
    {
        filterFilm = film;
    }



}

          /*  btn.setOnMouseClicked((event)-> {
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
            });*/

