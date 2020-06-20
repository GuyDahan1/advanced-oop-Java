package animals;

import Graphics.CompetitionPanel;
import mobility.Point;

/**
 * Presents Eagle object
 */
public class Eagle extends AirAnimal {

    private static final String sound = "Clack-wack-chack";

    /**
     * Creates Eagle object with the given arguments.
     *
     * @param name     A given name of Eagle object.
     * @param speed    A given speed of Eagle object.
     * @param position A given Point object of Eagle's object location in space.
     * @param choice   A given image choice of Eagle object.
     * @param pan      A given CompetitionPanel of Eagle object.
     * @see Point,CompetitionPanel
     */
    public Eagle(String name, double speed, Point position, CompetitionPanel pan, String choice, int energyPerMeter,gen gender) {
        super(name, speed, position, pan, "eagle", choice,energyPerMeter,gender);
    }

    /**
     * @return readable info of this Eagle object.
     */
    @Override
    public String toString() {
        return super.toString() + "\naltitudeOfFlight  : ";
    }

    /**
     * Gets this Eagle's object sound.
     *
     * @return this object's sound.
     */
    @Override
    public String getSound() {
        return sound;
    }

}
