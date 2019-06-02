package UserStatus;

import Context.Context;

public class ProffesionalState extends AUserStatusState{
    private final double downloadSpeed = 1.5 ;

    private Context context;
    public ProffesionalState(Context context) {
        super(context);
    }

    @Override
    public void run() {
        System.out.println("Entering Proffesional State");

    }

    @Override
    public double getDownloadSpeed() {
        return this.downloadSpeed;
    }
}
