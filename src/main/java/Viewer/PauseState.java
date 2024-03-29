package Viewer;

import Context.*;

public class PauseState implements State, MachineState {

    private Context context;
    public PauseState(Context context) {
        this.context = context;
    }

    @Override
    public void run() {

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
    public void fileRequest(File file) {

    }

    @Override
    public void downloadAborted() {

    }

    @Override
    public void downloadError() {

    }

    @Override
    public void errorFixed() {
        System.out.println("Error fixed , Leaving pause Movie");
        context.setState(((ViewerRegion)context).playing);

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
        System.out.println("Movie turned off, Leaving pause Movie");
        context.setState(((ViewerRegion)context).idle);


    }

    @Override
    public void resume() {
        System.out.println("Resumed , Leaving pause Movie");
        context.setState(((ViewerRegion)context).playing);

    }

    @Override
    public void printState() {

    }

    @Override
    public void runState() {
        System.out.println("Entered Pause Movie State");
        run();

    }
}
