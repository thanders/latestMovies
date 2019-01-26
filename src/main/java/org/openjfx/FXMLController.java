package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import movies.getMovies;
import movies.parseMovies;

import java.io.File;

public class FXMLController {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    public void initialize() {

        // Create an instance of the movies class

        try {
            getMovies dingle = new getMovies();

            // get the downloaded file and save it to a variable
            File htmlFile = dingle.getFile();

            // Create an instance of the parseMovies class and pass in the downloaded HTML file
            parseMovies parsed = new parseMovies(htmlFile);

        }
        catch(Exception e){

            System.out.println(e);
            e.printStackTrace();

        }
    }
}
