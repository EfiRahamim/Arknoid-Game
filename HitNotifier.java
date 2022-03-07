/**
 * @author Efraim Rahamim <efirahamim@gmail.com>
 * @version 1.0
 * @since 31.05.2020
 * ID 315392621
 * HitNotifier is an interface that would be implemented by all the objects that supposed to be notifiers of events
 */
public interface HitNotifier {
    /**
     * .
     * add a new hit listener to the list of the listeners objects
     *
     * @param hl the listener of the event
     */
    void addHitListener(HitListener hl);

    /**
     * .
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the listener that should remove
     */
    void removeHitListener(HitListener hl);
}
