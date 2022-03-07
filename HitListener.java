/**
 * @author Efraim Rahamim <efirahamim@gmail.com>
 * @version 1.0
 * @since 31.05.2020
 * ID 315392621
 * HitListener is an interface that would be implemented by all the objects that supposed to be listeners of events
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that was being hit
     * @param hitter   the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
