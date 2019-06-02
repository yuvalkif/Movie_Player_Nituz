import Context.File;
import Context.MovieDownloader;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String args[]){

        ExecutorService executor = Executors.newFixedThreadPool(4);
        MovieDownloader mv = new MovieDownloader();
        // mv.turnOn();
        // mv.internetOn();
        // mv.fileRequest(new File("f",35));

        String input ;
        Scanner in = new Scanner(System.in);


        while(true){
            input = in.nextLine() ;

            if(input.equals("turnOn")) {
                executor.submit(new Thread(()->mv.turnOn()));
            }else if(input.equals("turnOff")) {
                executor.submit(new Thread(()->mv.turnOff()));
            }else if(input.equals("internetOn")){
                executor.submit(new Thread(()->mv.internetOn()));
            }else if(input.equals("internetOff")) {
                executor.submit(new Thread(()->mv.internetOff()));
            }else if(input.equals("fileRequest")) {
                executor.submit(new Thread(()-> mv.fileRequest(new File("file", 20))));
            }else if(input.equals("downloadAborted")) {
                executor.submit(new Thread(()->mv.downloadAborted()));
            }else if(input.equals("downloadError")) {
                executor.submit(new Thread(()->mv.downloadError()));
            }else if(input.equals("errorFixed")) {
                executor.submit(new Thread(()->mv.errorFixed()));
            }else if(input.equals("movieOn")) {
                executor.submit(new Thread(()->mv.movieOn()));
            }else if(input.equals("restartMovie")) {
                executor.submit(new Thread(()->mv.restartMovie()));
            }else if(input.equals("holdMovie")) {
                executor.submit(new Thread(()->mv.holdMovie()));
            }else if(input.equals("movieOff")) {
                executor.submit(new Thread(()->mv.movieOff()));
            }else if(input.equals("resume")) {
                executor.submit(new Thread(()->mv.resume()));
            }


        }




    }
}