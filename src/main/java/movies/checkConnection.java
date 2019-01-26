package movies;

import java.net.URL;
import java.net.URLConnection;

// Checks the connection to the the movie info source
public class checkConnection {


    public static boolean checkConnection() {
        // TODO Auto-generated method stub
        try {
            URL url = new URL("https://www.imdb.com/");
            URLConnection connection = url.openConnection();
            connection.connect();

            return true;
        }
        catch (Exception e) {
            return false;
        }

    }

}