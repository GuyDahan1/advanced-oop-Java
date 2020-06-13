package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

/**
 * Presents Whale animal object
 */
public class  Whale extends WaterAnimal {

    private static final String sound = "Splash";
    private String foodType;

    /**
     * Creates Whale object with the given arguments.
     *
     * @param name     A given name of Whale object.
     * @param speed    A given speed of Whale object.
     * @param position A given Point object of Whale'a object location in space.
     * @see gen,Medal,Point
     */
    public Whale(String name,double speed, Point position, CompetitionPanel pan, String choice, int energyPerMeter) {
        super(name,speed, position, pan, "whale", choice,energyPerMeter);
        this.foodType = "ANYTHING !";
    }


    /**
     * Sets this Whale's food type.
     *
     * @param food A given value that is used to initialize this food type.
     * @return A boolean if the food type was initialized or not.
     */
    protected boolean setFoodType(String food) {
        this.foodType = food;
        return true;
    }

    /**
     * Gets this Whale's object food type.
     *
     * @return the object's current food type.
     */
    public String getFoodType() {
        return this.foodType;
    }

    /**
     * @return readable info of this Whale object.
     */
    public String toString() {
        return super.toString() + "\nFood Type : " + this.foodType;
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
