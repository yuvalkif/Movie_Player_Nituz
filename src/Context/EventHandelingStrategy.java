package Context;

public class EventHandelingStrategy{
    private State state;


    public EventHandelingStrategy(State state){
        this.state = state;
        }

    public void listen(String event){
        if(event.equals("turnOn"))
            state.turnOn();
        else if(event.equals("turnOff"))
            state.turnOff();
        else if(event.equals("internetOn"))
            state.internetOn();
        else if(event.equals("internetOff"))
            state.internetOff();

        else if(event.equals("downloadAborted"))
            state.downloadAborted();
        else if(event.equals("downloadError"))
            state.downloadError();
        else if(event.equals("errorFixed"))
            state.errorFixed();
        else if(event.equals("movieOn"))
            state.movieOn();
        else if(event.equals("movieOff"))
            state.movieOff();
        else if(event.equals("restartMovie"))
            state.restartMovie();
        else if(event.equals("holdMovie"))
            state.holdMovie();
        else if(event.equals("movieOff"))
            state.movieOff();
        else if(event.equals("resume"))
            state.resume();
        else{
                String [] request = event.split(" ");
                if(request[0].equals("fileRequest")) {
                state.fileRequest(new File("f",Double.parseDouble(request[1])));
            }
        }




    }

}
