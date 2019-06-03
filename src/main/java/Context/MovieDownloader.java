package Context;

import Connection.ConnectionRegion;
import Connection.OnState;
import Downloader.DownloaderRegion;
import UserStatus.UserStatusRegion;
import Viewer.ViewerRegion;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicInteger;


public class MovieDownloader extends Observable implements State,Context,Observer {

    protected double unitsDownloaded;
    public static String currEvent;
    AtomicInteger currentFileDownload;
    protected double downloadSpeed;
    private boolean systemOn;
    protected int userPoints;

    /**regions**/
    protected State internetStatus;
    protected State userStatus;
    protected State downloader;
    protected State viewer;
    protected ArrayList<State>regions;
    private ArrayList<Observer> observers;



    public MovieDownloader(){
        this.unitsDownloaded = 0;
        this.currentFileDownload = new AtomicInteger(-1);
        this.downloadSpeed = 0;
        this.systemOn = false;
        this.viewer = new ViewerRegion(this);
        this.internetStatus = new ConnectionRegion(this);
        this.observers = new ArrayList<>();
        this.userStatus = new UserStatusRegion(this);
        this.downloader = new DownloaderRegion(this);
        //observers on connectionregion
        ((Observable)internetStatus).addObserver((Observer)downloader);
        ((Observable)internetStatus).addObserver((Observer)viewer);
        //observers on userStatusregion
        ((Observable)userStatus).addObserver((Observer)downloader);
        //observers on downloaderregion
        ((Observable)downloader).addObserver((Observer)userStatus);
        ((Observable)downloader).addObserver(this);
        ((Observable)downloader).addObserver((Observer)viewer);
        //observers on MovieDownloader

        this.regions = new ArrayList<>();
        this.regions.add(internetStatus);
        this.regions.add(userStatus);
        this.regions.add(downloader);
        this.regions.add(viewer);
        addObserver((Observer)userStatus);
        this.userPoints = 0;

    }



    @Override
    public void turnOn() {
        this.systemOn = true;
        currEvent = "turnOn";
        System.out.println("Entering System On State");
//        for(State s:regions)
//            s.runState();

    }

    @Override
    public void turnOff() {
        this.systemOn = false;
        currEvent = "turnOff";
        System.out.println("Entering System Off State");
    }

    @Override
    public void internetOff() {
        currEvent = "internetOff";
        for(State s:regions)
            s.internetOff();
    }

    @Override
    public void internetOn() {
        currEvent = "internetOn";
        for(State s:regions)
            s.internetOn();

    }

    @Override
    public void fileRequest(AtomicInteger file) {
        if (systemOn) {
            if(this.currentFileDownload.get() == -1) {
                System.out.println("putting file in movieplayer");
                this.currentFileDownload = new AtomicInteger(file.get());
                for (State s : regions)
                    s.fileRequest(currentFileDownload);
            }

        }


    }

    @Override
    public void downloadAborted() {
        if (systemOn)
            for(State s:regions)
                s.downloadAborted();

    }

    @Override
    public void downloadError() {
        if (systemOn)
            for(State s:regions)
            s.downloadError();


    }

    @Override
    public void errorFixed() {
        if (systemOn)
            for(State s:regions)
            s.errorFixed();

    }

    @Override
    public void movieOn() {
        if (systemOn)
            for(State s:regions)
            s.movieOn();

    }

    @Override
    public void restartMovie() {
        if (systemOn)
            for(State s:regions)
            s.restartMovie();

    }

    public boolean getSystemOn(){
        return this.systemOn;
    }

    @Override
    public void holdMovie() {
        if (systemOn)
            for(State s:regions)
            s.holdMovie();

    }

    /**   notifing on userPoints   **/
    @Override
    public void notifyObservers() {
        if (systemOn) {
            for (Observer o : observers) {
                o.update(this, userPoints);
            }
        }
    }

    @Override
    public void movieOff() {
        if (systemOn)
            for(State s:regions)
            s.movieOff();

    }

    @Override
    public void resume() {
        if (systemOn)
            for(State s:regions)
            s.resume();

    }

    @Override
    public void printState() {
        if (systemOn)
            for(State s:regions)
            s.printState();

    }


    @Override
    public AtomicInteger getDownloadedFile() {
        return this.currentFileDownload;
    }

    @Override
    public double getDownloadSpeed() {
            return (((Context)userStatus).getDownloadSpeed());
    }

    @Override
    public double getDownloadedSoFar() {
            return ((Context)downloader).getDownloadedSoFar();
    }

    @Override
    public boolean hasInternetConnection() {
            return ((Context)internetStatus).getCurrState() instanceof OnState;
    }

    @Override
    public void setUnitsDownloaded(double unitsDownloaded) {
        if(systemOn) {
            this.unitsDownloaded += unitsDownloaded;
            if (this.unitsDownloaded == currentFileDownload.get())
                notifyObservers();
        }

    }

    @Override
    public void setDownloadSpeed(double downloadSpeed) {

    }


    @Override
    public void setState(State state) {

    }

    @Override
    public void changeUserPoints(int pointsToReduce) {
        if (systemOn) {
            this.userPoints += pointsToReduce;
            this.notifyObservers();
        }
    }

    @Override
    public State getCurrState() {
        return this;
    }


    public void setNewDisc(double newDiscSize){

    }

    public double getUserPoints(){
        return this.userPoints;
    }

    @Override
    public void runState() {

    }

    @Override
    public void deleteFile() {
        ((Context)downloader).deleteFile();
    }


    public State getInternetStatus() {
        return this.internetStatus;
    }

    public State getUserStatus() {
        return this.userStatus;
    }

    public State getDownloader() {
        return this.downloader;
    }

    public State getViewer() {
        return this.viewer;
    }



    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof DownloaderRegion){
            if(((boolean)arg)){
                this.currentFileDownload = new AtomicInteger(-1);

            }
            else if(!((boolean)arg)){
                this.currentFileDownload = new AtomicInteger(-1);
            }

        }

    }
}
