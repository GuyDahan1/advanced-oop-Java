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

        startSignal=true;
        System.out.println("TourThread startSignal True");

        synchronized (animalsArray){
        animalsArray.notifyAll();}
        System.out.println("Notify TourThread");
    }
}

