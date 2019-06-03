package UserStatus;

import Context.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicInteger;

public class UserStatusRegion extends java.util.Observable implements State, Observer,Context {

    private Context context;
    protected double userDownloadSpeed;
    protected AUserStatusState currState;
    protected double userPoints;
    private ArrayList<Observer> observers;

    AUserStatusState begginer;
    AUserStatusState medium;
    AUserStatusState pro;

    public UserStatusRegion(Context context) {

        this.context = context;
        this.begginer = new BegginerState(this);
        this.medium = new MediumState(this);
        this.pro = new ProffesionalState(this);
        this.observers = new ArrayList<>();
        this.currState = begginer;
        this.userDownloadSpeed = begginer.getDownloadSpeed();

    }


    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof MovieDownloader){
            if ((Integer)arg<4){
                this.currState = begginer;
                begginer.run();
                this.notifyObservers();
            }
            else if ((Integer)arg>4 && (Integer)arg< 7 ) {
                this.currState = medium;
                medium.run();
                this.notifyObservers();

            }
            else{
                this.currState = pro;
                pro.run();
                this.notifyObservers();
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

    }

    @Override
    public void internetOn() {

    }

    @Override
    public void fileRequest(AtomicInteger file) {

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


    public void notifyObservers(){
        for(Observer o : observers){
            o.update(this,this.getDownloadSpeed());
        }

    }

    @Override
    public AtomicInteger getDownloadedFile() {
        return context.getDownloadedFile();
    }

    @Override
    public double getDownloadSpeed() {
        return currState.getDownloadSpeed();
    }

    @Override
    public double getDownloadedSoFar() {
        return 0;
    }

    @Override
    public boolean hasInternetConnection() {
        return false;
    }

    @Override
    public void setUnitsDownloaded(double unitsDownloaded) {

    }

    @Override
    public void setDownloadSpeed(double downloadSpeed) {

    }

    @Override
    public void setState(State state) {

    }

    @Override
    public void changeUserPoints(int pointsToReduce) {

    }

    @Override
    public State getCurrState() {
        return (State)this.currState;
    }

    @Override
    public void runState() {

    }

    @Override
    public double getUserPoints() {
        return context.getUserPoints();
    }

    @Override
    public void deleteFile() {
        context.deleteFile();
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
