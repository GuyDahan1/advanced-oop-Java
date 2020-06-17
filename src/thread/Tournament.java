package thread;

import animals.Animal;

public abstract class Tournament {

    protected TournamentThread tournamentThread;

    public Tournament(Animal[][] animals){
        try {
            setup(animals);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract void setup(Animal[][] animals) throws InterruptedException;

    public TournamentThread getTournamentThread() {
        return tournamentThread;
    }

    public void setTournamentThread(TournamentThread tournamentThread) {
        this.tournamentThread = tournamentThread;
    }
}
