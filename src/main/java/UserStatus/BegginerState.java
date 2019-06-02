package UserStatus;

import Context.Context;
import Context.MachineState;

public class BegginerState extends AUserStatusState {

    private final double downloadSpeed = 1 ;

    private Context context;
    public BegginerState(Context context) {
        super(context);
    }

    @Override
    public void run() {
        System.out.println("Entering Begginer State");



    }

    @Override
    public double getDownloadSpeed() {
        return this.downloadSpeed;
    }
}
