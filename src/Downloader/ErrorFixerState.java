package Downloader;

import Context.*;

public class ErrorFixerState implements State, MachineState {
    private Context context;
    private boolean fixed;

    public ErrorFixerState(Context context) {

        this.context = context;
    }


    public void run(){
        System.out.println("Starting error fixing");

        int counter = 0;

        while(counter < 2) {
            double rnd = Math.random();
            if (rnd > 0.5) {
                fixed = true;
                this.errorFixed();
                return;
            } else {
                try {
                    counter++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Error in thread sleep in fix state");
                }
            }
        }
        fixed = false;
        context.deleteFile();
        context.changeUserPoints(-1);
        System.out.println("Could not fix Error after 3 seconds, Leaving ErrorFix State");
        context.setState(((DownloaderRegion)context).idleState);
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
        System.out.println("Download Aborted, Deleting file and Leaving ErrorFix State");
        context.deleteFile();
        context.changeUserPoints(-1);
        context.setState(((DownloaderRegion)context).idleState);

    }

    @Override
    public void downloadError() {

    }

    @Override
    public void errorFixed() {
        System.out.println("fixed error, Leaving ErrorFix State");
        context.setState(((DownloaderRegion)context).downloadingState);

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

    @Override
    public void runState() {

    }
}
