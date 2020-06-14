package designPatterns;

import Graphics.CompetitionPanel;
import animals.*;
import mobility.Point;

public class AirAnimalFactory extends SpeciesFactory {
    public static final String Eagle = "Eagle";
    public static final String Pigeon = "Pigeon";

    public Animal getAnimal(String animalName, double speed, int energyPerMeter , Point staarPoint, CompetitionPanel panel, String ImgChoice, gen gender) {
        if(ImgChoice.toLowerCase().contains(Eagle.toLowerCase()))
            return new Eagle(animalName,speed,staarPoint,panel,ImgChoice,energyPerMeter,gender);
        else if(ImgChoice.toLowerCase().contains(Pigeon.toLowerCase()))
            return new Pigeon(animalName,speed,staarPoint,panel,ImgChoice,energyPerMeter,gender);
        return null;  }

}
