import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import geometryprimitives.Line;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Efraim Rahamim<efirahamim@gmail.com>
 * @version 2.0
 * @since 01.06.2020
 * ID 315392621
 * Paddle class represents the paddle in the game
 */
public class Paddle implements Sprite, Collidable, HitNotifier {
    private biuoop.KeyboardSensor keyboard; // a KeyboardSensor object so make tha paddle move to the sides
    private Rectangle rectangle; // the rectangle that represents the paddle
    private Color color; //the color of the paddle
    private GameEnvironment gameEnvi; //the game environment of the paddle, to know if it hits the blocks on the
    // side of the screen
    private List<HitListener> hitListeners = new ArrayList<HitListener>();

    /**
     * .
     * constructor set the keyboard sensor of this paddle
     *
     * @param k               a keyboardSensor object
     * @param r               the rectangle size and shape of this paddle
     * @param c               the color of this paddle
     * @param gameEnvironment the game environment of this paddle
     */
    public Paddle(KeyboardSensor k, Rectangle r, Color c, GameEnvironment gameEnvironment) {
        this.keyboard = k; //set the keyboard sensor
        this.rectangle = r; //set the size and shape of the paddle (as a rectangle)
        this.color = c; //set the color of this paddle
        this.gameEnvi = gameEnvironment; //set the game environment of this paddle
    }

    /**
     * .
     * moveLeft method moves the paddle a little bit to the left side of the screen by changing its upper-left point
     */
    public void moveLeft() {
        ///check that moving to the left would not cause a collision with the screen border
        //check collision between the trajectory of the paddle to the screen border
        Point endTrajectory = new Point(this.rectangle.getUpperLeft().getX() - 10,
                this.rectangle.getUpperLeft().getY()); //set the end point of the trajectory
        Line trajectory = new Line(this.rectangle.getUpperLeft(), endTrajectory); //set the trajectory
        double freeSpace; //the space that paddle could move
        CollisionInfo collision = this.gameEnvi.getClosestCollision(trajectory); //get the collision info
        if (collision.collisionObject() == this) { //check if the collision is with the paddle itself
            freeSpace = 10; //there is no collision with the borders, the paddle can move freely
        } else { //move the paddle to the spare space between the paddle to the screen border
            //calculates the space between the paddle to the border
            freeSpace = this.rectangle.getUpperLeft().distance(collision.collisionPoint());
        }
        //set the new start point (upper-left point) for the paddle
        Point newPoint = new Point(this.rectangle.getUpperLeft().getX() - freeSpace,
                this.rectangle.getUpperLeft().getY());
        //set a new paddle on the new start point
        this.rectangle = new Rectangle(newPoint, this.rectangle.getWidth(), this.rectangle.getHeight());
    }


    /**
     * .
     * moveRight method moves the paddle a little bit to the right side of the screen by changing its upper-left point
     */
    public void moveRight() {
        //check that moving to the right would not cause a collision with the screen border
        //check collision between the trajectory of the paddle to the screen border
        Point endTrajectory = new Point(this.rectangle.getUpperRight().getX() + 10,
                this.rectangle.getUpperRight().getY()); //set the end point of the trajectory
        Line trajectory = new Line(this.rectangle.getUpperRight(), endTrajectory); //set the trajectory
        CollisionInfo collision = this.gameEnvi.getClosestCollision(trajectory); //get the collision info
        double freeSpace; //the space that the paddle could move
        if (collision.collisionObject() == this) { //check if the collision is with the paddle itself
            freeSpace = 10; //there is no collision with the borders, the paddle can move freely
        } else { //move the paddle to the spare space between the paddle to the screen border
            //calculates the space between the paddle to the border
            freeSpace = this.rectangle.getUpperRight().distance(collision.collisionPoint());
        }
        //set the new start point (upper-left point) for the paddle
        Point newPoint = new Point(this.rectangle.getUpperLeft().getX() + freeSpace,
                this.rectangle.getUpperLeft().getY());
        //set a new paddle on the new start point
        this.rectangle = new Rectangle(newPoint, this.rectangle.getWidth(), this.rectangle.getHeight());


    }

