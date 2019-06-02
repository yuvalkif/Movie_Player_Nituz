package Context;

public interface Context {
    public File getDownloadedFile();
    public double getDownloadSpeed();
    public double getDownloadedSoFar();
    public boolean hasInternetConnection();
    public void setUnitsDownloaded(double unitsDownloaded);
    public void setDownloadSpeed(double downloadSpeed);
    public void setState(State state);
    public void changeUserPoints(int pointsChange);
    public State getCurrState();
    public double getUserPoints();
    public void deleteFile();

}
