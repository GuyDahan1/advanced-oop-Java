package thread;

import java.util.concurrent.atomic.AtomicBoolean;

public class Referee implements Runnable{

    String name;
    Scores scores;
    AtomicBoolean startSignal;

    public Referee(String name , Scores scores , AtomicBoolean competitionLive){
        this.name=name;
        this.scores=scores;
        startSignal = competitionLive;
    }

    @Override
    public void run() {
        synchronized (this){
        while(startSignal.get())
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
