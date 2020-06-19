package thread;

import Graphics.CompetitionFrame;
import animals.Animal;

import java.util.concurrent.atomic.AtomicBoolean;

public class CourierTournament extends Tournament {


    AtomicBoolean startSignal;
    AtomicBoolean firstAnimalEndSignal;
    Scores scores;


    public CourierTournament(Animal[][] animals, CompetitionFrame frame) {
        super(animals, frame);
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
    public void setup(Animal[][] animals) throws InterruptedException {
        System.out.println("RegularTour setup");
        scores = new Scores();
        startSignal = new AtomicBoolean(false);
        AnimalThread[][] animalThread = new AnimalThread[animals.length][];


        for (int i = 0; i < animals.length; i++) {
            animalThread[i] = new AnimalThread[animals[i].length];
            for (int j = 0; j < animals[i].length; j++) {
                AtomicBoolean endSignal = new AtomicBoolean(false);
                if (j % 2 != 0) {
                    firstAnimalEndSignal = endSignal;
                }

                System.out.println("RegularTour setup Loop " + i + " " + j + "Build animal");

                Animal tempAnimal = animals[i][j];
                Referee ref = new Referee(animals[i][j].getName(), scores, endSignal);
                animalThread[i][j] = j % 2 == 0 ? new AnimalThread(tempAnimal, calcNeededDistance(tempAnimal, j), startSignal, endSignal, frame.getCompetitionPanel(),ref) : new AnimalThread(tempAnimal, calcNeededDistance(tempAnimal, j), firstAnimalEndSignal, endSignal, frame.getCompetitionPanel(),ref);

                Thread animalThreads = new Thread(animalThread[i][j]);
                animalThreads.start();
                Thread refThread = new Thread(ref);
                refThread.start();
            }
            super.frame.setAnimalVector(animals[i]);
            TournamentThread thread = new TournamentThread(animalThread, scores, startSignal);
            super.setTournamentThread(thread);
            Thread t = new Thread(thread);
            t.start();
//            super.tournamentThread.run();
        }

        System.out.println("RegularTour setup Loop End func");

    }
}
