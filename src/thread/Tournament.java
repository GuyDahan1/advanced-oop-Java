package thread;

import Graphics.CompetitionFrame;
import animals.Animal;

public abstract class Tournament {

    protected TournamentThread tournamentThread;
    protected CompetitionFrame frame;


    public Tournament(Animal[][] animals, CompetitionFrame frame) {
        this.frame = frame;
            setup(animals);
    }

    public abstract void setup(Animal[][] animals);

    public TournamentThread getTournamentThread() {
        return tournamentThread;
    }

    public void setTournamentThread(TournamentThread tournamentThread) {
        this.tournamentThread = tournamentThread;
    }

}
