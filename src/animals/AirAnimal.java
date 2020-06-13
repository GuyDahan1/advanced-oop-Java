package animals;

import Graphics.CompetitionPanel;
import mobility.Point;

/**
 * Represents AirAnimal object
 */
abstract public class AirAnimal extends Animal {
      private double wingspan;

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
        this.setWingSpan(2);

    }

    /**
     * Sets this AirAnimal's wingspan.
     *
     * @param wings A given value that is used to initialize this wingspan.
     * @return A boolean if the wingspan was initialized or not.
     */
    protected boolean setWingSpan(double wings) {
        if (wings > 0) {
            this.wingspan = wings;
            return true;
        }
        this.wingspan = 0;
        return false;
    }

    /**
     * Gets this AirAnimal's object wingspan.
     *
     * @return this object's wingspan.
     */
    public double getWingspan() {
        return this.wingspan;
    }

    @Override
    public String getType() {
        return super.type;
    }

    public String[] getAnimalInfo() {
        return new String[]{getName(), " Air Animal", getType(), String.valueOf(getSpeed()), String.valueOf(maxEnergy), String.valueOf(getTotalDistance()), String.valueOf(energyPerMeter)};
    }
}
