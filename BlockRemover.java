/**
 * @author Efraim Rahamim <efirahamim@gmail.com>
 * @version 2.0
 * @since 31.05.2020
 * ID 315392621
 * <p>
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Game game; //the specified game
    private Counter remainingBlocks; //the blocks counter of the game

    /**
     * .
     * constructor initialize this object with given Game and Counter objects
     *
     * @param g the specified Game object
     * @param c the specified Counter object
     */
    public BlockRemover(Game g, Counter c) {
        this.game = g; //initialize the game
        this.remainingBlocks = c; //initialize the counter
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * <p>
     * This method removes Blocks that are hit from the game.
     *
     * @param beingHit the block that was being hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1); //reduce the amount of blocks by 1

    }
}
