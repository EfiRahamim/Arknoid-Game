import geometryprimitives.Line;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Efraim Rahamim <efirahamim@gmail.com>
 * @version 2.0
 * @since 28.05.2020
 * ID 315392621
 * GameEnvironment class represents the objects that appears in this game and their collision options
 */
public class GameEnvironment {
    private List<Collidable> collidables; //declaring the list of collidables objects that appear in the game

    /**
     * .
     * constructor - creates a new list of collidables objects
     */
    public GameEnvironment() {
        collidables = new ArrayList<Collidable>();
    }

    /**
     * .
     * addCollidable method add a new collidable object that appears in the game to the list
     *
     * @param c the new collidable object
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * .
     * removeCollidable method removes a specifies collidable from the game environment
     *
     * @param c the specified collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c); //remove the specified collidable from the collection
    }

    /**
     * .
     * getClosestCollision method gets the closest collision between a trajectory of an object to an
     * collidable object. if the moving object  will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the route of the moving object
     * @return information about the collision, if exist. null otherwise.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = trajectory.end(); //initialize the closest collision point to be the end point of the
        // trajectory line
        Rectangle currentRectan; //representing the current rectangle in the array
        Collidable closestObject = null; //represents the closest object that collides with the trajectory
        //make a copy of the Collidable objects before iterating over them
        List<Collidable> tempCopy = new ArrayList<>(this.collidables);
        for (Collidable currentObject : tempCopy) { //running on the copy collidable objects array
            currentRectan = currentObject.getCollisionRectangle(); //initialize the current rectangle
            if (trajectory.closestIntersectionToStartOfLine(currentRectan) != null) { //check if there are collision
                // points between the trajectory the current object in the array
                Point currentPoint = trajectory.closestIntersectionToStartOfLine(currentRectan);
                //check for the closest point
                if (currentPoint.distance(trajectory.start()) < closestPoint.distance(trajectory.start())) {
                    closestPoint = currentPoint;
                    closestObject = currentObject;
                }
            }
        }
        if (closestPoint.equals(trajectory.end())) { //check if there were any collision points
            return null;
        } else { //return new collision info with the closest point and object
            return (new CollisionInfo(closestObject, closestPoint));
        }


    }
}
