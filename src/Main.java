import Context.File;
import Context.MovieDownloader;

public class Main {
    public static void main(String args[]){

        MovieDownloader mv = new MovieDownloader();
        mv.turnOn();
        mv.internetOn();
        mv.fileRequest(new File("f",1));
        mv.downloadAborted();
        mv.fileRequest(new File("f",5));
        mv.internetOff();
        mv.internetOn();
        mv.movieOn();
        mv.holdMovie();
        mv.resume();
        mv.movieOff();
















    }
}
