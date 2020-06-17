package thread;

import animals.Animal;
import animals.Orientation;
import mobility.Point;

import java.util.Random;

public class AnimalThread implements Runnable {

    Animal participant;//this Animal
    double neededDistance;//the distance between the start point to the end point
    Boolean startFlag;//Obj boolean
    Boolean finishFlag;//Obj boolean
    Random rnd;

    public AnimalThread(Animal participant, double neededDistance, Boolean start, Boolean end) {
        if (participant != null)
            this.participant = participant;
        else {
            System.out.println("animal is null in AnimalThread CTOR");
            System.exit(-1);
        }
        this.neededDistance = neededDistance;
        startFlag = start;
        finishFlag = end;
        rnd = new Random();

    }

    public void run() {
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

        while (startFlag) {
            System.out.println("STARTFLAGGG");
            while (!finishFlag) {
                System.out.println("FINISHFLAG");
                synchronized (this) {
                    double speed = participant.getSpeed();
                    Point position = participant.getPosition();
                    if (participant.getOrientation().equals(Orientation.E))
                        participant.move(new Point(position.getX() + (int) speed, position.getY()));
                    if (participant.getOrientation().equals(Orientation.N))
                        participant.move(new Point(position.getX(), position.getY() - (int) speed));
                    if (participant.getOrientation().equals(Orientation.W))
                        participant.move(new Point(position.getX(), position.getY() + (int) speed));
                    if (participant.getOrientation().equals(Orientation.E))
                        participant.move(new Point(position.getX() - (int) speed, position.getY()));
                    participant.drawObject(participant.getG());
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }
}
