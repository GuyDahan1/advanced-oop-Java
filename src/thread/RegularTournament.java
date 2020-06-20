package thread;

import Graphics.CompetitionFrame;
import Graphics.CompetitionPanel;
import animals.Animal;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class RegularTournament extends Tournament {
    AtomicBoolean startSignal;
    Scores scores;

    public RegularTournament(Animal[][] animals, CompetitionFrame frame) {
        super(animals,frame);
    }

    public double calcNeededDistance(Animal animal,int animalIndex) {
        double neededDistance=0;
        if (animal.getFamilyType().contains("Water")) {
            neededDistance = animal.calcDistance(CompetitionFrame.getEndPointWater()[animalIndex]) * 2;
        } else if (animal.getFamilyType().contains("Air")) {
            neededDistance = animal.calcDistance(CompetitionFrame.getEndPoint()[animalIndex]) * 2;
        } else if (animal.getFamilyType().contains("Terr")) {
            neededDistance = animal.calcDistance(CompetitionFrame.getEndPoint()[animalIndex]) * 2 + animal.calcDistance(CompetitionFrame.getStartPoint()[animalIndex + 1]) * 2;
        }
        return neededDistance;

    }


    @Override
    public void setup(Animal[][] animals) {
        System.out.println("RegularTour setup");
        scores = new Scores();
        startSignal = new AtomicBoolean(false);

        AnimalThread[][] animalThread = new AnimalThread[animals.length][];

        for (int i = 0; i < animals.length; i++) {
            animalThread[i]= new AnimalThread[animals[i].length];
            for (int j = 0; j < animals[i].length; j++) {
                System.out.println("RegularTour setup Loop " + i + " " + j + "Build animal");
                AtomicBoolean endSignal = new AtomicBoolean(false);
                endSignal.set(false);
                Animal tempAnimal = animals[i][j];
                animalThread[i][j] = new AnimalThread(tempAnimal,calcNeededDistance(tempAnimal,j), startSignal, endSignal,frame.getCompetitionPanel());
                Thread animalThreads = new Thread(animalThread[i][j]);
                animalThreads.start();
                Referee ref = new Referee(animals[i][j].getName(), scores, endSignal);
                Thread refThread = new Thread(ref);
                refThread.start();
            }
            super.frame.setAnimalVector(animals[i]);
            TournamentThread thread = new TournamentThread(animalThread, scores, startSignal);
            super.setTournamentThread(thread);
            Thread t = new Thread(thread);
            t.start();
        }

        System.out.println("RegularTour setup Loop End func");

    }
}
