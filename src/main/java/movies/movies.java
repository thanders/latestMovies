package movies;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class movies {

    private SimpleStringProperty title;
    private SimpleObjectProperty<LocalDate> releaseDate;
    private SimpleIntegerProperty runtime;
    private SimpleStringProperty certificate;
    private SimpleStringProperty genre;
    private SimpleDoubleProperty imdbRating;

    // This class helps to organize the movies
    public movies(SimpleStringProperty title, SimpleObjectProperty<LocalDate> releaseDate, SimpleIntegerProperty runtime, SimpleStringProperty genre, SimpleStringProperty certificate, SimpleDoubleProperty rating) {

        this.title = title;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.certificate = certificate;
        this.imdbRating = rating;
        this.genre = genre;
    }        // System.out.println(moviefile);

    public SimpleObjectProperty<LocalDate> getReleaseDate(){ return this.releaseDate; }

    public SimpleStringProperty getTitle(){
        return this.title;
    }

    public SimpleStringProperty getCertificate(){
        return this.certificate;
    }

    public SimpleIntegerProperty getRuntime(){
        return this.runtime;
    }

    public SimpleDoubleProperty getImdbRating(){
        return this.imdbRating;
    }

    // Returns the class variables to a String
    public String toString(){
        return "Title: "+ this.title + " Release date: "+ this.releaseDate + " Runtime: " + this.runtime + " Certificate: " + this.certificate + "Genre: " + this.genre + " IMDB rating: " + this.imdbRating + "\n";
    }
}
