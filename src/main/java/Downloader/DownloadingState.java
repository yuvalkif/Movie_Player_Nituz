package Downloader;

import Context.*;

public class DownloadingState implements State,MachineState {

    private Context context;

    public DownloadingState(Context context) {
        this.context = context;
    }


    public void run(){
        double fileSize = context.getDownloadedFile().getSize();
       // context.getDownloadedSoFar() <= fileSize && context.getCurrState() == this && (((DownloaderRegion)context).hasInternetConnection
        while(context.getDownloadedSoFar() <= fileSize && context.getCurrState() == this && (((DownloaderRegion)context).hasInternetConnection)){
            try {
                Thread.sleep(1000);
                if(context.getDownloadedFile() != null) {
                    context.setUnitsDownloaded(context.getDownloadedSoFar() + context.getDownloadSpeed());
                    //System.out.println("downloadeing :" + context.getDownloadedSoFar() + " units");
                    if(context.getDownloadedSoFar() >= context.getDownloadedFile().getSize())

                    {
                        ((DownloaderRegion)context).completeDownloading();


                    }
                }

            } catch (InterruptedException e) {
                System.out.println("Error at downloading state thread.sleep(1000)");

            }

        }
        context.setState(((DownloaderRegion)context).downloadPauseState);




    }


    @Override
    public void turnOn() {
        this.runState();

    }

    @Override
    public void turnOff() {

    }

    @Override
    public void internetOff() {
        System.out.println("Internet go down, Leaving Downloading State");
        context.setState(((DownloaderRegion)context).downloadPauseState);

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
    public void runState() {
        System.out.println("Entering Downloading Mode");

        //Thread t =
        MovieDownloader.downThread = new Thread(()->this.run());
        Thread t = new Thread(()->this.run());
        t.start();

//        try {
//           t.join();
//        } catch (InterruptedException e) {
//            System.out.println("Error at running download thread from Downloading State");
//        }




        System.out.println("Leaving Downloading Mode, file units downloaded: "+context.getDownloadedSoFar());



    }

    @Override
    public void downloadError() {
        System.out.println("Download ERROR occured while downloading , Leaving Downloading State");
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
