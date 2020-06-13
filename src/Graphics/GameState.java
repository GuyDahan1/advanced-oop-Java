package Graphics;

/**
 * Holds the game's state progress.
 */
public enum GameState {
    /**
     * "choosing the competition type" state
     */
    CHOOSING_COMP_TYPE,
    /**
     * "choosing the competition's first animal" state
     */
    CHOOSING_COMP_FIRST_ANIMAL,
    /**
     * "choosing the competition's animals" state
     */
    CHOOSING_COMP_ANIMALS,
    /**
     * "competition is on" state
     */
    COMPETING,
    /**
     * "*clear* button was clicked" state
     */
    CLEARED
}
