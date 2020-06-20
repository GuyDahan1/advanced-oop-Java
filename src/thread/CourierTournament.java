package thread;

import Graphics.CompetitionFrame;
import animals.Animal;

import java.util.concurrent.atomic.AtomicBoolean;

public class CourierTournament extends Tournament {

    Thread t;//TournamentThread Thread
    AtomicBoolean startSignal;
    AtomicBoolean endSignal;
    AtomicBoolean oddLocationEndSignal;
    Scores scores;
    Thread[][] animalThreads;

    public CourierTournament(Animal[][] animals, CompetitionFrame frame, int index) {
        super(animals, frame, index);
    }


    public double calcNeededDistance(Animal animal, int animalIndex) {
        double neededDistance = 0;
        if (animal.getFamilyType().contains("Water")) {
            neededDistance = animal.calcDistance(CompetitionFrame.getEndPointWater()[animalIndex]);
        } else if (animal.getFamilyType().contains("Air")) {
            neededDistance = animal.calcDistance(CompetitionFrame.getEndPoint()[animalIndex]);
        } else if (animal.getFamilyType().contains("Terr")) {
            neededDistance = animal.calcDistance(CompetitionFrame.getEndPoint()[animalIndex]) + animal.calcDistance(CompetitionFrame.getStartPoint()[animalIndex + 1]);
        }
        return neededDistance;
    }


    @Override
    public void setup(Animal[][] animals) {
        scores = new Scores();
        startSignal = new AtomicBoolean(false);
        endSignal = new AtomicBoolean(false);

        AnimalThread[][] animalThread = new AnimalThread[animals.length][];
        animalThreads = new Thread[animals.length][];


        animalThread[tourIndex] = new AnimalThread[animals[tourIndex].length];
        animalThreads[tourIndex] = new Thread[animals[tourIndex].length];
        AtomicBoolean[] booleansArray;
        booleansArray = new AtomicBoolean[animals[tourIndex].length];

        for (int j = 0; j < animals[tourIndex].length; j++) {
            booleansArray[j] = new AtomicBoolean(false);

            Animal currentAnimal = animals[tourIndex][j]; // syntax sugar

            Referee ref = new Referee(currentAnimal.getName(), scores, booleansArray[j]); // make a referee for the current animal


            if (j % 2 == 0) {
                animalThread[tourIndex][j] = new AnimalThread(currentAnimal, calcNeededDistance(currentAnimal, j),
                        startSignal, endSignal, ref, false, this, j);
            } else {
                animalThread[tourIndex][j] = new AnimalThread(currentAnimal, calcNeededDistance(currentAnimal, j),
                        startSignal, endSignal, ref, false, this); //
            }

            animalThreads[tourIndex][j] = new Thread(animalThread[tourIndex][j], animals[tourIndex][j].getName());
            animalThreads[tourIndex][j].start();
            Thread refThread = new Thread(ref, animals[tourIndex][j].getName() + "REF");
            refThread.start();
        }
        super.frame.setAnimalVector(animals[tourIndex]);
        TournamentThread tournamentThread = new TournamentThread(animalThread, scores, startSignal, tourIndex, booleansArray);
        super.setTournamentThread(tournamentThread);
        t = new Thread(tournamentThread, "TournamentThread");
        t.start();
    }


    public void notifyTournamentThread() {
        synchronized (t) {
            t.notify();
        }
    }

    public void notifyNextAnimal(int index) {
        synchronized (animalThreads[tourIndex][index + 1]) {
            animalThreads[tourIndex][index + 1].notify();
        }
    }


}