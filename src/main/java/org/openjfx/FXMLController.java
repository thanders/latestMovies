package org.openjfx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import movies.getMovies;
import movies.movies;
import movies.parseMovies;

import java.io.File;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.now;

public class FXMLController {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML private TableView<movies> tableView;
    @FXML private TableColumn<movies, String> title;
    @FXML private TableColumn<movies, String> releasedate;
    @FXML private TableColumn<movies, String> genre;
    @FXML private TableColumn<movies, Integer> imdbRating;
    @FXML private TableColumn<movies, String> director;
    @FXML private TableColumn<movies, String> runtime;
    @FXML private TableColumn<movies, String> certificate;
    @FXML private Label descText;



    public void initialize() {

        // Create an instance of the movies class

        try {
            getMovies dingle = new getMovies();

            // get the downloaded file and save it to a variable
            File htmlFile = dingle.getFile();

            // Create an instance of the parseMovies class and pass in the downloaded HTML file
            parseMovies parsed = new parseMovies(htmlFile);

            ObservableList<movies> ml = parsed.getMovielist();
            Integer numMovies = ml.size();

            descText.setText(numMovies+ " movies are playing in your area today, "+ now().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));

            title.setCellValueFactory(new PropertyValueFactory<>("title"));
            releasedate.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
            genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
            imdbRating.setCellValueFactory(new PropertyValueFactory<>("imdbRating"));
            director.setCellValueFactory(new PropertyValueFactory<>("director"));
            runtime.setCellValueFactory(new PropertyValueFactory<>("runtime"));
            certificate.setCellValueFactory(new PropertyValueFactory<>("certificate"));





            tableView.getItems().setAll(ml);
            releasedate.setSortType(TableColumn.SortType.DESCENDING);
            tableView.getSortOrder().add(releasedate);
            tableView.sort();
        }
        catch(Exception e){

            System.out.println(e);
            e.printStackTrace();

        }
    }
}
