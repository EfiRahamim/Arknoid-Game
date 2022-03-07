package geometryprimitives;

/**
 * @author Efraim Rahamim <efirahamim@gmail.com>
 * @version 1.1
 * @since 27.04.2020
 * ID 315392621
 * Representation of a point in space
 */
public class Point {
    //the x and y's values of the point
    private double x;
    private double y;

    /**
     * .
     * constructor with 2 numbers that represents a point
     *
     * @param x the 'x' value of the point
     * @param y the 'y' value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point
     * by using the formula for finding distance between two points.
     * if one of the points is null - return -1
     *
     * @param other the other point
     * @return the distance between this point to the other one. if one of the points is null - return -1.
     */
    public double distance(Point other) {
        //define the distance by using the formula
        double dis = Math.sqrt(Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2));
        return dis;
    }

    /**
     * .
     * equals -- checks if two points are equal
     *
     * @param other the other point
     * @return return true if the two points are equal, false otherwise
     */
    public boolean equals(Point other) {
        //checks if the values of x and y are equals
        if ((this.x == other.x) && (this.y == other.y)) {
            return true;
        }
        return false;
    }

    /**
     * .
     * getX - return the x value of this point
     *
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * .
     * getY - return the y value of this point
     *
     * @return the y value of this point
     */
    public double getY() {
        return this.y;
    }
}


