package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

/**
 * Presents Whale animal object
 */
public class  Whale extends WaterAnimal {

    private static final String sound = "Splash";

    /**
     * Creates Whale object with the given arguments.
     *
     * @param name     A given name of Whale object.
     * @param speed    A given speed of Whale object.
     * @param position A given Point object of Whale'a object location in space.
     * @see gen,Medal,Point
     */
    public Whale(String name,double speed, Point position, CompetitionPanel pan, String choice, int energyPerMeter,gen gender) {
        super(name,speed, position, pan, "whale", choice,energyPerMeter,gender);
    }

    /**
     * @return readable info of this Whale object.
     */
    public String toString() {
        return super.toString() ;
    }

    /**
     * Gets this Whale's object sound.
     *
     * @return this object's sound.
     */
    public String getSound() {
        return sound;
    }
}
