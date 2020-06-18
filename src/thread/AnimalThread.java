package thread;

import Graphics.CompetitionFrame;
import animals.Animal;
import animals.Orientation;
import mobility.Point;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AnimalThread implements Runnable {

    private Animal participant;//this Animal
    private double neededDistance;//the distance between the start point to the end point
    private Boolean startFlag;//Obj boolean
    private Boolean finishFlag;//Obj boolean
    private Random rnd;
    private CompetitionFrame myFrame;
    private int animalIndex;

    public AnimalThread(Animal participant, double neededDistance, Boolean start, Boolean end, CompetitionFrame frame, int index) {
        this.participant = participant;
        this.neededDistance = neededDistance;
        startFlag = start;
        finishFlag = end;
        myFrame = frame;
        animalIndex = index;
        rnd = new Random();


    }

    public void setNeededDistance() {
        if (participant.getFamilyType().contains("Water")) {
            neededDistance = participant.calcDistance(CompetitionFrame.getEndPointWater()[animalIndex]) * 2;
        } else if (participant.getFamilyType().contains("Air")) {
            neededDistance = participant.calcDistance(CompetitionFrame.getEndPoint()[animalIndex]) * 2;
        } else if (participant.getFamilyType().contains("Terr")) {
            neededDistance = participant.calcDistance(CompetitionFrame.getEndPoint()[animalIndex]) * 2 + participant.calcDistance(CompetitionFrame.getStartPoint()[animalIndex + 1]) * 2;
        }

    }


    @Override
    public void run() {
        setNeededDistance();
        System.out.println(participant.getPosition().toString());
        synchronized (this) {
            if (!startFlag) {
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
//        while (startFlag) {
        System.out.println("STARTFLAGGG");
        while (!finishFlag) {
            //TODO boolean RegularTour/CourTour
            System.out.println("FINISHFLAG");
            System.out.println(participant.getTotalDistance());
            synchronized (this) {
                if (participant.getFamilyType().contains("Air") || participant.getFamilyType().contains("Water")) {
                    if (participant.getTotalDistance() >= 0.5 * neededDistance) {
                        participant.setOrientation(Orientation.W);
                    }
                } else if (participant.getFamilyType().contains("Terr")) {

                    double distanceY = Math.abs((CompetitionFrame.getStartPoint()[0].getY() - CompetitionFrame.getStartPoint()[1].getY()));
                    double distanceX = Math.abs((CompetitionFrame.getStartPoint()[0].getX() - CompetitionFrame.getEndPoint()[1].getX()));
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
                    this.finishFlag = true;
                    notify();

                }
                double speed = participant.getSpeed();
                Point position = participant.getPosition();
                Image img = null;
                if (participant.getOrientation().equals(Orientation.E)) {
                    participant.move(new Point(position.getX() + (int) speed, position.getY()));
                    img = participant.getEastImg();
                } else if (participant.getOrientation().equals(Orientation.N)) {
                    participant.move(new Point(position.getX(), position.getY() - (int) speed));
                    img = participant.getNorthImg();
                } else if (participant.getOrientation().equals(Orientation.S)) {
                    participant.move(new Point(position.getX(), position.getY() + (int) speed));
                    img = participant.getWestImg();
                } else if (participant.getOrientation().equals(Orientation.W)) {
                    participant.move(new Point(position.getX() - (int) speed, position.getY()));
                    img = participant.getSouthImg();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(participant.getPosition().toString());


            }
        }//TODO sleep
//        }
    }
}
