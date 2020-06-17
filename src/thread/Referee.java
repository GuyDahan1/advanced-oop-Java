package thread;

public class Referee implements Runnable{

    String name;
    Scores scores;
    Boolean startSignal;

    public Referee(String name , Scores scores , Boolean competitionLive){
        this.name=name;
        this.scores=scores;
        startSignal = competitionLive;
    }

    @Override
    public void run() {
        synchronized (this){
        while(startSignal)
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}
        synchronized (this) {
            scores.add(name);
            notify();
        }
    }
}
