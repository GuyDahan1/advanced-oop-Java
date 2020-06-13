package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

/**
 * Represents TerrestrialAnimal object
 */
abstract public class  TerrestrialAnimal extends Animal {
    private int noLegs;

    /**
     * Creates TerrestrialAnimal object with the given arguments.
     *
     * @param name     A given name of TerrestrialAnimal object.
     * @param gender   A given gender reference of TerrestrialAnimal object.
     * @param weight   A given weight value of TerrestrialAnimal object.
     * @param speed    A given speed of TerrestrialAnimal object.
     * @param medals   A given array of Medal objects of TerrestrialAnimal object.
     * @param position A given Point object of TerrestrialAnimal's object location in space.
     * @param legs     A given number of legs of TerrestrialAnimal object.
     * @see gen,Medal,Point
     */
    public TerrestrialAnimal(String name,double speed,Point position, CompetitionPanel pan, String type, String choice, int energyPerMeter,gen gender) {
        super(name, speed,position, pan, type, choice,energyPerMeter,gender);
        this.setNoLegs(4);
    }


    /**
     * Gets this TerrestrialAnimal's object noLegs.
     *
     * @return this object's noLegs.
     */
    public int getNoLegs() {
        return this.noLegs;
    }

    /**
     * Sets this TerrestrialAnimal's noLegs.
     *
     * @param legs A given value that is used to initialize this name.
     * @return A boolean if the noLegs was initialized or not.
     */
    protected boolean setNoLegs(int legs) {
        if (legs < 0) {
            this.noLegs = 0;
            return false;
        }
        this.noLegs = legs;
        return true;
    }


    /**
     * Makes this TerrestrialAnimal dive with a given Integer.
     *
     * @param p A given Point object that is used to move the object to.
     * @return A boolean if this TerrestrialAnimal object was initialized or not.
     * @see Point
     */
    @Override
    public double move(Point p) {
        if (p.getY() != this.getPosition().getY()) {
            System.out.println("TerrestrialAnimals can't fly or dive");
            return this.getTotalDistance();
        }
        return super.move(p);
    }

    @Override
    public String getType() {
        return super.type;
    }

    public String[] getAnimalInfo() {
        return new String[]{getName(), "Terrestrial Animal", getType(), String.valueOf(getSpeed()), String.valueOf(maxEnergy), String.valueOf(getTotalDistance()), String.valueOf(energyPerMeter)};
    }
}
