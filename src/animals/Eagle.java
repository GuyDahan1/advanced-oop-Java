package animals;

import Graphics.CompetitionPanel;
import mobility.Point;

/**
 * Presents Eagle object
 */
public class Eagle extends AirAnimal {

    private double altitudeOfFlight;
    private static final int MAX_SPEED = 100;
    private final String sound = "Clack-wack-chack";

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
    public Eagle(String name, double speed, Point position, CompetitionPanel pan, String choice) {
        super(name, speed, position, pan, "eagle", choice);
        this.altitudeOfFlight = 800;
    }

    /**
     * Gets this Eagle object's altitudeOfFlight.
     *
     * @return this object's altitudeOfFlight.
     */
    public double getAltitudeOfFlight() {
        return altitudeOfFlight;
    }

    /**
     * Gets this Eagle object's maximum speed.
     *
     * @return this object's MAX_SPEED.
     */
    public int getMaxSpeed() {
        return MAX_SPEED;
    }

    /**
     * Sets this Eagle's altitudeOfFlight.
     *
     * @param altitude A given value that is used to initialize this altitudeOfFlight.
     * @return A boolean if this altitudeOfFlight was initialized or not.
     */
    protected boolean setAltitudeOfFlight(double altitude) {
        if (altitude > 0) {
            this.altitudeOfFlight = altitude;
            return true;
        }
        return false;
    }

    /**
     * @return readable info of this Eagle object.
     */
    @Override
    public String toString() {
        return super.toString() + "\naltitudeOfFlight  : " + this.altitudeOfFlight;
    }

    /**
     * Gets this Eagle's object sound.
     *
     * @return this object's sound.
     */
    @Override
    public String getSound() {
        return this.sound;
    }

}
