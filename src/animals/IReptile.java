package animals;

/**
 * this Interface should be implemented only by Animal objects that are a kind of reptiles.
 * such as snakes,lizards,chameleon & etc.
 */
interface IReptile {
    static final int MAX_SPEED = 5;

    /**
     * Speeds up a reptile animal object, until a maximum amount of speed.
     *
     * @param Speed - A given speed that will increase the current speed.
     * @return boolean value if increasing speed succeeded or not.
     */
    boolean speedUp(int Speed);
}