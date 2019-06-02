import Context.File;
import Context.MovieDownloader;

public class Main {
    public static void main(String args[]){

        MovieDownloader mv = new MovieDownloader();
        mv.turnOn();
        mv.internetOn();
        mv.fileRequest(new File("f",35));






    }
}
