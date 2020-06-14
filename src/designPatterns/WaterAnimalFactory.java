package designPatterns;

import Graphics.CompetitionPanel;
import animals.*;
import mobility.Point;

public class WaterAnimalFactory extends SpeciesFactory{
    public static final String Alligator = "Alligator";
    public static final String Dolphin = "Dolphin";
    public static final String Whale = "Whale";

    public Animal getAnimal(String animalName, double speed,int energyPerMeter , Point staarPoint, CompetitionPanel panel, String ImgChoice,gen gender){
        if(ImgChoice.toLowerCase().contains(Alligator.toLowerCase()))
            return new Alligator(animalName,speed,staarPoint,panel,ImgChoice,energyPerMeter,gender);
        else if(ImgChoice.toLowerCase().contains(Dolphin.toLowerCase()))
            return new Dolphin(animalName,speed,staarPoint,panel,ImgChoice,energyPerMeter,gender);
        else if(ImgChoice.toLowerCase().contains(Whale.toLowerCase()))
            return new Whale(animalName,speed,staarPoint,panel,ImgChoice,energyPerMeter,gender);
        return null;
    }
}
