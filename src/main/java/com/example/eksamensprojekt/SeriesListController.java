package com.example.eksamensprojekt;

import com.example.eksamensprojekt.Models.Series;
import com.example.eksamensprojekt.Services.LoadingSeries;
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
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class SeriesListController { //implements EventHandler<ActionEvent>

    private List<Series> series;
    private List<Series> filterSeries;
    private Button seasonsButton;
    MyList ml = new MyList();

    public SeriesListController() throws FileNotFoundException {
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
    private GridPane seriesGridPane;

    @FXML
    private TextField searchField;

    @FXML
    public void renderSeries(List<Series> series)
    {
        int i = 0;
        for (Series s : series) {

            //Laver parametrer til labels
            Label titleLabel = new Label(s.getName());
            Label yearLabel = new Label(s.getYear() + s.getEndYear());
            Label seasonLabel = new Label(s.getSeasonLength() + " seasons");
            Label episodeLabel = new Label(Arrays.toString(s.getEpisodes()) + " episodes");
            Label genreToStringLabel = new Label(s.genreToString() + "");
            Label ratingLabel = new Label(s.getRating() + "");
            Button playButton = new Button("Play");
            Button MyListbtn = new Button("Add to My List");

            //Henter image/thumbnail
            URL url = SeriesListController.class.getResource(s.getImage());
            Image image = new Image(String.valueOf(url));
            ImageView thumbnailImageView = new ImageView(image);

            ratingLabel.setPadding(new Insets(0, 0, 1, 0));

            //Laver en virtuel box i hvert rum i GridPane, som smider alle labels ind i rækkefølge
            VBox box = new VBox(titleLabel, yearLabel, seasonLabel, genreToStringLabel, ratingLabel, thumbnailImageView, playButton, MyListbtn);
            box.setAlignment(Pos.BASELINE_CENTER);
            box.setPadding(new Insets(12, 12, 12, 12));

            seriesGridPane.add(box, i % 3, Math.floorDiv(i, 3)); //gør at hver række går fra index 0 til 2 og floor gør at der divideres med 3 fra listen, tager den heltal lavest
            i++;

            //viser valgte serie og dens seasons
            //finder det rigtige billede
            playButton.setOnMouseClicked((event)-> {
                clearView();
                VBox imageView = new VBox(thumbnailImageView);
                imageView.setAlignment(Pos.BASELINE_CENTER);
                imageView.setPadding(new Insets(12, 12, 12, 12));
                seriesGridPane.add(imageView, 0, 0);

                //laver knapper til de forskellige seasons og tilføjer til GridPane
                VBox seasonList = new VBox(10);
                VBox backBtn = new VBox(10);
                seasonList.setAlignment(Pos.BASELINE_CENTER);
                backBtn.setAlignment(Pos.TOP_LEFT);
                seasonList.setPadding(new Insets(12, 12, 12, 12));
                backBtn.setPadding(new Insets(10,10, 10, 10));
                Button seasonOne = new Button("Season 1");
                Button goBackBtn = new Button("Go Back");
                backBtn.getChildren().add(goBackBtn);
                seasonList.getChildren().addAll(titleLabel, yearLabel, seasonLabel, episodeLabel, seasonOne);
                for (int j = 1; j < s.getSeasonLength(); j++) {
                    seasonsButton = new Button("Season " + (int) (j + 1));
                    seasonList.getChildren().add(seasonsButton);
                }
                seriesGridPane.add(seasonList, 1, 0);
                seriesGridPane.add(backBtn, 1, 0);


                //ændrer String Array til Integer Array af episoder
                Integer[] episodesIntArray = s.getIntegerArray(s.getEpisodes());
                VBox episodeList = new VBox(10);
                episodeList.setAlignment(Pos.BASELINE_CENTER);
                episodeList.setPadding(new Insets(12, 12, 12, 12));
                Button episodeOne = new Button("Episode 1");
                episodeList.getChildren().add(episodeOne);

                //viser episoderne for season 1
                //laver knapper til de forskellige episoder og tilføjer til GridPane
                seasonOne.setOnMouseClicked((event2) -> {

    //måske bruge en if statement her - der fortæller den ikke kan display det mere end 1 gang
                    for (int k = 0; k < 1; k++) {
                        for(int p = 1; p < episodesIntArray[k]; p++) {
                            Button episodesButton = new Button("Episode " + (int) (p + 1));
                                episodeList.getChildren().add(episodesButton);
                        }
                    }
                    seriesGridPane.add(episodeList, 2, 0);

                    //afspiller episode 1 i nyt vindue
                    episodeOne.setOnMouseClicked((event3) -> {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("Playwindow.fxml"));

                            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                            Stage stage = new Stage();
                            stage.setTitle(s.getName());
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    });
                });
                //Knap til at gå tilbage fra oversigt over sæsoner
                goBackBtn.setOnMouseClicked((event4) -> {
                    seriesGridPane.getChildren().clear();
                        renderSeries(series);
                });
            });
            //Knap til at tilføje serier til MyList
            MyListbtn.setOnMouseClicked((event)-> {
                try {
                    ml.writeMyListMedie(null, s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @FXML
    public void clearView()
    {
        seriesGridPane.getChildren().clear();
    }

    @FXML
    public void searchSeries(KeyEvent event)
    {
        Search se = new Search();
        List<Series> filterSeries = se.getSearchedSeries(searchField.getText(), this.filterSeries);
        clearView();
        renderSeries(filterSeries);
        System.out.println(event);
    }
    @FXML
    public void initialize() {
        LoadingSeries ls = new LoadingSeries();
        series = ls.openFile();
        filterSeries = series;
        renderSeries(series);
        Search se = new Search();
        ObservableList<String> list = FXCollections.observableArrayList(se.getAllGenreSeries(series));
        comboBox.setItems(list);
    }

    @FXML
    public void select(ActionEvent event)
    {
        resetSelect();
        var combo = comboBox.getSelectionModel().getSelectedItem().toString();
        if(combo.equals("All"))
            return;
        Search se = new Search();
        List<Series> filter = se.getSearchedSeriesGenre(combo, this.series);
        this.filterSeries = se.getSearchedSeries(searchField.getText(), filter);
        clearView();
        renderSeries(this.filterSeries);
    }

    private void resetSelect()
    {
        filterSeries = series;
    }
}