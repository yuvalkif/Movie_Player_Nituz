package UserStatus;

import Context.Context;

public class MediumState extends AUserStatusState {
    private final double downloadSpeed = 1.2 ;

    private Context context;
    public MediumState(Context context) {
        super(context);
    }

    @Override
    public void run() {
        System.out.println("Entering Medium State");



    }

    @Override
    public double getDownloadSpeed() {
        return this.downloadSpeed;
    }

}
