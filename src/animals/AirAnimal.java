package animals;

import Graphics.CompetitionPanel;
import mobility.Point;

/**
 * Represents AirAnimal object
 */
abstract public class AirAnimal extends Animal {

    /***
     * Creates AirAnimal object with the given arguments.
     *
     * @param name     A given name of AirAnimal object.
     * @param speed    A given speed of AirAnimal object.
     * @param position A given Point object of AirAnimal's object location in space.
     * @param pan      A given CompetitionPanel of AirAnimal object.
     * @param type     A given animal type of AirAnimal object.
     * @param choice   A given image choice of AirAnimal object.
     * @see Point
     */
    public AirAnimal(String name, double speed, Point position, CompetitionPanel pan, String type, String choice, int energyPerMeter,gen gender) {
        super(name, speed, position, pan, type, choice,energyPerMeter,gender);

    }


    @Override
    public String getType() {
        return super.type;
    }

    public String[] getAnimalInfo() {
        return new String[]{getName(), " Air Animal", getType(), String.valueOf(getSpeed()), String.valueOf(maxEnergy), String.valueOf(getTotalDistance()), String.valueOf(energyPerMeter)};
    }

    public String getFamilyType(){
        return "AirAnimal";
    }

}
