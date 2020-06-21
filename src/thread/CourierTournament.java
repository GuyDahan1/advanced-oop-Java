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

        for (int i = 0; i < animals.length; i++) {


            animalThread[i] = new AnimalThread[animals[i].length];

            AtomicBoolean[] booleansArray;
            booleansArray = new AtomicBoolean[animals[i].length];

            for (int j = 0; j < animals[i].length; j++) {
                booleansArray[j] = new AtomicBoolean(false);

                Animal currentAnimal = animals[i][j]; // syntax sugar

                Referee ref = new Referee(currentAnimal.getName(), scores, booleansArray[j]); // make a referee for the current animal


                if (j % 2 == 0) {
                    animalThread[i][j] = new AnimalThread(currentAnimal, calcNeededDistance(currentAnimal, j),
                            startSignal, booleansArray[j], ref, false, this);
                } else {
                    animalThread[i][j] = new AnimalThread(currentAnimal, calcNeededDistance(currentAnimal, j),
                            booleansArray[j - 1], endSignal, ref, false, this); //
                }

                Thread animalThreads = new Thread(animalThread[i][j], animals[i][j].getName());
                animalThreads.start();
                Thread refThread = new Thread(ref, animals[i][j].getName() + "REF");
                refThread.start();
            }
            super.frame.setAnimalVector(animals[i]);
            TournamentThread tournamentThread = new TournamentThread(animalThread, scores, startSignal, i, booleansArray);
            t = new Thread(tournamentThread, "TournamentThread");
            super.setTournamentThread(t);
            t.start();
        }
    }

}