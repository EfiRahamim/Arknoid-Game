/**
 * @author Efraim Rahamim <efirahamim@gmail.com>
 * @version 2.0
 * @since 31.05.2020
 * ID 315392621
 * <p>
 * Counter class is in charge to count the remaining blocks in the game
 */
public class Counter {
    private int amount; //the amount of the block

    /**.
     * constructor creates Counter object and initialize the blocks amount with 0
     */
    public Counter() {
        this.amount = 0;
    }

    /**
     * .
     * this method increase the amount of blocks
     *
     * @param number the number to add to the amount of blocks
     */
    void increase(int number) {
        this.amount += number;
    }

    /**.
     * this method decrease the amount of blocks
     * @param number the number to subtract from the block amount
     */
    void decrease(int number) {
        this.amount -= number;
    }

    /**.
     * this method return the current blocks value
     *
     * @return this.amount
     */
    int getValue() {
        return this.amount;
    }
}
