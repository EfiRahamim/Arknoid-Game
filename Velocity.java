import geometryprimitives.Point;

/**
 * @author Efraim Rahamim <efirahamim@gmail.com>
 * @version 1.0
 * @since 12.04.2020
 * ID 315392621
 * Velocity class specifies the change in position on the `x` and the `y` axes for a certain point.
 */
public class Velocity {
    //define the variables that represents the changes in the 'x' and 'y' axes.
    private double moveX, moveY;

    /**
     * .
     * constructor type #1 - set the velocity object by getting the changes in the 'x' and 'y' axes
     *
     * @param dx the progress in the 'x' axes
     * @param dy the progress in the 'y' axes
     */
    public Velocity(double dx, double dy) {
        this.moveX = dx;
        this.moveY = dy;
    }

    /**
     * .
     * constructor type #2 - set the velocity object from another velocity that already been created
     *
     * @param v the created velocity object
     */
    public Velocity(Velocity v) {
        this.moveX = v.moveX;
        this.moveY = v.moveY;
    }

    /**.
     * fromAngleAndSpeed method creates a new instance of "Velocity" object
     * by getting the velocity values as angle and speed
     * the method would convert the angle and speed into 'dx' and 'dy' by using equations of right angled triangle
     * @param angle the angle of the velocity
     * @param speed the speed of the velocity
     * @return creating of new Velocity object with the converted values
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //We will create an imaginary right angled triangle, so that one side is parallel to the x axis and the other
        //side is parallel to the y axis and the velocity we obtained represents the remainder in the triangle
        double dx = speed * (Math.sin(Math.toRadians(angle))); //calculates the side that parallel to the x axis
        double dy = -speed * (Math.cos(Math.toRadians(angle))); //calculates the side that parallel to the y axis
        return new Velocity(dx, dy); //creating a new Velocity object with the converter values
    }

    /**
     * .
     * getVelocityX method returns the progress of the 'x' axes in this velocity
     *
     * @return the "moveX" field
     */
    public double getVelocityX() {
        return this.moveX;
    }

    /**
     * .
     * getVelocityY method returns the progress of the 'y' axes in this velocity
     *
     * @return the "moveY" field
     */
    public double getVelocityY() {
        return this.moveY;
    }


    /**
     * .
     * applyToPoint method takes a point with position (x,y) and return a new point with position (x+dx, y+dy)
     *
     * @param p the original point, before the changes
     * @return the new point, after adding the progress of the 'x' and 'y' axes
     */
    public Point applyToPoint(Point p) {
        return (new Point(p.getX() + this.moveX, p.getY() + this.moveY));
    }
}
