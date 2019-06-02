package Downloader;

import Context.*;

public class DownloadPauseState implements State {

    private Context context;


    public DownloadPauseState(Context context) {
        this.context = context;
    }




    @Override
    public void turnOn() {
        System.out.println("Leaving DownloadPause State");
        this.context.setState(((DownloaderRegion)context).downloadingState);


    }

    @Override
    public void turnOff() {

    }

    @Override
    public void internetOff() {

    }

    @Override
    public void internetOn() {
        System.out.println("Leaving DownloadPause State");
        this.context.setState(((DownloaderRegion)context).downloadingState);

    }

    @Override
    public void fileRequest(File file) {

    }

    @Override
    public void downloadAborted() {
        System.out.println("Download aborted, Leaving DownloadPause State");
        this.context.deleteFile();
        this.context.changeUserPoints(-1);
        this.context.setState(((DownloaderRegion)context).idleState);
    }

    @Override
    public void downloadError() {
        downloadError();

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

    @Override
    public void runState() {

    }


}
