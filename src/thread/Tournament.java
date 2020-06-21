package thread;

import Graphics.CompetitionFrame;
import animals.Animal;

public abstract class Tournament {

    protected Thread tournamentThread;
    protected int tourIndex;
    protected CompetitionFrame frame;

    public Tournament(Animal[][] animals, CompetitionFrame frame, int index) {
        this.frame = frame;
        tourIndex = index;
        setup(animals);
    }

    public abstract void setup(Animal[][] animals);

    public void notifyTournamentThread() {
        synchronized (tournamentThread) {
            System.out.println(tournamentThread.getName() + " is a wake");
            tournamentThread.interrupt();
            System.out.println(tournamentThread.isAlive());
        }
    }
    public void setTournamentThread(Thread tournamentThread) {
        this.tournamentThread = tournamentThread;
    }


}