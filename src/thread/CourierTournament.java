package thread;

import Graphics.CompetitionFrame;
import animals.Animal;

import java.util.concurrent.atomic.AtomicBoolean;

public class CourierTournament extends Tournament {

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
        System.out.println("Courier setup");
        scores = new Scores();
        startSignal = new AtomicBoolean(false);
        endSignal = new AtomicBoolean(false);

        AnimalThread[][] animalThread = new AnimalThread[animals.length][];

        for (int i = 0; i < animals.length; i++) {

            System.out.println(" animals in CourierTournament =  " + animals[i].length);// todo delete

            animalThread[i] = new AnimalThread[animals[i].length];

            for (int j = 0; j < animals[i].length; j++) {

                AtomicBoolean endSignal = new AtomicBoolean(false);

                Animal currentAnimal = animals[i][j]; // syntax sugar

                Referee ref = new Referee(currentAnimal.getName(), scores, endSignal); // make a referee for the current animal
                System.out.println("RegularTour setup Loop " + i + " " + j + "Build animal");

                if (j % 2 == 0) {
                    oddLocationEndSignal = endSignal;
                    animalThread[i][j] = new AnimalThread(currentAnimal, calcNeededDistance(currentAnimal, j),
                            startSignal, endSignal, ref);
                } else {
                    animalThread[i][j] = new AnimalThread(currentAnimal, calcNeededDistance(currentAnimal, j)
                            , oddLocationEndSignal, animalThread[i][j - 1].getFinishFlag(), ref); // todo -check
                }


                Thread animalThreads = new Thread(animalThread[i][j],animals[i][j].getName());
                animalThreads.start();
                Thread refThread = new Thread(ref,animals[i][j].getName()+"REF");
                refThread.start();
            }
            super.frame.setAnimalVector(animals[i]);
            TournamentThread tournamentThread = new TournamentThread(animalThread, scores, startSignal, i);
            super.setTournamentThread(tournamentThread);
            Thread t = new Thread(tournamentThread,"TournamentThread");
            t.start();
        }

        System.out.println("RegularTour setup Loop End func");

    }
}