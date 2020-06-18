package thread;

import animals.Animal;

public class TournamentThread implements Runnable {

    private Scores scores;
    private Boolean startSignal;
    AnimalThread[][] animalsArray;

    public TournamentThread(AnimalThread[][] animalsThreads, Scores scores, Boolean startSignal) {
        this.animalsArray = animalsThreads;
        this.scores = scores;
        this.startSignal = startSignal;
    }

    public void run() {
        synchronized (startSignal) {
            startSignal = Boolean.TRUE;
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

