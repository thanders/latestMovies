module SilverScreen {

    requires javafx.controls;
    requires javafx.fxml;
    requires jsoup;
    requires javafx.graphics;


    opens org.openjfx to javafx.fxml;
    exports org.openjfx;

    exports movies;
    opens movies to javafx.base;
}