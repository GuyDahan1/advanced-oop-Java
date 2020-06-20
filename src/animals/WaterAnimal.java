package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

/**
 * Represents WaterAnimal object
 */
abstract public class  WaterAnimal extends Animal {


    /**
     * Creates WaterAnimal object with the given arguments.
     *
     * @param name     A given name of WaterAnimal object.
     * @param speed    A given speed of WaterAnimal object.
     * @param position A given Point object of WaterAnimal's object location in space.
     * @see gen,Medal,Point
     */
    public WaterAnimal(String name, double speed, Point position, CompetitionPanel pan, String type, String choice, int energyPerMeter,gen gender) {
        super(name, speed, position, pan, type, choice,energyPerMeter,gender);
    }

    @Override
    public String getType() {
        return super.type;
    }

    public String[] getAnimalInfo() {
        return new String[]{getName(), "Water Animal", getType(), String.valueOf(getSpeed()), String.valueOf(maxEnergy), String.valueOf(getTotalDistance()), String.valueOf(energyPerMeter)};
    }

    public String getFamilyType(){
        return "WaterAnimal";
    }
}
