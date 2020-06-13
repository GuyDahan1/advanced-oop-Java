package mobility;

/**
 * Represents an abstract mobile.
 */
public abstract class Mobile implements ILocatable {
    private Point Position;
    private double totalDistance;

    /**
     * Creates a mobile with the specified location and distance.
     *
     * @param Loc A given location of Mobile object.
     *            default value of distance is always 0.
     */
    public Mobile(Point Loc) {
        this.Position = Loc;
        this.setTotalDistance(0);
    }

    /**
     * Creates a mobile with the specified location and distance.
     * <p>
     * default value of location is always 0.
     * default value of distance is always 0.
     */
    public Mobile() {
        this.Position = new Point(0, 0);
        this.setTotalDistance(0);
    }

    /**
     * Sets the mobile's object total of distance traveled.
     *
     * @param Dis A given distance of Mobile object.
     * @return A boolean value if a given distance is a non-negative ot not.
     */
    protected boolean setTotalDistance(double Dis) {
        if (Dis >= 0) {
            this.totalDistance = Dis;
            return true;
        }
        return false;
    }

    /**
     * Gets the mobile's object total of distance traveled.
     *
     * @return the object's total distance traveled.
     */
    public double getTotalDistance() {
        return this.totalDistance;
    }

    /**
     * Gets the mobile's object location.
     *
     * @return the object's current location.
     * @see Point
     */
    public Point getPosition() {
        return this.Position;
    }

    /**
     * Sets the mobile's location.
     *
     * @param p A given Point object that is used to initialize this location.
     * @return A boolean value if a given distance is a non-negative ot not.
     * @see Point
     */
    public boolean setPosition(Point p) {
        this.Position = p;
        return true;
    }

    /**
     * Adds a distance to this object's total distance.
     *
     * @param addDis A distance which need to be added to this distance.
     */
    protected void addTotalDistance(double addDis) {
        this.setTotalDistance(this.totalDistance + addDis);
    }


    /**
     * Calc the distance between this object's location and a given Point object.
     *
     * @param otherPoint A given Point object that is used to calculate the distance.
     * @return the calculated distance between this object's location and the given point.
     */
    public double calcDistance(Point otherPoint) {

        double Y = (otherPoint.getY() - this.getPosition().getY());
        double X = otherPoint.getX() - getPosition().getX();
        return Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2));
    }

    /**
     * Moves this object's location to a desired given point.
     *
     * @param p A given Point object that is used to move the object.
     * @return the calculated distance between this object's location and the given point.
     */
    public double move(Point p) {
        double distanceTraveled = this.calcDistance(p);
        this.addTotalDistance(distanceTraveled);
        this.setPosition(p);
        return distanceTraveled;
    }

}
