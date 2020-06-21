package thread;

import Graphics.CompetitionFrame;
import animals.Animal;
import animals.Orientation;
import mobility.Point;

import java.util.concurrent.atomic.AtomicBoolean;

public class AnimalThread implements Runnable {
    private final Animal participant;//this Animal
    private final double neededDistance;//the distance between the start point to the end point
    private final AtomicBoolean startFlag;//Obj boolean
    private final AtomicBoolean finishFlag;//Obj boolean
    private final Referee ref;
    private Tournament tour;
    private boolean regularTournamentBoolean;
    int index;
    boolean firstAnimal;

    public AnimalThread(Animal participant, double neededDistance, AtomicBoolean start, AtomicBoolean end, Referee ref, boolean regularTour, Tournament tour) {
        this.participant = participant;
        this.neededDistance = neededDistance;
        startFlag = start;
        finishFlag = end;
        this.ref = ref;
        regularTournamentBoolean = regularTour;
        this.tour = tour;
    }

    public AnimalThread(Animal participant, double neededDistance, AtomicBoolean start, AtomicBoolean end, Referee ref, boolean regularTour, Tournament tour, int index) {
        this(participant, neededDistance, start, end, ref, regularTour, tour);
        this.index = index;
        firstAnimal = true;

    }

    @Override
    public void run() {
        double distanceY = Math.abs((CompetitionFrame.getStartPoint()[0].getY() - CompetitionFrame.getStartPoint()[1].getY()));
        double distanceX = Math.abs((CompetitionFrame.getStartPoint()[0].getX() - CompetitionFrame.getEndPoint()[1].getX()));
        System.out.println(participant.getPosition().toString());
        synchronized (this) {
            if (!startFlag.get()) {
                try {
                    System.out.println("AnimalThread Wait");
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("secondAnimalWake");
                    notify();
                }
            }
        }
        double tournamentValue = 1;
        if (regularTournamentBoolean) {
            tournamentValue =0.5 ;
        }
        while (startFlag.get()) {
            while (!finishFlag.get()) {
                synchronized (this) {
                    if (participant.getFamilyType().contains("Air") || participant.getFamilyType().contains("Water")) {
                        if (participant.getTotalDistance() >= tournamentValue * neededDistance) {
                            participant.setOrientation(Orientation.W);
                        }
                    } else if (participant.getFamilyType().contains("Terr")) {

                        if (participant.getTotalDistance() >= distanceX) {
                            participant.setOrientation(Orientation.S);
                            if (participant.getTotalDistance() >= distanceX + distanceY) {
                                participant.setOrientation(Orientation.W);
                                if (participant.getTotalDistance() >= 2 * distanceX + distanceY) {
                                    participant.setOrientation(Orientation.N);
                                }
                            }
                        }
                    }
                    if (participant.getTotalDistance() >= this.neededDistance) {
                        synchronized (this) {
                            this.finishFlag.set(true);
                            if (firstAnimal) {
                                tour.notifyNextAnimal(index);
                            } else {
                                synchronized (ref) {
                                    ref.notify();
                                    System.out.println("ref.notify(); i am here");
                                }
                                System.out.println("before tour.notifyTournamentThread(); i am here");
                                tour.notifyTournamentThread();
                                System.out.println("after tour.notifyTournamentThread(); i am here");

                                try {
                                    System.out.println(participant.getName() + " is waiting");
                                    wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    double speed = participant.getSpeed();
                    Point position = participant.getPosition();

                    if (participant.getOrientation().equals(Orientation.E)) {
                        participant.move(new Point(position.getX() + (int) speed, position.getY()));
                    } else if (participant.getOrientation().equals(Orientation.N)) {
                        participant.move(new Point(position.getX(), position.getY() - (int) speed));
                    } else if (participant.getOrientation().equals(Orientation.S)) {
                        participant.move(new Point(position.getX(), position.getY() + (int) speed));
                    } else if (participant.getOrientation().equals(Orientation.W)) {
                        participant.move(new Point(position.getX() - (int) speed, position.getY()));
                    }
                    try {
                        //noinspection BusyWait
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    participant.energyConsumption();
                }
            }
        }
        startFlag.set(false);
    }


}
