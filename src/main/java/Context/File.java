package Context;

public class File {
    double size;
    String name;

    public File(String name,double size){
        this.name = name;
        this.size = size;
    }


    public double getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
