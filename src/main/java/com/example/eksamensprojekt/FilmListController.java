package com.example.eksamensprojekt;

import com.example.eksamensprojekt.Models.Film;
import com.example.eksamensprojekt.Services.LoadingFilm;
import com.example.eksamensprojekt.Services.MyList;
import com.example.eksamensprojekt.Services.Search;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

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

    //Render til gridpane
    @FXML
    public void renderFilm(List<Film> film) {
    int i = 0;
        for (Film f : film) {
            //Laver parametrer til labels
            Label titleLabel = new Label(f.getName());
            titleLabel.setFont(new Font("Arial", 13));
            Label yearLabel = new Label(f.getYear() + "");
            yearLabel.setFont(new Font("Arial", 13));
            Label genreToStringLabel = new Label(f.genreToString()+ "");
            genreToStringLabel.setFont(new Font("Arial", 13));
            Label ratingLabel = new Label(f.getRating()+ "");
            ratingLabel.setFont(new Font("Arial", 13));
            Button btn=new Button("Play Movie");
            btn.setFont(new Font("Arial", 13));
            Button MyListbtn=new Button("Add to My List");
            MyListbtn.setFont(new Font("Arial", 13));

            //Henter image/thumbnail
            URL url = FilmListController.class.getResource(f.getImage());
            Image image = new Image(String.valueOf(url));
            ImageView thumbnailImageView = new ImageView(image);

            ratingLabel.setPadding(new Insets(0,0,1,0));

            //Laver en virtuel box i hvert rum i gridpane, som smider alle labels ind i rækkefølge
            VBox box = new VBox(titleLabel,yearLabel,genreToStringLabel,ratingLabel,thumbnailImageView,btn,MyListbtn);
            box.setAlignment(Pos.BASELINE_CENTER);
            box.setPadding(new Insets(12,12,12,12));

            filmGridPane.add(box, i % 3, Math.floorDiv(i, 3)); //gør at hver række går fra index 0 til 2 og floor gør at der divideres med 3 fra listen, tager den heltal lavest
            i++;

            //afspil film
            btn.setOnMouseClicked((event)-> {
                try {
                    FXMLLoader fxmlLoader= new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Playwindow.fxml"));

                    Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setTitle(f.getName() + " is playing..");
                    stage.setScene(scene);
                    stage.show();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            //Knap til at tilføje film til MyList
            MyListbtn.setOnMouseClicked((event)-> {
                try {
                    ml.writeMyListMedie(f, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    //fjerner alt i gridpane
    @FXML
    public void clearView()
    {
        filmGridPane.getChildren().clear();
    }

    //søger efter title (contains) eller year (equals)
    @FXML
    public void searchFilm() {
        //System.out.println("searchFilm");
        //System.out.println(film.size());
        clearView();
        searchAndGenre();
        renderFilm(this.filterFilm);
    }

    //initialiserer view'et
    @FXML
    public void initialize() {
        //System.out.println("initialize");
        LoadingFilm lf = new LoadingFilm();
        film = lf.openFile();
        filterFilm = film;
        renderFilm(film);
        Search se = new Search();

        //laver en "Observable" liste med genrer, som kan bruges i combobox
        ObservableList<String> list = FXCollections.observableArrayList(se.getAllGenreFilm(film));
        comboBox.setStyle("-fx-font: 13px \"Arial\";");
        comboBox.setItems(list); //Tager den "Observable" liste og putter i comboboxen
    }

    //vælg genre
    @FXML
    public void select() {
        clearView();
        searchAndGenre();
        renderFilm(this.filterFilm); //en metode burde kun have et formål
    }

    //søg efter film under én bestemt genre
    private void searchAndGenre() {
        Search se = new Search();
        resetSelect();
        var combo = comboBox.getSelectionModel().getSelectedItem();
        //Ternery, first, checker om combo har et selected value. eller combo sættes til at være alle
        var value = !Objects.isNull(combo) ? combo.toString() : "All";
        //System.out.println(combo);
        //System.out.println("check"+combo);
        List<Film> filter = se.getSearchedFilmGenre(value, this.film);
        this.filterFilm = se.getSearchedFilm(searchField.getText(), filter);

    }
    //buffer, sæt filterFilm til fim
    private void resetSelect()
    {
        filterFilm = film;
    }
}

