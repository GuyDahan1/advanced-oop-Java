package thread;

import animals.Animal;

public class RegularTournament extends Tournament {
    Boolean startSignal;
    Scores scores;

    public RegularTournament(Animal[][] animals) {
        super(animals);
    }

    @Override
    public void setup(Animal[][] animals) throws InterruptedException {
        System.out.println("RegularTour setup");
        scores = new Scores();
        startSignal = false;
        AnimalThread[][] animalThread = new AnimalThread[animals.length][];


        for (int i = 0; i < animals.length; i++) {
            animalThread[i]= new AnimalThread[animals[i].length];
            for (int j = 0; j < animals[i].length; j++) {
                System.out.println("RegularTour setup Loop " + i + " " + j + "Build animal");
                Boolean endSignal = false;

                Animal tempAnimal = animals[i][j];
                System.out.println(tempAnimal.getName());
                animalThread[i][j] = new AnimalThread(tempAnimal, -1, startSignal, endSignal);
                Thread animalThreads = new Thread(animalThread[i][j]);
                animalThreads.start();
                Referee ref = new Referee(animals[i][j].getName(), scores, endSignal);
                Thread refThread = new Thread(ref);
                refThread.start();
            }
            TournamentThread thread = new TournamentThread(animalThread, scores, startSignal);
            super.setTournamentThread(thread);
            super.tournamentThread.run();
        }

        System.out.println("RegularTour setup Loop End func");

    }
}
