package UserStatus;

import Context.*;

public abstract class  AUserStatusState implements MachineState {

    protected Context context;
    public AUserStatusState(Context context){
        this.context = context;
    }

    public abstract  double getDownloadSpeed();

}
