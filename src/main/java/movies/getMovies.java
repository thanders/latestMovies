package movies;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
// import org.jsoup.Jsoup;



public class getMovies {

    String downloadURL = "https://www.imdb.com/showtimes/location?ref_=inth_mny_sm";
    String fileName = "dingle.html";
    String home = System.getProperty("user.home");
    File outputFile = new File(home+"/Downloads/" + fileName);

    // Constructor
    public getMovies() throws IOException {

        // if (checkConnection.checkConnection()) {
            // System.out.println("Connection Check Passed");
            this.download();
            System.out.println("File downloaded");
   //     }
    }

    // Starts download. exception thrown if doesn't work
    public void download() throws IOException {
        ReadableByteChannel readChannel = Channels.newChannel(new URL(this.downloadURL).openStream());
        FileOutputStream fileOS = new FileOutputStream(this.outputFile);
        FileChannel writeChannel = fileOS.getChannel();

        writeChannel.transferFrom(readChannel, 0, Long.MAX_VALUE);
        fileOS.close();
    }

    public java.io.File getFile(){
        return outputFile;
    }

    // Starts download. exception thrown if doesn't work
    public void parseHTML() {
        //Document doc = Jsoup.parse(this.outputFile, "UTF-8", "");



    }

}