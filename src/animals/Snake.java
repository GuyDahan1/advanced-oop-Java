package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

/**
 * Presents Snake animal object
 */
public class  Snake extends TerrestrialAnimal implements IReptile {
    private final static String sound = "ssssssss";
    private final Poisonous poisonous;
    private double length;

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
        this.poisonous = Poisonous.Easy;
        setLength(20);
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
     * Gets this Snake object's Poisonous enum value.
     *
     * @return this object's Poisonous enum value.
     */
    public String getPoisonous() {
        return this.poisonous.toString();
    }

    /**
     * Gets this Snake object's length.
     *
     * @return this object's length.
     */
    public double getLength() {
        return this.length;
    }

    /**
     * Sets this Snake's length.
     *
     * @param len - A given value that is used to initialize length.
     * @return A boolean if the length was initialized or not.
     */
    protected boolean setLength(double len) {
        if (len > 0) {
            this.length = len;
            return true;
        }
        return false;
    }

    /**
     * @return readable info of this Snake object.
     */
    @Override
    public String toString() {
        return super.toString() + "\nPoisonous  : " + this.getPoisonous() + "\nlength : " + this.getLength();
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
