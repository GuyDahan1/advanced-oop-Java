package thread;

import Graphics.CompetitionFrame;
import animals.Animal;

public abstract class Tournament {

    protected TournamentThread tournamentThread;
    protected int tourIndex;
    protected CompetitionFrame frame;
    public Tournament(Animal[][] animals, CompetitionFrame frame,int index){
        this.frame=frame;
        tourIndex = index;
        setup(animals);
    }

    public abstract void setup(Animal[][] animals) ;

    public abstract void notifyTournamentThread();


        public void setTournamentThread(TournamentThread tournamentThread) {
        this.tournamentThread = tournamentThread;
    }
}