package Viewer;

import Context.*;
import Downloader.DownloaderRegion;

import java.util.concurrent.atomic.AtomicInteger;

public class IdleState implements State, MachineState {

    private Context context;


    public IdleState(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
            System.out.println("Entered Idle State");
            ((ViewerRegion)context).playingTime = 0;



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
        if((int)(context.getDownloadedSoFar()*100/context.getDownloadedFile().get()) >= 20) {
            System.out.println("Leaving Movie Idle State");
            this.context.setState(((ViewerRegion) context).playing);
        }



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
    public void runState() {
        run();

    }
}
