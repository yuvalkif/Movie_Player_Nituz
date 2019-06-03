package Connection;

import Context.*;

import java.util.ArrayList;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionRegion extends java.util.Observable implements State,Context{

    private Context context;
    private State currState;
    private ArrayList<Observer> observers;
    State onState ;
    State offState ;

    public ConnectionRegion(MovieDownloader context){
        this.context = context;
        this.onState = new OnState(this);
        this.offState = new OffState(this);
        /**on startup internet is off**/
        this.observers = new ArrayList<>();

        this.setState(offState);

    }


    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }

    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
    }

    public void notifyObservers(){
        for(Observer o:observers){
            o.update(this,currState);
        }

    }

//    @Override
//    public void run() {
//
//        while(((MovieDownloader)context).getSystemOn()){
//            this.strat.listen(((MovieDownloader)context).getSystemOn());
//        }
//
//    }

    @Override
    public void runState() {

    }

    @Override
    public void turnOn() {
    }

    @Override
    public void turnOff() {
    }

    @Override
    public void internetOff() {
        this.setState(this.offState);
    }

    @Override
    public void internetOn() {
        this.setState((this.onState));

    }

    @Override
    public void fileRequest(AtomicInteger file) {
        currState.fileRequest(file);
    }

    @Override
    public void downloadAborted() {
    }

    @Override
    public void downloadError() {
    }

    @Override
    public void errorFixed() {
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
    public AtomicInteger getDownloadedFile() {
        return context.getDownloadedFile();
    }

    @Override
    public double getDownloadSpeed() {
        return 0;
    }

    @Override
    public double getDownloadedSoFar() {
        return 0;
    }

    @Override
    public boolean hasInternetConnection() {
        return this.currState == onState;
    }

    @Override
    public void setUnitsDownloaded(double unitsDownloaded) {

    }

    @Override
    public void setDownloadSpeed(double downloadSpeed) {

    }

    @Override
    public void setState(State state) {
        this.currState = state;
        this.currState.runState();

    }

    @Override
    public void changeUserPoints(int pointsChange) {

    }


    @Override
    public State getCurrState() {
        return null;
    }

    @Override
    public double getUserPoints() {
        return context.getUserPoints();
    }

    @Override
    public void deleteFile() {
         context.deleteFile();

    }
}
