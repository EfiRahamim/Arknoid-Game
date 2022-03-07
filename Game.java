import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

import java.awt.Color;

/**
 * @author Efraim Rahamim<efirahamim@gmail.com>
 * @version 2.0
 * @since 31.05.2020
 * ID 315392621
 * Game class holds the sprites and the collidables, and will be in charge of the animation.
 */
public class Game {
    private SpriteCollection sprites; //the sprite's objects collection
    private GameEnvironment gameEnvi; //the collidables objects
    private biuoop.GUI gui; //the GUI object for tha game
    private KeyboardSensor keyboard; //keyboard sensor for the paddle
    private Counter blocksCounter; //the amount of target blocks
    private Counter ballsCounter; //the amount of balls
    private Counter score; //the score in the game

    /**
     * .
     * constructor that builds empty spritescollections.sprites and game environment collections
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.gameEnvi = new GameEnvironment();
        this.blocksCounter = new Counter(); //initialize the blocks counter with zero
        this.ballsCounter = new Counter(); //initialize the balls counter with zero
        this.score = new Counter(); //initialize the game score with zero
    }

    /**
     * .
     * addCollidable method adds new collidable object to the game environment list
     *
     * @param c the new collidable object
     */
    public void addCollidable(Collidable c) {
        this.gameEnvi.addCollidable(c);
    }

    /**
     * .
     * removeCollidable method removes a specified collidable from the game
     *
     * @param c the specified collidable
     */
    public void removeCollidable(Collidable c) {
        this.gameEnvi.removeCollidable(c); //remove the coliidable from the game
    }

    /**
     * .
     * addSprites method adds new sprite object to the spritescollections.sprites collection
     *
     * @param s the new sprite object
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * .
     * removeSprite method removes a specified sprite from the game
     *
     * @param s the specified sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s); //remove the specifies sprite from the game
    }

    /**
     * initialize method initializes a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        gui = new biuoop.GUI("Game", 800, 600); //creating the GUI
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        //create a blockRemover object that would be HitListener for hitting the target blocks
        BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);
        //create a ballRemover object that would be HitListener for the death-block
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);
        //create a ScoreTrackingListener object that would be HitListener for the target blocks
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);

        //setting the borders of the screen with blocks
        //those blocks would not have a specific HitListener because those are the borders and not supposed to be remove

        Block leftSide = new Block(new Point(0, 0), 30, 600, Color.GRAY);
        leftSide.addToGame(this);
        Block rightSide = new Block(new Point(770, 0), 30, 600, Color.GRAY);
        rightSide.addToGame(this);
        Block upperSide = new Block(new Point(0, 0), 800, 30, Color.GRAY);
        upperSide.addToGame(this);
        //setting the "death block" - when a ball hit that block, the ball is out of the game
        Block lowerSide = new Block(new Point(0, 570), 800, 30, Color.GRAY);
        lowerSide.addToGame(this);
        lowerSide.addHitListener(ballRemover); //add the ballRemover as a HitListener of the "death block"
        //setting the score indicator on the top of the screen
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, 30, 800);
        scoreIndicator.addToGame(this);

        //set colors array for coloring the rows of the target blocks
        Color[] colorsArr = new Color[]{Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};
        //setting colored target-blocks on the screen
        double xValue = 730;
        double yValue = 100;
        for (int i = 0; i < 6; i++) {
            for (int j = 12 - i; j > 0; j--) {
                Block block = new Block(new Point(xValue, yValue), 40, 20, colorsArr[i]); //creat block
                block.addToGame(this); //add the block to the game
                block.addHitListener(blockRemover); //add the blockRemover as a HitListener of this block
                block.addHitListener(scoreTrackingListener); //add the score tracking listener as a listener
                xValue -= 40; //move the xValue to the next block on this row
            }
            yValue += 20; //move the yValue for the next row
            xValue = 730; //return the xValue to the beginning value for the next row
        }
        this.blocksCounter.increase(57); //there are 57 target blocks on the screen,
        // not including the borders and the paddle


        //setting the first ball
        Ball ball1 = new Ball(new Point(100, 300), 5, Color.WHITE, this.gameEnvi);
        ball1.setVelocity(5, 5);
        ball1.addToGame(this); //adding the ball to the sprites collection
        //setting the second ball
        Ball ball2 = new Ball(new Point(200, 300), 5, Color.WHITE, this.gameEnvi);
        ball2.setVelocity(5, 5);
        ball2.addToGame(this); //adding the ball to the sprites collection
        //setting the third ball
        Ball ball3 = new Ball(new Point(150, 300), 5, Color.WHITE, this.gameEnvi);
        ball3.setVelocity(5, 5); //adding the ball to the sprites collection
        ball3.addToGame(this);

        this.ballsCounter.increase(3); //add 3 balls to the amount of balls in the game

        //paddle
        Rectangle rectan9 = new Rectangle(new Point(350, 550), 100, 20);
        this.keyboard = gui.getKeyboardSensor();
        Paddle paddle = new Paddle(this.keyboard, rectan9, Color.YELLOW, this.gameEnvi);
        paddle.addToGame(this);
        //add the BallRemover as a listener to the paddle in case the ball hits the sides of the paddle
        paddle.addHitListener(ballRemover);


    }

    /**
     * .
     * <p>
     * run method will run the game by starting the animation loop
     */
    public void run() {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        //run the game as long as there are still target-blocks and balls
        while (this.blocksCounter.getValue() != 0 && this.ballsCounter.getValue() != 0) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = this.gui.getDrawSurface();
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, 800, 600);
            this.sprites.drawAllOn(d); //draw the objects on the screen
            this.gui.show(d);
            this.sprites.notifyAllTimePassed(); //make changes in object, if needed, between frames

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        //if the game ended because all the blocks were hit - add 100 points to the score counter
        if (this.blocksCounter.getValue() == 0) {
            this.score.increase(100);
        }

        //show the screen when the game is over just for a few seconds
        for (int i = 0; i < 20; i++) {
            //show the screen when the game is over for one more frame
            DrawSurface d = this.gui.getDrawSurface();
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, 800, 600);
            this.sprites.drawAllOn(d); //draw the objects on the screen
            this.gui.show(d);
            // timing
            long startTime = System.currentTimeMillis(); // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }


    }

    /**
     * .
     * this method end the game and closes all the gui window
     */
    public void endGame() {
        this.gui.close(); //close the gui when game is over
    }


}
