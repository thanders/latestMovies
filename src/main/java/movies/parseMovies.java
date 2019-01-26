package movies;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;


import java.io.File;
import java.util.Locale;

import static java.lang.Integer.parseInt;

public class parseMovies {

    // Initialize an ArrayList of Objects
    private ArrayList<Object> movieList = new ArrayList<Object>();
    private File movieFile;
    private SimpleStringProperty title;
    private SimpleObjectProperty<LocalDate> releaseDateDF;
    private SimpleIntegerProperty runtime;
    private SimpleStringProperty certificate;
    private SimpleStringProperty genre;
    private String imdbRating_content;
    private SimpleDoubleProperty imdbRating;
    private movies movie;


    // Constructor
    public parseMovies(File file) {
         this.movieFile = file;
        System.out.println("This is parseMovies");
        // clean the movieFile
        clean();
        parse();
        instanciateMovies();



        // parse(movieFile);
    }

    // Cleans the HTML file by replacing certain HTML tags to remove whitespaces
    public void addtoArrayList(Object movie) {

        movieList.add(this.movie);
        System.out.println("ArrayList size: " + movieList.size());

    }

    // Cleans the HTML file by replacing certain HTML tags to remove whitespaces
    public void instanciateMovies() {

        // Create a new instance of the movies class and add each movie to it
        this.movie = new movies(this.title, this.releaseDateDF, this.runtime, genre, certificate, imdbRating);

        System.out.println(this.movie.toString());

        // add it to an arraylist
        addtoArrayList(this.movie);
    }

    // Cleans the HTML file by replacing certain HTML tags to remove whitespaces
    public File clean() {

        try{

        BufferedReader reader = new BufferedReader(new FileReader(this.movieFile));
        String line = "", oldtext = "";
        while ((line = reader.readLine()) != null) {
            oldtext += line + "\r\n";
        }
        reader.close();
        // replace a word in a file
        //String newtext = oldtext.replaceAll("drink", "Love");

        //To replace a line in a file
        String newText1 = oldtext.replaceAll("lister-item mode-grid", "listmovies");
        String newText2 = newText1.replaceAll("text-muted text-small", "director");
        String newText3 = newText2.replaceAll("p class=\"\"", "description");
        String newText4 = newText3.replaceAll("rating rating-list", "imdbRating-list");

        FileWriter writer = new FileWriter(this.movieFile);
        writer.write(newText4);
        writer.close();

        // System.out.println(moviefile);
        }

        catch (IOException e){

            e.printStackTrace();
    }

        return this.movieFile;

}
    // Parses the html file with Jsoup
    public void parse(){

        try{
            Document doc = Jsoup.parse(this.movieFile, "utf-8");

            Elements movies = doc.select("div.listMovies");
            // System.out.println(headerDiv.children());

            //Elements dingle = doc.select("p");//or only `<p>` elements
            int count=0;

            // Location of downloaded file
            System.out.println(this.movieFile+ "Dingle");

            for (Element el : movies){
                // System.out.println("element: "+el);
                // Parses the title from <div class = title><a href> movie title </a> </div>
                 this.title = new SimpleStringProperty(el.select("div.title").first().select("a").first().text());

                // Parses the release date
                String releaseDate = el.select("div#release_date").first().select("strong").text();
                System.out.println("The date: "+ releaseDate);


                //Format formatter = new SimpleDateFormat("dd MMM yyyy");



                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
                LocalDate dt = LocalDate.parse(releaseDate, formatter);
                this.releaseDateDF = new SimpleObjectProperty(dt);
                System.out.println("Parsed date is : " + dt);



                // Parses the runtime - converts String to Integer and stores it as a SimpleIntegerProperty
                 this.runtime = new SimpleIntegerProperty(parseInt(el.select("div#runtime").first().select("strong").text()));

                // Parses the certificate rating - stores as SimpleStringProperty
                 this.certificate = new SimpleStringProperty(el.select("p.director").first().select("span.certificate").text());

                // Parses the genre - Stores as SimpleStringProperty
                 this.genre = new SimpleStringProperty(el.select("p.director").first().select("span.genre").text());

                // Parses the imdbRating - Stores as SimpleDoubleProperty
                Element meta = el.select("meta[itemprop=ratingValue]").first();
                imdbRating_content = meta.attr("content");
                // Cast string to Double
                 this.imdbRating = new SimpleDoubleProperty(Double.parseDouble(imdbRating_content));

                count++;

                System.out.println(count);

                // create an instance of the movies class
                instanciateMovies();

                }
            }


            catch (IOException e) {

            System.err.println("An IOException was caught :"+e.getMessage());
        }




    }

}
