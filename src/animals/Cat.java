package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

/**
 * Presents Cat object
 */
public class Cat extends TerrestrialAnimal {
    private static final String sound = "Meow";

    /**
     * Creates Cat object with the given arguments.
     *
     * @param name     A given name of Cat object.
     * @param speed    A given speed of Cat object.
     * @param position A given Point object of Cat's object location in space.
     * @see Medal,Point,gen
     */
    public Cat(String name, double speed, Point position, CompetitionPanel pan, String choice, int energyPerMeter,gen gender) {
        super(name, speed, position, pan, "cat", choice,energyPerMeter,gender);
    }

    /**
     * @return readable info of this Cat object.
     */
    @Override
    public String toString() {
        return super.toString() ;
    }


    /**
     * Gets this Cat's object sound.
     *
     * @return this object's sound.
     */
    @Override
    public String getSound() {
        return sound;
    }
}

