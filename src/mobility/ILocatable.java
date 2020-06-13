package mobility;


public interface ILocatable {
    /**
     * Gets The object's current location.
     * this method should be implements if a class is "move-able".
     *
     * @return The object's current Point object location.
     */
    Point getPosition();

    /**
     * Sets The object's current location.
     * this method should be implements if a class is "move-able".
     *
     * @param p A given Point object which used to initialize this Point object.
     * @return A boolean value if initialization succeeded or not.
     */
    boolean setPosition(Point p);
}
