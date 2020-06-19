package thread;

import animals.Animal;

import java.util.concurrent.atomic.AtomicBoolean;

public class TournamentThread implements Runnable {

    private Scores scores;
    private AtomicBoolean startSignal;
    private AnimalThread[][] animalsArray;
    private String[][] arrayOfScore;
    private int index;

    public TournamentThread(AnimalThread[][] animalsThreads, Scores scores, AtomicBoolean startSignal, int index) {
        this.animalsArray = animalsThreads;
        this.scores = scores;
        this.startSignal = startSignal;
        this.index = index;
    }

    public void run() {
        synchronized (startSignal) {
            startSignal.set(true);
            System.out.println("TourThread startSignal True");
        }
        arrayOfScore = new String[animalsArray.length][];
        for (int j = 0; j < animalsArray[index].length; j++) {
            synchronized (animalsArray[index][j]) {
                animalsArray[index][j].notifyAll();
                System.out.println("Notify TourThread");
            }
        }
        arrayOfScore[index] = new String[animalsArray[index].length];
        for (int j = 0; j < animalsArray[index].length; j++) {
            arrayOfScore[index][j] = scores.getScores().toString();

        }

    }
}
