import geometryprimitives.Point;

/**
 * @author Efraim Rahamim <efirahamim@gmail.com>
 * @version 1.0
 * @since 28.04.2020
 * ID 315392621
 * CollisionInfo class gives the information about a collision between objects
 */
public class CollisionInfo {
    private Collidable collisionObject; //the object the ball collided with
    private Point collisionPoint; //the collision point on the object

    /**
     * .
     * constructor - get the collidable object and the collision point
     *
     * @param collisionObject the object the ball collided with
     * @param collisionPoint  the collision point
     */
    public CollisionInfo(Collidable collisionObject, Point collisionPoint) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }

    /**
     * .
     * collisionPoint method gets the collision point of this collision
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * .
     * collisionObject method gets the collision object of this collision
     *
     * @return the collision object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
