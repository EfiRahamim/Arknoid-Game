/**
 * @author Efraim Rahamim<efirahamim@gmail.com>
 * @version 1.0
 * @since 01.06.2020
 * ID 315392621
 * as3Game class runs the game as required in part 4 of ass3
 */
public class Ass5Game {
    /**
     * .
     * main method - runs the game
     *
     * @param args noting to except
     */
    public static void main(String[] args) {
        Game game = new Game(); //create new object of game
        game.initialize(); //initialize the objects in the game
        game.run(); //run the game
        game.endGame(); //end the game

    }
}
