package org.openjfx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import movies.getMovies;
import movies.movies;
import movies.parseMovies;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
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

    private ObservableList<movies> ml;

    @FXML private TableView<movies> tableView;
    @FXML private TableColumn<movies, String> title;
    @FXML private TableColumn<movies, String> releasedate;
    @FXML private TableColumn<movies, String> genre;
    @FXML private TableColumn<movies, Integer> imdbRating;
    @FXML private TableColumn<movies, String> director;
    @FXML private TableColumn<movies, String> runtime;
    @FXML private TableColumn<movies, String> certificate;
    @FXML private Label descText;
    @FXML private Label refreshText;

    @FXML private javafx.scene.control.Button closeButton;
    //@FXML private javafx.scene.control.Button refreshButton;


    // Close the stage (application automatically stops when all stages are closed)
    @FXML private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }


    // Refresh TableView
    @FXML private void refreshTableView(){

        this.ml = processMovies();
        this.ml.clear();
        System.out.println("Refresh button pressed "+ now());
        this.ml = processMovies();
        displayTableView(this.ml);
        refreshText.setText("Last refreshed at "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));


    }

    public void initialize() {

        // Create an instance of the movies class
            this.ml = processMovies();

            displayTableView(this.ml);
    }


    public void displayTableView(ObservableList<movies> ml){

        Integer numMovies = this.ml.size();
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

    public ObservableList<movies> processMovies() {

        getMovies dingle = null;

        try { dingle = new getMovies(); }

        catch (IOException e) { e.printStackTrace(); }

        // get the downloaded file and save it to a variable
        File htmlFile = dingle.getFile();

        // Create an instance of the parseMovies class and pass in the downloaded HTML file
        parseMovies parsed = new parseMovies(htmlFile);

        return parsed.getMovielist();

    }
}
