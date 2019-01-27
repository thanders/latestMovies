package movies;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;

public class movies {

    private SimpleStringProperty title;
    private SimpleObjectProperty<LocalDate> releaseDate;
    private SimpleIntegerProperty runtime;
    private SimpleStringProperty director;
    private SimpleStringProperty certificate;
    private SimpleStringProperty genre;
    private SimpleDoubleProperty imdbRating;


    // This class helps to organize the movies
    public movies(SimpleStringProperty title, SimpleObjectProperty<LocalDate> releaseDate, SimpleIntegerProperty runtime, SimpleStringProperty genre, SimpleStringProperty director, SimpleStringProperty certificate, SimpleDoubleProperty rating) {

        this.title = title;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.certificate = certificate;
        this.imdbRating = rating;
        this.genre = genre;
        this.director = director;
    }        // System.out.println(moviefile);

    public LocalDate getReleaseDate(){ return this.releaseDate.get(); }

    public String getTitle(){
        return this.title.get();
    }

    public String getGenre(){
        return this.genre.get();
    }

    public String getCertificate(){
        return this.certificate.get();
    }

    public String getDirector(){
        return this.director.get();
    }

    public Integer getRuntime(){
        return this.runtime.get();
    }

    public Double getImdbRating(){
        return this.imdbRating.get();
    }

    // public SimpleStringProperty getDirector(){ return this.director; }

    // Returns the class variables to a String
    public String toString(){
        return "Title: "+ this.title + " Release date: "+ this.releaseDate + "Runtime: " + this.runtime +  "Director: " + this.director + "Certificate: " + this.certificate + "Genre: " + this.genre + " IMDB rating: " + this.imdbRating + "\n";
    }
}
