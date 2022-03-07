import geometryprimitives.Point;
import geometryprimitives.Rectangle;

/**
 * @author Efraim Rahamim <efirahamim@gmail.com>
 * @version 2.0
 * @since 01.06.2020
 * ID 315392621
 * Interface of a collidable object
 */
public interface Collidable {
    /**
     * .
     * Return the "collision shape" of the object
     *
     * @return the colision object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity. Also notify all the listeners objects that a hit occurred with a specific ball.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  the collision point of the object with the ball
     * @param currentVelocity the current velocity of the ball (before the collision)
     * @param hitter          that ball that made the hit
     * @return the new hit after the changes
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * .
     * add Collidable object to a specified game
     *
     * @param game the specified game
     */
    void addToGame(Game game);
}
