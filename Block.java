import biuoop.DrawSurface;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Efraim Rahamim <efirahamim@gmail.com>
 * @version 2.0
 * @since 31.05.2020
 * ID 315392621
 * Block class represents a block, which is a rectangle that the ball can collide with.
 * this class implements the 'Collidable' and 'Sprite' interfaces
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle block;
    private java.awt.Color color;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();

    /**
     * .
     * constructor of an block object - creates a new block as a rectangle
     *
     * @param upperLeft the upper-left point of the block
     * @param width     the width of the block (X axis)
     * @param height    the height of the block (Y axis)
     * @param color     the color of the block
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.block = new Rectangle(upperLeft, width, height);
        this.color = color;
    }


    /**
     * .
     * constructor type #3 - creates  anew block object from an existing rectangle
     *
     * @param originalRectan the exist rectangle that represents the block
     * @param color          the color of the block
     */
    public Block(Rectangle originalRectan, Color color) {
        this.block = originalRectan;
        this.color = color;
    }

    @Override
    /**
     * .
     * getCollisionRectangle method gets the "collision shape" of the object.
     * @return the collision shape of the object
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    @Override
    /**
     * hit method calculates new velocity for the ball that collided with the block
     * if the ball hits the ball in the upper or lower side, the vertical velocity would change
     * and if the ball hits the block in the right or left side, the horizontal velocity would change
     *In addition, the method notify all the listeners objects that a hit occurred with a ball
     * @param collisionPoint the collision point between the ball and the block
     * @param currentVelocity the current velocity of the ball
     * @param hitter the ball that made the hit
     * @return new velocity fot he ball
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //check on which side of the rectangle the collisionPoint is located
        //first, check if the collision is on the edges
        double newXvelocity = currentVelocity.getVelocityX();
        double newYvelocity = currentVelocity.getVelocityY();
        if (this.block.getUpperSide().isPointOnLine(collisionPoint)
                || this.block.getLowerSide().isPointOnLine(collisionPoint)) {
            //the collision is on the upper/lower side, reverse the velocity of the Y axis
            newYvelocity = -newYvelocity;
        }
        if (this.block.getLeftSide().isPointOnLine(collisionPoint)
                || this.block.getRightSide().isPointOnLine(collisionPoint)) {
            // the collision is on the right/left side, reverse the velocity of the X axis
            newXvelocity = -newXvelocity;
        }
        this.notifyHit(hitter); //notify all the listeners objects that a hit occurred by that ball
        return (new Velocity(newXvelocity, newYvelocity)); //return the new velocity
    }

    /**
     * .
     * drawOn method draws the block on the screen
     *
     * @param surface the surface og the screen we want to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color); //set the color of the block
        surface.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight()); //fill the block with the color
        surface.setColor(Color.BLACK); //set black color for the frame of the block
        surface.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight()); //mark in block the frame of the block

    }

    /**
     * .
     * time passed method
     */
    @Override
    public void timePassed() {

    }

    /**
     * .
     * add this block to a the Collidable and Sprite's objects collection of a specified game
     *
     * @param game the specified game
     */
    @Override
    public void addToGame(Game game) {
        game.addSprite(this); //add this block to the sprite collection of the game
        game.addCollidable(this); //add this block to the Collidable collection of the game
    }

    /**
     * .
     * remove this block from a specified game
     *
     * @param game the specified game
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this); //remove this block from the collidable's collection of the game
        game.removeSprite(this); //remove this block from the sprite's collection of this game
    }

    /**
     * .
     * add a new hit listener to the list of the listeners objects
     *
     * @param hl the listener of the event
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl); //add the listener to the list

    }

    /**
     * .
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the listener that should remove
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl); //remove the listener from the list

    }

    /**
     * .
     * this method notify all the listeners that a hit was occurred.
     * this method is being called whenever a the method "hit()" occurs
     * and will notify all of the registered HitListener objects by calling their hitEvent method.
     *
     * @param hitter the ball the made the hit
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
