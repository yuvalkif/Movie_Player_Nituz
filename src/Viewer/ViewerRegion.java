package Viewer;
import Connection.ConnectionRegion;
import Connection.OffState;
import Context.*;
import Downloader.DownloaderRegion;

import java.util.Observable;
import java.util.Observer;

public class ViewerRegion implements State, Observer,Context {

    private Context context;
    protected int percentCompleted;
    protected State currState;
    protected double playingTime;
    State idle;
    State pause;
    State playing;




    public ViewerRegion(Context context) {

        this.context = context;
        this.idle = new IdleState(this);
        this.pause = new PauseState(this);
        this.playing = new PlayingState(this);
        this.percentCompleted = 0;
        setState(idle);
        this.playingTime = 0;


    }


    @Override
    public void update(Observable o, Object arg) {
            if (o instanceof DownloaderRegion) {
                if(((String)arg).equals("completed")){
                    setState(idle);


                }
            }
            if (o instanceof ConnectionRegion){
                if(arg instanceof OffState){
                    this.internetOff();
                }
                else{
                    this.internetOn();
                }

            }


        }


    @Override
    public File getDownloadedFile() {
        return null;
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
        this.currState = state;
        this.currState.runState();

    }

    @Override
    public void changeUserPoints(int pointsChange) {
        context.changeUserPoints(pointsChange);

    }

    @Override
    public State getCurrState() {
        return this.currState;
    }

    @Override
    public double getUserPoints() {
        return context.getUserPoints();
    }

    @Override
    public void deleteFile() {
        this.context.deleteFile();
    }


    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }

    @Override
    public void internetOff() {
        currState.internetOn();

    }

    @Override
    public void internetOn() {
        currState.internetOff();

    }

    @Override
    public void fileRequest(File file) {


    }

    @Override
    public void downloadAborted() {
        currState.downloadAborted();

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
        currState.movieOn();

    }

    @Override
    public void restartMovie() {
        currState.restartMovie();

    }

    @Override
    public void holdMovie() {
        currState.holdMovie();

    }

    @Override
    public void movieOff() {
        currState.movieOff();

    }

    @Override
    public void resume() {
        currState.resume();

    }

    @Override
    public void printState() {

    }

    @Override
    public void runState() {

    }
}
