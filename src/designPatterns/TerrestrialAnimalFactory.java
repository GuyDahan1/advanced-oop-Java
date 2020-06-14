package designPatterns;

import Graphics.CompetitionPanel;
import animals.*;
import mobility.Point;

public class TerrestrialAnimalFactory extends SpeciesFactory{

    public static final String Alligator = "Alligator";
    public static final String Cat = "Cat";
    public static final String Dog = "Dog";
    public static final String Snake = "Snake";


    public Animal getAnimal(String animalName, double speed, int energyPerMeter, Point staarPoint, CompetitionPanel panel, String ImgChoice, gen gender) {
        if (ImgChoice.toLowerCase().contains(Alligator.toLowerCase()))
            return new Alligator(animalName, speed, staarPoint, panel, ImgChoice, energyPerMeter, gender);
        else if (ImgChoice.toLowerCase().contains(Cat.toLowerCase()))
            return new Cat(animalName, speed, staarPoint, panel, ImgChoice, energyPerMeter, gender);
        else if (ImgChoice.toLowerCase().contains(Dog.toLowerCase()))
            return new Dog(animalName, speed, staarPoint, panel, ImgChoice, energyPerMeter, gender);
        else if (ImgChoice.toLowerCase().contains(Snake.toLowerCase()))
            return new Snake(animalName, speed, staarPoint, panel, ImgChoice, energyPerMeter, gender);
        return null;
    }
}