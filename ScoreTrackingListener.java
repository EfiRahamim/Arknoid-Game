/**
 * @author Efraim Rahamim <efirahamim@gmail.com>
 * @version 2.0
 * @since 31.05.2020
 * ID 315392621
 * <p>
 * ScoreTrackingListener is in charge of updating the score in the game.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore; //the current score in the game

    /**
     * .
     * constructor creates an object with a given score
     *
     * @param c the given counter
     */
    public ScoreTrackingListener(Counter c) {
        this.currentScore = c; //initialize the current score with a given score
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that was being hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5); //increase the score by 5 points when hit a block
    }
}
