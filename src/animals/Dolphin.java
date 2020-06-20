package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

/**
 * Presents  Dolphin object
 */
public class Dolphin extends WaterAnimal {
    private static final String sound = "Click-click";

    /**
     * Creates Dolphin object with the given arguments.
     *
     * @param name     A given name of Dolphin object.
     * @param speed    A given speed of Dolphin object.
     * @param position A given Point object of Dolphin's object location in space.
     * @see Medal,gen,Point
     */
    public Dolphin(String name, double speed, Point position, CompetitionPanel pan, String choice, int energyPerMeter,gen gender) {
        super(name, speed, position, pan, "dolphin", choice,energyPerMeter,gender);
    }

    /**
     * @return readable info of this Dolphin object.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Gets this Dolphin's object sound.
     *
     * @return this object's sound.
     */
    @Override
    public String getSound() {
        return sound;
    }

}
