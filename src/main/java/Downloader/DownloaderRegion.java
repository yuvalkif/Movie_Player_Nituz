package Downloader;

import Connection.ConnectionRegion;
import Connection.OffState;
import Context.AState;
import Context.*;
import UserStatus.UserStatusRegion;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class DownloaderRegion extends Observable implements State,Context,Observer {

    private Context context;
    private State currState;
    protected boolean hasInternetConnection;
    private ArrayList<Observer> observers;
     State idleState;
     State diskCheckState;
     State downloadingState;
     State downloadPauseState;
     State errorFixerState;
     ArrayList<State> states;

    protected int filePrecentDownloaded;
    protected double unitsDownloaded;
    protected File currentFile;
    protected double fileSize;
    protected double downloadSpeed;//should be recived from observer







    public DownloaderRegion(Context context){
        this.context = context;
        this.idleState = new IdleState(this);
        this.diskCheckState = new DiskCheckState(this);
        this.downloadingState = new DownloadingState(this);
        this.downloadPauseState = new DownloadPauseState(this);
        this.errorFixerState = new ErrorFixerState(this);
        this.hasInternetConnection = false;
        this.currentFile =null;
        this.observers = new ArrayList<>();
        states = new ArrayList<>();
        states.add(idleState);
        states.add(diskCheckState);
        states.add(downloadingState);
        states.add(errorFixerState);
        states.add(downloadPauseState);
        this.currState=idleState;

    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof UserStatusRegion) {
            this.downloadSpeed = ((Context)o).getDownloadSpeed();
        }
        if (o instanceof ConnectionRegion){
            if(arg instanceof OffState){
                this.hasInternetConnection=false;
                this.internetOff();
            }
            else{
                this.hasInternetConnection=true;
                this.internetOn();
            }

        }

    }

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }

    @Override
    public void internetOff() {
        hasInternetConnection =false;
        currState.internetOff();

    }

    @Override
    public void internetOn() {
        hasInternetConnection = true;
        currState.internetOn();

    }

    @Override
    public void fileRequest(File file) {
        currState.fileRequest(file);

    }

    @Override
    public void downloadAborted() {
        System.out.println("download Aborted, Enter DownloadIdle State");
        this.unitsDownloaded = 0;
        this.deleteFile();
        setState(idleState);


    }

    @Override
    public void downloadError() {
        currState.downloadError();

    }

    @Override
    public void errorFixed() {
        currState.errorFixed();

    }

    @Override
    public void movieOn() {

    }

    @Override
    public void restartMovie() {

    }

    @Override
    public void holdMovie() {

    }

    @Override
    public void movieOff() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void printState() {

    }

    @Override
    public File getDownloadedFile() {
        if(this.currentFile == null)
            this.currentFile = new File(context.getDownloadedFile());
        return context.getDownloadedFile();
    }

    @Override
    public double getDownloadSpeed() {

        return context.getDownloadSpeed();
    }

    @Override
    public double getDownloadedSoFar() {
        return this.unitsDownloaded;
    }

    @Override
    public boolean hasInternetConnection() {
        return hasInternetConnection;
    }

    @Override
    public void setUnitsDownloaded(double unitsDownloaded) {
        this.unitsDownloaded = unitsDownloaded;
        this.filePrecentDownloaded = (int)(this.unitsDownloaded*100/this.getDownloadedFile().getSize());

    }

    @Override
    public void setDownloadSpeed(double downloadSpeed) {
        this.downloadSpeed = downloadSpeed;

    }

    @Override
    public void setState(State state) {
        this.currState = state;
        currState.runState();

    }

    @Override
    public void notifyObservers() {
        for(Observer o : observers){
            o.update(this,true);
        }
    }

    @Override
    public void changeUserPoints(int pointsChange) {
        context.changeUserPoints(pointsChange);

    }

    @Override
    public void runState() {
//        while(((MovieDownloader)context).getSystemOn()){
//
//        }

    }

    @Override
    public State getCurrState() {
        return currState;
    }

    @Override
    public double getUserPoints() {
        return context.getUserPoints();
    }

    @Override
    public void deleteFile() {
        ((DiskCheckState)this.diskCheckState).deleteFile(context.getDownloadedFile());
    }

    public void completeDownloading(){
        System.out.println("Download completed in downloader region");
        setState(idleState);
        context.changeUserPoints(+1);
        notifyObservers();

    }


    @Override
    public synchronized void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        this.observers.remove(o);
    }
}
