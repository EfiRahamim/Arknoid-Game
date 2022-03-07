import biuoop.DrawSurface;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;


import java.awt.Color;

/**
 * @author Efraim Rahamim <efirahamim@gmail.com>
 * @version 1.0
 * @since 01.06.2020
 * ID 315392621
 * <p>
 * ScoreIndicator is in charge of showing the score on the screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter; //the score counter of this indicator
    private Rectangle rectangle; //the shape of the indicator


    /**
     * .
     * constructor creates an indicator from given score counter and sizes of the screen
     *
     * @param c    the score counter of the game
     * @param high the high of the screen
     * @param wide the wide of the screen
     */
    public ScoreIndicator(Counter c, int high, int wide) {
        this.rectangle = new Rectangle(new Point(0, 0), wide, high); //create the rectangle of this object
        this.scoreCounter = c; //initialize the scoreCounter with the given score
    }

    /**
     * .
     * this method draw the indicator on the screen
     *
     * @param surface the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.GRAY); //set the color of the rectangle
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight()); //fill the rectangle with color
        surface.setColor(Color.WHITE); //set color for the text
        //draw the text
        surface.drawText(300, 25, "Score: " + Integer.toString(this.scoreCounter.getValue()), 20);
    }

    /**
     * .
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {

    }

    /**
     * .
     * add the sprite object to the game
     *
     * @param game the specified game
     */
    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
    }


}
