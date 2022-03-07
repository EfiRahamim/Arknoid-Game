import biuoop.DrawSurface;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Efraim Rahamim<efirahamim@gmail.com>
 * @version 2.0
 * @since 01.06.2020
 * ID 315392621
 * SpriteCollection class defines a collection of Sprite's objects
 */
public class SpriteCollection {
    private List<Sprite> sprites; //declaring a list that will contain all the Sprite's objects

    /**
     * .
     * constructor creates an array list for all the Sprite's objects
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * .
     * addSprite method adds new Sprite object to the collection
     *
     * @param s the new Sprite object
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s); // add new object to the list
    }

    /**
     * .
     * removeSprite method removes a specified sprite from the collection
     *
     * @param s the specified sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s); //remove the specifies sprite from the collection
    }

    /**
     * .
     * notifyAllTimePassed method calls the 'timePassed' method for every object in the collection
     */
    public void notifyAllTimePassed() {
        //make a copy of the sprite's list before iterating over them
        List<Sprite> tempCopy = new ArrayList<>(this.sprites);
        //call the "timePassed" methods
        for (Sprite currentObject : tempCopy) {
            currentObject.timePassed();
        }
    }

    /**
     * .
     * drawAllOn method calls the 'drawOn' method of all the objects in the collection, to draw on a specified surface
     *
     * @param draw the specified surface
     */
    public void drawAllOn(DrawSurface draw) {
        //make a copy of the sprite's list before iterating over them
        List<Sprite> tempCopy = new ArrayList<>(this.sprites);
        //call the "drawOn" methods
        for (Sprite currentObject : tempCopy) {
            currentObject.drawOn(draw);
        }
    }
}
