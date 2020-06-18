package thread;

import animals.Animal;

import java.util.concurrent.atomic.AtomicBoolean;

public class TournamentThread implements Runnable {

    private Scores scores;
    private AtomicBoolean startSignal;
    AnimalThread[][] animalsArray;

    public TournamentThread(AnimalThread[][] animalsThreads, Scores scores, AtomicBoolean startSignal) {
        this.animalsArray = animalsThreads;
        this.scores = scores;
        this.startSignal = startSignal;
    }

    public void run() {
        synchronized (startSignal) {
            startSignal.set(true);
            System.out.println("TourThread startSignal True");
        }
        for (int i = 0; i <animalsArray.length ; i++) {
            for (int j = 0; j <animalsArray[i].length ; j++) {
                synchronized (animalsArray[i][j]) {
                    animalsArray[i][j].notifyAll();
                    System.out.println("Notify TourThread");
                }
            }
        }
    }
}

