package designPatterns;

import Graphics.CompetitionPanel;
import animals.*;
import mobility.Point;

public class AnimalFactory {
    public static final String Alligator = "Alligator";
    public static final String Cat = "Cat";
    public static final String Dog = "Dog";
    public static final String Dolphin = "Dolphin";
    public static final String Eagle = "Eagle";
    public static final String Pigeon = "Pigeon";
    public static final String Snake = "Snake";
    public static final String Whale = "Whale";

    public Animal getAnimal(String animalName, double speed,int energyPerMeter , Point staarPoint, CompetitionPanel panel, String ImgChoice,gen gender){
        if(ImgChoice.toLowerCase().contains(Alligator.toLowerCase()))
            return new Alligator(animalName,speed,staarPoint,panel,ImgChoice,energyPerMeter,gender);
        else if(ImgChoice.toLowerCase().contains(Cat.toLowerCase()))
            return new Cat(animalName,speed,staarPoint,panel,ImgChoice,energyPerMeter,gender);
        else if(ImgChoice.toLowerCase().contains(Dog.toLowerCase()))
            return new Dog(animalName,speed,staarPoint,panel,ImgChoice,energyPerMeter,gender);
        else if(ImgChoice.toLowerCase().contains(Dolphin.toLowerCase()))
            return new Dolphin(animalName,speed,staarPoint,panel,ImgChoice,energyPerMeter,gender);
        else if(ImgChoice.toLowerCase().contains(Eagle.toLowerCase()))
            return new Eagle(animalName,speed,staarPoint,panel,ImgChoice,energyPerMeter,gender);
        else if(ImgChoice.toLowerCase().contains(Pigeon.toLowerCase()))
            return new Pigeon(animalName,speed,staarPoint,panel,ImgChoice,energyPerMeter,gender);
        else if(ImgChoice.toLowerCase().contains(Snake.toLowerCase()))
            return new Snake(animalName,speed,staarPoint,panel,ImgChoice,energyPerMeter,gender);
        else if(ImgChoice.toLowerCase().contains(Whale.toLowerCase()))
            return new Whale(animalName,speed,staarPoint,panel,ImgChoice,energyPerMeter,gender);
        return null;
    }
}
