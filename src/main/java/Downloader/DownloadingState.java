package Downloader;

import Context.*;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadingState implements State,MachineState {

    private Context context;
    protected Thread downloadingThread;
    AtomicBoolean stop;

    public DownloadingState(Context context) {
        this.context = context;
        this.stop = new AtomicBoolean(false);
    }


    public void run(){
        //this.stop.set(true);
        //System.out.println("");
        double fileSize = context.getDownloadedFile().get();
       // context.getDownloadedSoFar() <= fileSize && context.getCurrState() == this && (((DownloaderRegion)context).hasInternetConnection
        while(context.getCurrState() == this && context.getDownloadedSoFar() <= fileSize){
            try {
                if(context.getDownloadedFile() != null) {
                    context.setUnitsDownloaded(context.getDownloadedSoFar() + context.getDownloadSpeed());
                    //System.out.println("downloadeing :" + context.getDownloadedSoFar() + " units");
                    if(context.getDownloadedSoFar() >= context.getDownloadedFile().get())

                    {
                        ((DownloaderRegion)context).completeDownloading();
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
                //System.out.println("Error at downloading state thread.sleep(1000)");

            }

        }



    }


    @Override
    public void turnOn() {
        this.runState();

    }

    @Override
    public void turnOff() {
        System.out.println("System off, Leaving Downloading State");
        downloadingThread.interrupt();
        context.setState(((DownloaderRegion)context).downloadPauseState);

    }

    @Override
    public void internetOff() {
        System.out.println("Internet go down, Leaving Downloading State");
        downloadingThread.interrupt();
        context.setState(((DownloaderRegion)context).downloadPauseState);

    }

    @Override
    public void internetOn() {

    }

    @Override
    public void fileRequest(AtomicInteger file) {

    }

    @Override
    public void downloadAborted() {
        System.out.println("Leaving Downloading State");
        //this.stop.set(true);
        downloadingThread.interrupt();
        context.setState(((DownloaderRegion)context).idleState);
        context.setUnitsDownloaded(0);


    }

    @Override
    public void runState() {
        System.out.println("Entering DownloadingMode");
        downloadingThread = new Thread(()->this.run());
        downloadingThread.start();



    }

    @Override
    public void downloadError() {
        System.out.println("ERROR , Leaving Downloading State");
        downloadingThread.interrupt();
        context.setState(((DownloaderRegion)context).errorFixerState);
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
