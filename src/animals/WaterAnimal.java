package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

/**
 * Represents WaterAnimal object
 */
abstract public class  WaterAnimal extends Animal {
    static final double MAX_DIVE = -800.0;
    private double diveDept;


    /**
     * Creates WaterAnimal object with the given arguments.
     *
     * @param name     A given name of WaterAnimal object.
     * @param speed    A given speed of WaterAnimal object.
     * @param position A given Point object of WaterAnimal's object location in space.
     * @see gen,Medal,Point
     */
    public WaterAnimal(String name, double speed, Point position, CompetitionPanel pan, String type, String choice, int energyPerMeter) {
        super(name, speed, position, pan, type, choice,energyPerMeter);
        this.diveDept = 0;
    }

    public double getDiveDept() {
        return diveDept;
    }

    /**
     * Sets this WaterAnimal's diveDept.
     *
     * @param diveDept A given value that is used to initialize this name.
     * @return A boolean if the diveDept was initialized or not.
     */
    public boolean setDiveDept(double diveDept) {
        this.diveDept = diveDept;
        return true;
    }

    /**
     * Gets this WaterAnimal's object MAX DIVE.
     *
     * @return this object's diveDept.
     */
    public static double getMaxDive() {
        return MAX_DIVE;
    }

    /**
     * Makes this WaterAnimal dive with a given Integer.
     *
     * @param dive A given value that is used to dive to.
     * @return A boolean if the diveDept was initialized or not.
     */
    private boolean Dive(double dive) {
        if (dive < 0) {
            System.out.println("WaterAnimal " + super.getName() + "cannot dive negative value.");
            return false;
        }

        if ((-dive) + this.diveDept < WaterAnimal.MAX_DIVE) {
            System.out.println("WaterAnimal " + super.getName() + "cannot dive under" + WaterAnimal.MAX_DIVE);
            System.out.println("current depth dived  " + this.diveDept);
            this.diveDept = -WaterAnimal.MAX_DIVE;
            return true;
        }
        this.diveDept -= dive;
        System.out.println(super.getName() + " the " + getType() + " dived " + this.diveDept);
        return true;
    }

    @Override
    public String getType() {
        return super.type;
    }

    public String[] getAnimalInfo() {
        return new String[]{getName(), "Water Animal", getType(), String.valueOf(getSpeed()), String.valueOf(maxEnergy), String.valueOf(getTotalDistance()), String.valueOf(energyPerMeter)};
    }
}
