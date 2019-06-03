package Viewer;

import Context.*;

import java.util.concurrent.atomic.AtomicInteger;

public class PlayingState implements State, MachineState {

    private Context context;
    protected Thread playingThread;

    public PlayingState(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        System.out.println("Enter MoviePlaying State");
        while(context.getCurrState() == this){
            try {
                ((ViewerRegion)context).playingTime+=1;
                System.out.println("Movie Playing Time: "+((ViewerRegion)context).playingTime);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }

    }

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {
        System.out.println("Leaving PlayMovie State");
        playingThread.interrupt();


    }

    @Override
    public void internetOff() {
        System.out.println("Internet off, Leaving PlayMovie State");
        playingThread.interrupt();
        context.setState(((ViewerRegion)context).pause);

    }

    @Override
    public void internetOn() {

    }

    @Override
    public void fileRequest(AtomicInteger file) {

    }

    @Override
    public void downloadAborted() {
        System.out.println("Download Abort, Leaving Play Movie");
        playingThread.interrupt();
        context.changeUserPoints(-1);
        context.setState(((ViewerRegion)context).idle);


    }

    @Override
    public void downloadError() {
        System.out.println("Download Error, Leaving Play Movie");
        playingThread.interrupt();
        context.setState(((ViewerRegion)context).pause);


    }

    @Override
    public void errorFixed() {

    }

    @Override
    public void movieOn() {
    }

    @Override
    public void restartMovie() {
        System.out.println("Restarting Movie");
        playingThread.interrupt();
        ((ViewerRegion)context).playingTime = 0;
        this.runState();

    }

    @Override
    public void holdMovie() {
        System.out.println("holding movie, Leaving Playing State");
        playingThread.interrupt();
        context.setState(((ViewerRegion)context).pause);


    }

    @Override
    public void movieOff() {
        System.out.println("Leaving Playing Movie");
        playingThread.interrupt();
        context.setState(((ViewerRegion)context).idle);

    }

    @Override
    public void resume(){

    }

    @Override
    public void printState() {

    }

    @Override
    public void runState() {
        System.out.println("Entered PlayingMovie State");
        playingThread = new Thread(()->run());
        playingThread.start();

    }
}
