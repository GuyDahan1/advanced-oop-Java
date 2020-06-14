package designPatterns;

import Graphics.CompetitionPanel;
import animals.Animal;
import animals.gen;
import mobility.Point;

public abstract class SpeciesFactory {

    public abstract Animal getAnimal(String animalName, double speed, int energyPerMeter , Point staarPoint, CompetitionPanel panel, String ImgChoice, gen gender);

}


