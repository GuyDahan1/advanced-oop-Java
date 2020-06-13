package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

/**
 * Presents Eagle Pigeon
 */
public class Pigeon extends AirAnimal {

    private final String family;
    private final String sound = "Arr-rar-rar-rar-raah";

    /**
     * Creates Pigeon object with the given arguments.
     *
     * @param name     A given name of Pigeon object.
     * @param speed    A given speed of Pigeon object.
     * @param position A given Point object of Pigeon's object location in space.
     * @param choice   A given image choice of Pigeon object.
     * @param pan      A given CompetitionPanel of Pigeon object.
     * @see gen,Medal,Point
     */
    public Pigeon(String name, double speed, Point position, CompetitionPanel pan,String choice) {
        super(name,speed,position,pan,"pigeon",choice);

        this.family = "";
    }

    /**
     * Gets this Pigeon object's family.
     *
     * @return this object's family.
     */
    public String getFamily() {
        return this.family;
    }

    /**
     * @return readable info of this Pigeon object.
     */
    @Override
    public String toString() {
        return super.toString() + "\nFamily  : " + this.family;
    }

    /**
     * Gets this Pigeon's object sound.
     *
     * @return this object's sound.
     */
    @Override
    public String getSound() {
        return this.sound;
    }
}


