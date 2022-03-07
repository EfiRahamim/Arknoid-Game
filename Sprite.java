import biuoop.DrawSurface;


/**
 * @author Efraim Rahamim<efirahamim@gmail.com>
 * @version 1.0
 * @since 29.04.2020
 * ID 315392621
 * Interface of a Sprite object
 */

public interface Sprite {
    /**
     * .
     * draw the sprite to the screen
     *
     * @param draw the surface
     */
    void drawOn(DrawSurface draw);

    /**
     * .
     * notify the sprite that time has passed
     */
    void timePassed();

    /**
     * .
     * add the sprite object to the game
     * @param game the specified game
     */
    void addToGame(Game game);
}
