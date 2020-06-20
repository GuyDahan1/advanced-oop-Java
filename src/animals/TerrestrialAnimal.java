package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

/**
 * Represents TerrestrialAnimal object
 */
abstract public class  TerrestrialAnimal extends Animal {

    /**
     * Creates TerrestrialAnimal object with the given arguments.
     *
     * @param name     A given name of TerrestrialAnimal object.
     * @param gender   A given gender reference of TerrestrialAnimal object.
     * @param speed    A given speed of TerrestrialAnimal object.
     * @param position A given Point object of TerrestrialAnimal's object location in space.
     * @see gen,Medal,Point
     */
    public TerrestrialAnimal(String name,double speed,Point position, CompetitionPanel pan, String type, String choice, int energyPerMeter,gen gender) {
        super(name, speed,position, pan, type, choice,energyPerMeter,gender);
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
        if(currentEnergy>0){
            return super.move(p);
        }
        else return super.move(getPosition());
    }

    @Override
    public String getType() {
        return super.type;
    }

    public String[] getAnimalInfo() {
        return new String[]{getName(), "Terrestrial Animal", getType(), String.valueOf(getSpeed()), String.valueOf(maxEnergy), String.valueOf(getTotalDistance()), String.valueOf(energyPerMeter)};
    }


    public String getFamilyType(){
        return "TerrestrialAnimal";
    }
}
