package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

/**
 * Presents Snake animal object
 */
public class  Snake extends TerrestrialAnimal implements IReptile {
    private final static String sound = "ssssssss";

    /**
     * Creates Snake object with the given arguments.
     *
     * @param name      A given name of Snake object.
     * @param speed     A given speed of Snake object.
     * @param position  A given Point object of Snake's object location in space.
     * @param pan       A given CompetitionPanel of Snake object.
     * @param choice    A given image choice of Snake object.
     * @see Point
     */
    public Snake(String name, double speed, Point position, CompetitionPanel pan, String choice, int energyPerMeter,gen gender) {
        super(name, speed,  position,  pan, "snake", choice,energyPerMeter,gender);
    }

    /**
     * Speeds up a Snake object, until a maximum amount of speed.
     *
     * @param speed - A given speed that will increase the current speed.
     * @return boolean value if increasing speed succeeded or not.
     */
    @Override
    public boolean speedUp(int speed) {
        if ((super.getSpeed() + speed < MAX_SPEED) && speed > 0) {
            super.setSpeed(super.getSpeed() + speed);
            return true;
        } else if (speed <= 0)
            return false;
        else
            System.out.println(this.getName() + "'s new speed is the MAX SPEED");
        this.setSpeed(MAX_SPEED);
        return true;
    }


    /**
     * @return readable info of this Snake object.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Gets this Snake's object sound.
     *
     * @return this object's sound.
     */
    @Override
    public String getSound() {
        return sound;
    }
}
