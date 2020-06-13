package mobility;

/**
 * Represents a point with 2 values - X and Y.
 */
public class Point implements Cloneable{
    private int x;
    private int y;

    /**
     * Creates a point with the specified x and y.
     *
     * @param x horizontal value of point.
     * @param y vertical value of point.
     */
    public Point(int x, int y) {
        if (!this.setX(x)) {
            System.out.println("Default x = 0");
        }
        if (!this.setY(y)) {
            System.out.println("Default y = 0");
        }
    }

    /**
     * Creates a point with the specified x and y.
     *
     * @param other - A given Point object which used to initialize this Point object.
     */
    public Point(Point other) {
        this.setX(other.getX());
        this.setY(other.getY());
    }

    /**
     * Gets the point's x value.
     *
     * @return this Point's object value of x.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the point's y value.
     *
     * @return this Point's object value of y.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Sets the point's x value.
     *
     * @param x An int containing the point's x value.
     * @return A boolean value if x was non-negative, or not.
     */
    public boolean setX(int x) {
        if (x >= 0) {
            this.x = x;
            return true;
        } else {
            this.x = 0;
            return false;
        }
    }

    /**
     * Sets the point's y value.
     *
     * @param y A int containing the point's x value.
     * @return A boolean value if x was non-negative, or not.
     */
    public boolean setY(int y) {
        if (y >= 0) {
            this.y = y;
            return true;
        } else {
            this.y = 0;
            return false;
        }
    }

    /**
     * @return readable info of Point object.
     */
    public String toString() {
        return "Point (x = " + getX() + " , y = " + getY() + " )";
    }
}
