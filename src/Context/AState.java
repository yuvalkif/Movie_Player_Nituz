package Context;

public abstract class AState {

    protected Context context;
    protected State currState;

    public AState(Context context){
        this.context = context;
    }

    public abstract void setCurrState(State state);
    public abstract State getCurrState();
}
