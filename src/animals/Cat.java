package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

/**
 * Presents Cat object
 */
public class Cat extends TerrestrialAnimal {
    private Boolean Castrated;
    private final String sound = "Meow";

    /**
     * Creates Cat object with the given arguments.
     *
     * @param name     A given name of Cat object.
     * @param speed    A given speed of Cat object.
     * @param position A given Point object of Cat's object location in space.
     * @see Medal,Point,gen
     */
    public Cat(String name, double speed, Point position, CompetitionPanel pan, String choice) {
        super(name, speed, position, pan, "cat", choice);
        this.Castrated = true;
    }

    /**
     * @return readable info of this Cat object.
     */
    @Override
    public String toString() {
        return super.toString() + "\nCastrated  : " + this.Castrated;
    }

    /**
     * Returns a boolean if Cat is castrated.
     *
     * @return this Cat's object Castrated boolean .
     */
    public boolean isCastrated() {
        return this.Castrated;
    }

    /**
     * Sets this Cat's Castrated.
     *
     * @param cast A given boolean value that is used to initialize this Castrated.
     * @return A boolean if the area was initialized or not.
     */
    public boolean setCastrated(boolean cast) {
        if (this.isCastrated())
            return false;
        this.Castrated = true;
        return true;
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

