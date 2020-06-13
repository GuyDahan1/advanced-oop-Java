package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

/**
 * Presents Dog object
 */
public class Dog extends TerrestrialAnimal {
    private final String breed;
    private static final String sound = "Woof Woof";

    /**
     * Creates Dog object with the given arguments.
     *
     * @param name     A given name of Dog object.
     * @param speed    A given speed of Dog object.
     * @param position A given Point object of Dog's object location in space.
     * @see gen,Medal,Point
     */
    public Dog(String name, double speed, Point position, CompetitionPanel pan, String choice) {
        super(name, speed, position, pan, "dog", choice);
        this.breed = "breed";


        super.drawObject(this.pan.getG());
    }


    /**
     * Gets this Dog object's breed.
     *
     * @return this object's breed.
     */
    public String getBreed() {
        return breed;
    }

    /**
     * @return readable info of this Dog object.
     */
    @Override
    public String toString() {
        return super.toString() + "\nbreed : " + this.getBreed();
    }

    /**
     * Gets this Dog's object sound.
     *
     * @return this object's sound.
     */
    @Override
    public String getSound() {
        return sound;
    }
}
