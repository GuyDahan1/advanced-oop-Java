package thread;

import animals.Animal;

public abstract class Tournament {

    private String TourName;
    protected TournamentThread tournamentThread;


    public Tournament(){
    }


    public abstract void setup(Animal[][] animals);
}
