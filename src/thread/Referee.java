package thread;

import java.util.concurrent.atomic.AtomicBoolean;

public class Referee implements Runnable {

    String name;
    Scores scores;
    AtomicBoolean endSignal;

    public Referee(String name, Scores scores, AtomicBoolean endSignal) {
        this.name = name;
        this.scores = scores;
        this.endSignal = endSignal;
    }

    @Override
    public void run() {
        synchronized (this) {
            while (!endSignal.get()) {
                try {
                    System.out.println("Referee : " + name);
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (this) {
                scores.add(name);
                System.out.println("score added");
                notify();
            }
        }
    }
}