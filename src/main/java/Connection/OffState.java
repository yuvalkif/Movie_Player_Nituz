package Connection;
import Context.*;

import java.util.concurrent.atomic.AtomicInteger;

public class OffState implements State, MachineState {
    private Context context;

    public OffState(Context context) {
        this.context = context;
    }

    @Override
    public void turnOn() {

    }

    @Override
    public void run(){
        System.out.println("Entering Internet Off State");

    }



    @Override
    public void turnOff() {

    }

    @Override
    public void runState() {
        run();

    }

    @Override
    public void internetOff() {

    }

    @Override
    public void internetOn() {
        System.out.println("Leaving InterNetOff State");
        context.setState(((ConnectionRegion)context).onState);

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


}
