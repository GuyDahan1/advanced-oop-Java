package thread;

import animals.Animal;

public class AnimalThread implements Runnable{

    Animal participant;//this Animal
    double neededDistance;//the distance between the start point to the end point
    Boolean startFlag;//Obj boolean
    Boolean finishFlag;//Obj boolean

    public AnimalThread(Animal participant,double neededDistance){
        this.participant=participant;
        this.neededDistance=neededDistance;
    }

    public void run() {


    }
}
