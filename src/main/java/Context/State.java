package Context;

import java.util.concurrent.atomic.AtomicInteger;

/** interface which represents the system events**/
public interface State {

    public void turnOn();
    public void turnOff();
    public void internetOff();
    public void internetOn();
    public void fileRequest(AtomicInteger file);
    public void downloadAborted();
    public void downloadError();
    public void errorFixed();
    public void movieOn();
    public void restartMovie();
    public void holdMovie();
    public void movieOff();
    public void resume();
    public void printState();
    public void runState();





}
