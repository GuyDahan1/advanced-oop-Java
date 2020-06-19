package thread;

import Graphics.*;
import animals.Animal;
import animals.Orientation;
import mobility.Point;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class AnimalThread implements Runnable {

    private Animal participant;//this Animal
    private double neededDistance;//the distance between the start point to the end point
    private AtomicBoolean startFlag;//Obj boolean
    private AtomicBoolean finishFlag;//Obj boolean
    private Random rnd;
    private CompetitionPanel myPanel;

    public AnimalThread(Animal participant, double neededDistance, AtomicBoolean start, AtomicBoolean end, CompetitionPanel panel) {
        this.participant = participant;
        this.neededDistance = neededDistance;
        startFlag = start;
        finishFlag = end;
        myPanel = panel;
        rnd = new Random();


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
                    e.printStackTrace();
                    System.out.println("ERROR IN WAIT");
                }
            }
        }
        System.out.println("AnimalThread Wait Finish");
        while (startFlag.get()) {
            System.out.println("STARTFLAGGG");
            while (!finishFlag.get()) {
                //TODO boolean RegularTour/CourTour
                System.out.println("FINISHFLAG");
                System.out.println(participant.getTotalDistance());
                synchronized (this) {
                    if (participant.getFamilyType().contains("Air") || participant.getFamilyType().contains("Water")) {
                        if (participant.getTotalDistance() >= 0.5 * neededDistance) {
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
                        this.finishFlag.set(true);
                        synchronized (Referee.class) {
                            Referee.class.notifyAll();
                        }
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    double speed = participant.getSpeed();
                    Point position = participant.getPosition();
                    Image img = null;
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
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(participant.getPosition().toString());
                }
            }
        }//TODO sleep
    }
}
