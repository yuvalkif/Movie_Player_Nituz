package Downloader;

import Context.*;

import javax.crypto.Mac;
import java.util.concurrent.atomic.AtomicInteger;

public class IdleState implements State, MachineState {

    private Context context;

    public IdleState(Context context) {

        this.context  = context;
    }




    @Override
    public void run() {
        System.out.println("Entered DownloadIdle State");
        context.setUnitsDownloaded(0);

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
    public void runState() {
        this.run();

    }

    @Override
    public void internetOn() {

    }

    @Override
    public void fileRequest(AtomicInteger file) {
        System.out.println("Recived a file request for file: " +"size: "+file.get());
        System.out.println("Leaving IdleDownload State");
        context.setState(((DownloaderRegion)context).diskCheckState);

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
}
