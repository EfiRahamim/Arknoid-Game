import biuoop.DrawSurface;
import geometryprimitives.Line;
import geometryprimitives.Point;

/**
 * @author Efraim Rahamim <efirahamim@gmail.com>
 * @version 3.0
 * @since 01.06.2020
 * ID 315392621
 * Defines a ball.
 * this class implement the Sprite interface
 */
public class Ball implements Sprite {
    //declaring the high and wide of the screen
    private static final int HIGH = 300; //the y axes
    private static final int WIDE = 400; //the x axes

    private Point center; //the center point defined by "geometryprimitives.Point" variable
    private int radius; //the radius of the ball
    private java.awt.Color color; //the color of the ball.
    private Velocity velocity = new Velocity(0, 0); //the velocity of the ball in the surface. initialize with
    //zero in case the user would'nt set the velocity by himself
    private GameEnvironment gameEnvironment; //the game enviroment of the ball (how many colliadable objects are on
    // the screen)


    /**
     * .
     * constructor type #1 - get point, radius and color of the required ball
     * the point will be served as a "geometryprimitives.Point" variable
     *
     * @param center   the center point of the ball
     * @param r        the radius of the ball from the center point
     * @param color    the color of the ball
     * @param gameEnvi the game environment of the ball
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvi) {
        this.center = new Point(center.getX(), center.getY()); //define the center point
        this.radius = r; //define the radius of the ball
        this.color = color; //define the color of the ball
        this.gameEnvironment = gameEnvi; //initialize the game environment
    }

    /**
     * .
     * constructor type #2 - get the point, radius and color of the required ball
     * the point will be served as x&y's values
     *
     * @param x        the x value of the center point
     * @param y        the y value of the center point
     * @param r        the radius of the ball
     * @param color    the color of the ball
     * @param gameEnvi the game environment of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvi) {
        this.center = new Point(x, y); //define the center point
        this.radius = r; //define the radius
        this.color = color; //define the color of the ball
        this.gameEnvironment = gameEnvi; //define the game environment of the ball
    }

    /**
     * .
     * getX method returns the x value of the center point of the ball
     * casting from double to Integer is being used because the value is double and the method returns Integer
     *
     * @return the x value of the mid-point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * .
     * getY method returns the y value of the center point of the ball
     * casting from double to Integer is being used because the value is double and the method returns Integer
     *
     * @return the y value of the mid-point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * .
     * getSize method returns the radius of the ball
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * .
     * getColor returns the color of the ball
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * .
     * drawOn method draws the ball on the required DrawSurface
     *
     * @param surface the DrawSurace object we want to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * .
     * setVelocity method sets the velocity of the ball by getting the required velocity
     *
     * @param v the required velocity we want to set to our ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v);
    }

    /**
     * .
     * setVelocity method (type #2) sets the velocity of the ball by getting the progress of the 'x' any 'y' axes
     *
     * @param dx the progress of the 'x' axes
     * @param dy the progress of the 'y' axes
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * .
     * getVelocity returns the velocity of the ball
     *
     * @return velocity object that represents the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * .
     * getGameEnvironment method gets the game Environment of this ball
     *
     * @return the game Environment of this ball
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * .
     * moveOneStep method changes the center point of the ball according to the velocity of the ball
     * the method would use the velocity class the make the progress of the 'x' and 'y' values of the ball
     */
    public void moveOneStep() {
        //checks if adding the velocity to the x axes would exclude the screen from the right
        if (this.center.getX() + this.radius + this.velocity.getVelocityX() > WIDE) {
            this.setVelocity(-this.velocity.getVelocityX(), this.velocity.getVelocityY()); //changing the velocity
            // of the x axis to be the other way by multiply it by (-1)
        }
        //checks if adding the velocity to the x axes would exclude the screen from the left
        if (this.center.getX() - this.radius + this.velocity.getVelocityX() < 0) {
            this.setVelocity(-this.velocity.getVelocityX(), this.velocity.getVelocityY()); //reversing the velocity
            // of the x axes
        }
        //checks if adding the velocity to the y axis would exclude the screen from the bottom
        if (this.center.getY() + this.radius + this.velocity.getVelocityY() > HIGH) {
            this.setVelocity(this.velocity.getVelocityX(), -this.velocity.getVelocityY()); //reversing the velocity
            // of the x\y axes
        }
        //checks if adding the velocity to the y axis would exclude the screen from the top
        if (this.center.getY() - this.radius + this.velocity.getVelocityY() < 0) {
            this.setVelocity(this.velocity.getVelocityX(), -this.velocity.getVelocityY()); //reversing the velocity
            // of the x\y axes
        }
        //changing the point position
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * .
     * moveOneStep -type#2- method changes the center point of the ball according to the velocity of the ball
     * the method would use the velocity class to make the progress of the 'x' and 'y' values of the ball.
     * In this method, the ball is in a specific area on the screen with specifics high's and wide's
     *
     * @param high             the high of the specific area
     * @param wide             the wide of the specific area
     * @param screenStartPoint the start point on the X&Y axis of the specific area
     */
    public void moveOneStep(int wide, int high, Point screenStartPoint) {
        //checks if adding the velocity to the x axes would exclude the screen from the right
        if (this.center.getX() + this.radius + this.velocity.getVelocityX() > wide + screenStartPoint.getX()) {
            this.setVelocity(-this.velocity.getVelocityX(), this.velocity.getVelocityY()); //changing the velocity
            // of the x axis to be the other way by multiply it by (-1)
        }
        //checks if adding the velocity to the x axes would exclude the screen from the left
        if (this.center.getX() - this.radius + this.velocity.getVelocityX() < screenStartPoint.getX()) {
            this.setVelocity(-this.velocity.getVelocityX(), this.velocity.getVelocityY()); //reversing the velocity
            // of the x axes
        }
        //checks if adding the velocity to the y axis would exclude the screen from the bottom
        if (this.center.getY() + this.radius + this.velocity.getVelocityY() > high + screenStartPoint.getY()) {
            this.setVelocity(this.velocity.getVelocityX(), -this.velocity.getVelocityY()); //reversing the velocity
            // of the x\y axes
        }
        //checks if adding the velocity to the y axis would exclude the screen from the top
        if (this.center.getY() - this.radius + this.velocity.getVelocityY() < screenStartPoint.getY()) {
            this.setVelocity(this.velocity.getVelocityX(), -this.velocity.getVelocityY()); //reversing the velocity
            // of the x\y axes
        }
        //changing the point position
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * .
     * setTrajectory method calculates the route of the ball, if it will move without any obstacles.
     * its a line starting at current location, and ending where the velocity will take the ball
     * if no collisions will occur
     *
     * @param v the velocity of the ball
     * @return the trajectory of the ball, as a geometryprimitives.Line object
     */
    public Line setTrajectory(Velocity v) {
        Point start = this.center; //the start point of the trajectory
        Point end = v.applyToPoint(this.center); //the end point of the trajectory
        return (new Line(start, end)); //return the trajectory
    }

    /**
     * .
     * <p>
     * moveOneStep -type #3- method changes the center point of the ball depends on the obstacles the ball might hit
     * if there are no obstacles on its way, the ball will move on freely.
     * if there are any obstacles on its way, the ball will change its way before hitting the first obstacle
     *
     * @param trajectory the way of the ball, without considering presence of obstacles
     */
    public void moveOneStep(Line trajectory) {
        //set the collision info of this ball in the given trajectory
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        //check if the ball encounter with obstacles
        if (collisionInfo == null) {
            this.center = this.getVelocity().applyToPoint(this.center); //moving the ball to the end of his trajectory
        } else {
            //Calculates a point that is close to the collision point
            double xValue = collisionInfo.collisionPoint().getX(); //initialize the x value of the point
            double yValue = collisionInfo.collisionPoint().getY(); //initialize the y value of the point
            if (this.velocity.getVelocityX() < 0) { //moving aside the x value by 1, according to the x velocity
                xValue = xValue + 1;
            } else {
                xValue = xValue - 1;
            }
            if (this.velocity.getVelocityY() < 0) { //moving aside the y value by 1, according to the y velocity
                yValue = yValue + 1;
            } else {
                yValue = yValue - 1;
            }
            Point closePoint = new Point(xValue, yValue); //a close point to the end of the trajectory
            this.center = closePoint; //changing the center of the ball to be the close point,
            // which moves the ball to "almost" the hit point
            //changing the velocity of the ball
            this.setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.velocity));
        }

    }

    /**
     * .
     * timePassed method move the ball one step, by using the setTrajectory and moveOneStep methods
     */
    public void timePassed() {
        this.moveOneStep(this.setTrajectory(this.getVelocity()));
    }

    /**
     * .
     * addToGame method adds the ball to the sptire collection objects of a specified game
     *
     * @param game the specified game
     */
    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * .
     * this method removes the ball from the game
     *
     * @param g the given game
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }


}
