package animals;

import Graphics.CompetitionPanel;
import Graphics.IDrawable;
import Olympics.Medal;
import mobility.ILocatable;
import mobility.Mobile;
import mobility.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Represents animal object
 */
public abstract class Animal extends Mobile implements IDrawable, IAnimal, ILocatable {
    private String name;
    private gen gender;
    private double weight;
    private double speed;
    private Medal[] medals;
    protected String type;

    protected int currentEnergy;
    protected int size;
    protected Orientation orientation;
    protected int maxEnergy;
    protected int energyPerMeter;
    protected CompetitionPanel pan;
    protected BufferedImage eastImg, westImg, southImg, northImg;
    protected Graphics g;

    /**
     * Creates Animal object with the given arguments.
     *
     * @param name      A given name of Animal object.
     * @param speed     A given speed of Animal object.
     * @param position  A given Point object of Animal's object location in space.
     * @param imgChoice A given image choice of Animal object.
     * @param pan       A given CompetitionPanel of Animal object.
     * @param type      A given animal type of Animal object.
     * @see gen,Medal,Point
     */
    public Animal(String name, double speed, Point position, CompetitionPanel pan, String type, String imgChoice , int energyPerMeter,gen gender) {
        super(position);
        this.setName(name);
        this.setGender(gender);
        this.setWeight(weight);
        this.setSpeed(speed);
        this.setMedals(null);
        this.type = type;

        Random rnd = new Random();
        this.orientation = Orientation.E;
        size = 65;
        this.energyPerMeter=energyPerMeter;
        maxEnergy = rnd.nextInt(1200) + 300;
        currentEnergy = maxEnergy;
        this.pan = pan;

        loadImages(imgChoice);
    }

    /**
     * Creates Animal object with the given arguments.
     *
     * @param name   A given name of Animal object.
     * @param speed  A given speed of Animal object.
     * @param medals A given array of Medal objects of Animal object.
     * @see gen,Medal
     */
    public Animal(String name, double speed, String type, Medal[] medals) {
        super();
        this.setName(name);
        this.setGender(gen.Male);
        this.setWeight(15);
        this.setSpeed(speed);
        this.setMedals(medals);
    }

    /**
     * Gets this Animal's object sound.
     *
     * @return this object's sound.
     */
    abstract public String getSound();

    /**
     * Gets this Animal's object name.
     *
     * @return this object's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets this Animal's object gender.
     *
     * @return this object's gender.
     */
    public gen getGender() {
        return this.gender;
    }

    /**
     * Gets this Animal's object weight.
     *
     * @return this object's weight.
     * @see gen
     */
    public double getWeight() {
        return this.weight;
    }


    /**
     * Gets this Animal's object speed.
     *
     * @return this object's speed.
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Gets this Animal's object medals.
     *
     * @return this object's array of medals objects.
     * @see Medal
     */
    public Medal[] getMedals() {
        return medals;
    }

    /**
     * Sets this Animal's name.
     *
     * @param name A given value that is used to initialize this name.
     * @return A boolean if the name was initialized or not.
     * @see Medal
     */
    protected boolean setName(String name) {
        if (name.length() > 0) {
            this.name = name;
            return true;
        }
        this.name = "- Empty name - ";
        return false;
    }

    public abstract String getType();

    /**
     * Sets this Animal's gender enum.
     *
     * @param gender A given enum value that is used to initialize this gender .
     * @return A boolean if the gender was initialized or not.
     * @see gen
     */
    protected boolean setGender(gen gender) {
        this.gender = gender;
        return true;
    }

    /**
     * Sets this Animal's weight.
     *
     * @param weight A given value that is used to initialize this weight .
     * @return A boolean if the weight was initialized or not.
     */
    protected boolean setWeight(double weight) {
        if (weight > 0) {
            this.weight = weight;
            return true;
        }
        this.weight = 0;
        return false;
    }

    /**
     * Sets this Animal's speed.
     *
     * @param speed A given value that is used to initialize this speed .
     * @return A boolean if the speed was initialized or not.
     */
    protected boolean setSpeed(double speed) {
        if (speed > 0) {
            this.speed = speed;
            return true;
        }
        this.speed = 0;
        return false;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public abstract String[] getAnimalInfo();

    /**
     * Sets this Animal's array of medals objects.
     *
     * @param medals A given array of Medal objects that is used to initialize this medals .
     * @return A boolean if this medals was initialized or not.
     * @see Medal
     */
    protected boolean setMedals(Medal[] medals) {
        if (medals != null) {
            this.medals = medals.clone();
            return true;
        }
        this.medals = null;
        return false;
    }

    public Graphics getG() {
        return g;
    }

    /**
     * Returns a readable String of this array od medals.
     *
     * @return this array of medals' objects as readable String.
     */
    public String MedalsToString() {
        StringBuilder info = new StringBuilder();
        if (this.medals == null)
            return "None";

        for (int i = 0; i < medals.length; ++i) {
            if (medals.length > 1) {
                String headLine = "\n\t MEDAL NO " + (i + 1) + " -";
                info.append(headLine);
            }
            info.append(medals[i].toString());
        }
        return info.toString();
    }

    public String toString() {
        return "Name : " + this.getName() + "\nGender : " + this.getGender() + "\nWeight : " + this.getWeight()
                + "\nSpeed : " + this.getSpeed() + "\nMedals : " + this.MedalsToString() + "\nLocation : "
                + super.getPosition();
    }

    /**
     * Makes this Animal object's sound.
     */
    public void makeSound() {
        System.out.println(this.getName() + " said " + getSound());
    }

    public void setCurrentEnergy(int Energy) {
        this.currentEnergy = Energy;
    }

    @Override
    public void loadImages(String nm) {

        try {
            this.eastImg = ImageIO.read(new File(PICTURE_PATH + nm + "E.png"));
            this.westImg = ImageIO.read(new File(PICTURE_PATH + nm + "W.png"));
            this.northImg = ImageIO.read(new File(PICTURE_PATH + nm + "N.png"));
            this.southImg = ImageIO.read(new File(PICTURE_PATH + nm + "S.png"));
        } catch (IOException e) {
            try {
                this.eastImg = ImageIO.read(new File(PICTURE_PATH + nm + "E.png"));
                this.westImg = ImageIO.read(new File(PICTURE_PATH + nm + "W.png"));

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }

    public void drawObject(Graphics g) {
        this.g = g;
        if (orientation == Orientation.E)  // animal move to the east side
            g.drawImage(eastImg, super.getPosition().getX() - size / 10, super.getPosition().getY() - size / 10, size * 2, size, pan);
        else if (orientation == Orientation.W) // animal move to the south side
            g.drawImage(westImg, super.getPosition().getX() - size / 10, super.getPosition().getY() - size / 10, size * 2, size, pan);
        else if (orientation == Orientation.N) // animal move to the west side
            g.drawImage(northImg, super.getPosition().getX() - size / 10, super.getPosition().getY() - size / 10, size * 2, size, pan);
        else if (orientation == Orientation.S) // animal move to the north side
            g.drawImage(southImg, super.getPosition().getX() - size / 10, super.getPosition().getY() - size / 10, size * 2, size, pan);
    }

    @Override
    public boolean eat(int energy) {
        if (energy <= 0 || energy >= maxEnergy)
            return false;
        else {
            setCurrentEnergy(energy);
            return true;
        }
    }
}

