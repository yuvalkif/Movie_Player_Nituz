package Downloader;


import Context.*;

import java.util.ArrayList;

public class DiskCheckState  implements State, MachineState {

    private Context context;
    private double diskSize;
    private double diskSizeLeft;
    private int counter;
    private File file;
    ArrayList<File> diskFiles;

    public DiskCheckState(Context context){
        this.context = context;
        this.diskSize = 100;
        this.diskSizeLeft = diskSize;
        this.counter = 0;
        this.diskFiles = new ArrayList<>();
        this.file = context.getDownloadedFile();
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
    public void run() {
        System.out.println("Entering DiscCheck State");
        if(file == null)
            file = this.context.getDownloadedFile();


        if(file.getSize()-diskSizeLeft <=0 ){
            diskSizeLeft = diskSizeLeft - file.getSize();
            if(context.hasInternetConnection()) {
                System.out.println("Leaving DiskCheck State");
                context.setState(((DownloaderRegion) context).downloadingState);
                return;
            }
            else {
                System.out.println("Leaving DiskCheck State");
                context.setState(((DownloaderRegion) context).downloadPauseState);
                return;

            }

        }
        else{
            System.out.println("No room in disc, trying again in 4 seconds.");
            try {
                Thread.sleep(4000);
                //if there is space , try to start download
                if(file.getSize() - diskSizeLeft  >= 0 && context.getDownloadedSoFar() != 0){
                    diskSizeLeft = diskSizeLeft - file.getSize();

                    if(context.hasInternetConnection()) {
                        System.out.println("Leaving DiskCheck State");

                        context.setState(((DownloaderRegion) context).downloadingState);
                        return;
                    }
                    else {
                        System.out.println("Leaving DiskCheck State");

                        context.setState(((DownloaderRegion) context).downloadPauseState);
                        return;
                    }
                }
                //no space after second attempt

                    System.out.println("not enough room after two attempts, download aborted.");
                    System.out.println("Leaving CheckDisk State");
                    context.setState(((DownloaderRegion)context).idleState);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }

    @Override
    public void runState() {
        run();

    }

    @Override
    public void fileRequest(File file) {
        System.out.println("Entering CheckDisk State");
        this.file = file;
        this.run();

    }

    @Override
    public void downloadAborted() {
        System.out.println("Download Aborted, Deleting file and Leaving CheckDisk State");
        diskSizeLeft = diskSizeLeft + file.getSize();
        context.changeUserPoints(-1);
        context.setState(((DownloaderRegion)context).idleState);

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
        System.out.println(" In CheckDisc State, size left: "+this.diskSizeLeft);

    }

    protected void deleteFile(File f){

        this.diskSizeLeft+= f.getSize();
    }


}