    /**
     * .
     * timePassed method moves the paddle to the sides of the screen, according to the keyboardsensor
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) { //if "left" is pressed
            this.moveLeft(); //move the paddle to the left
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) { // if "right" is pressed
            this.moveRight(); //move the paddle to the right
        }
    }

    /**
     * .
     * drawOn method draws the paddle on the screen
     *
     * @param d the specifies surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color); //set the color as the paddle color
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight()); //fill the paddle with color
        d.setColor(Color.BLACK); //set black color for the framfe of the paddle
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight()); //draw the frame of the paddle
    }

    /**
     * .
     * getCollisionRectangle method returns this paddle
     *
     * @return this paddle as a rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * .
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * In this case we will notify the listeners only when the ball hits the paddle on its sides
     *
     * @param collisionPoint  the collision point of the paddle with the ball
     * @param currentVelocity the current velocity of the ball (before the collision)
     * @param hitter          the ball that made the hit
     * @return the new hit after the changes
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //set the speed of the currentVelocity
        double speed = Math.sqrt(Math.pow(currentVelocity.getVelocityX(), 2)
                + Math.pow(currentVelocity.getVelocityY(), 2));
        //split the upper side line of the paddle into 5 lines that represents 5 regions
        double fifthWidth = this.rectangle.getWidth() / 5; //calculate the size of each region
        double xValue = this.rectangle.getUpperLeft().getX();
        double yValue = this.rectangle.getUpperLeft().getY();
        //set the points of each region
        Point fifth1 = this.rectangle.getUpperLeft();
        Point fifth2 = new Point(xValue + (1 * fifthWidth), yValue);
        Point fifth3 = new Point(xValue + (2 * fifthWidth), yValue);
        Point fifth4 = new Point(xValue + (3 * fifthWidth), yValue);
        Point fifth5 = new Point(xValue + (4 * fifthWidth), yValue);
        Point fifth6 = this.rectangle.getUpperRight();
        //set the lines of each region
        Line region1 = new Line(fifth1, fifth2);
        Line region2 = new Line(fifth2, fifth3);
        Line region3 = new Line(fifth3, fifth4);
        Line region4 = new Line(fifth4, fifth5);
        Line region5 = new Line(fifth5, fifth6);
        //check on which region is the collision point
        if (region1.isPointOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(300, speed);
        } else if (region2.isPointOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(330, speed);
        } else if (region3.isPointOnLine(collisionPoint)) {
            //change only the dy velocity
            return (new Velocity(currentVelocity.getVelocityX(), -currentVelocity.getVelocityY()));
        } else if (region4.isPointOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(30, speed);
        } else if (region5.isPointOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(60, speed);
        } else { //the ball hit one of the sides of the paddle
            notifyHit(hitter); //notify all the listeners about the hit on the side
        }
        return currentVelocity; //default return the current velocity
    }

    /**
     * .
     * addToGame method adds this paddle to the GameEnvironment and to the Sprite's collection of a specified game
     *
     * @param g the specified game
     */
    public void addToGame(Game g) {
        g.addCollidable(this); //add this paddle to the Collidable objects in the specified game
        g.addSprite(this); //add this paddle to the Sprite objects collection of the specified game
    }


    /**
     * .
     * add a new hit listener to the list of the listeners objects
     *
     * @param hl the listener of the event
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);

    }

    /**
     * .
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the listener that should remove
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * .
     * this method notify all the listeners that a hit was occurred.
     * this method is being called whenever the ball hits the paddle from the sides
     * and will notify all of the registered HitListener objects by calling their hitEvent method.
     *
     * @param hitter the ball that made the hit
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(new Block(this.rectangle, this.color), hitter);
        }
    }
}
