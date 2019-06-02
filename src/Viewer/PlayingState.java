package Viewer;

import Context.*;

public class PlayingState implements State, MachineState {

    private Context context;

    public PlayingState(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        while(context.getCurrState() == this){
            try {
                ((ViewerRegion)context).playingTime+=1;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Error in sleep in playing State");
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
        System.out.println("Internet off, Leaving PlayMovie");
        context.setState(((ViewerRegion)context).pause);

    }

    @Override
    public void internetOn() {

    }

    @Override
    public void fileRequest(File file) {

    }

    @Override
    public void downloadAborted() {
        System.out.println("Download Abort, Leaving Play Movie");
        context.changeUserPoints(-1);
        context.setState(((ViewerRegion)context).idle);


    }

    @Override
    public void downloadError() {
        System.out.println("Download Error, Leaving Play Movie");
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
        ((ViewerRegion)context).playingTime = 0;
        this.runState();

    }

    @Override
    public void holdMovie() {
        System.out.println("holding movie, Leaving Playing State");
        context.setState(((ViewerRegion)context).pause);


    }

    @Override
    public void movieOff() {
        System.out.println("Leaving Playing Movie");
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
        System.out.println("Entered Playing State");
        Thread t = new Thread(()->run());
        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println("Error on viewer when try to play movie in thread");
        }

    }
}
