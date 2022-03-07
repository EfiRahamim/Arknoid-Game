/**
 * @author Efraim Rahamim <efirahamim@gmail.com>
 * @version 2.0
 * @since 31.05.2020
 * ID 315392621
 * <p>
 * a BallRemover is in charge of deleting balls that hit the "death block" from the game, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private Game game; //the game that this object belongs to
    private Counter counter; //the counter of the balls in this game

    /**
     * .
     * constructer creates an BallRemover object from given game and counter object
     *
     * @param g the given game
     * @param c the given counter
     */
    public BallRemover(Game g, Counter c) {
        this.counter = c; //initialize the counter of the balls
        this.game = g; //initialize the game of this object
    }

    /**
     * .
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that was being hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game); //remove the given ball from the game
        this.counter.decrease(1); //decrease the ball counter by 1

    }
}
