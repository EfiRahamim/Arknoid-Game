package geometryprimitives;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Efraim Rahamim <efirahamim@gmail.com>
 * @version 1.0
 * @since 26.04.2020
 * ID 315392621
 * Representation of a rectangle on the screen
 */
public class Rectangle {
    private Point upperLeft; //declaring the upper-left (start point) of the rectangle
    private Point upperRight; //declaring the upper-right point of this rectangle
    private Point lowerLeft; //declaring the lower-left point of this rectangle
    private Point lowerRight; //declaring the lower-right point of this rectangle
    private double width; //declaring the width of the rectangle
    private double height; //declaring the height of the rectangle
    //representing the sides of the rectangle as 4 lines
    private Line upperSide;
    private Line lowerSide;
    private Line leftSide;
    private Line rightSide;

    /**
     * .
     * constructor - creates a new rectangle object with location (a point) and width and height
     *
     * @param upperLeft the location of the rectangle as a point of the upper left corner
     * @param width     the width of the rectangle (X axis)
     * @param height    the height of the rectangle (Y axis)
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.lowerRight = new Point(this.upperRight.getX(), this.lowerLeft.getY());
        this.width = width;
        this.height = height;
        //converting the sides of the rectangle into 4 lines
        this.upperSide = new Line(this.upperLeft, this.upperRight);
        this.lowerSide = new Line(this.lowerLeft, this.lowerRight);
        this.leftSide = new Line(this.upperLeft, this.lowerLeft);
        this.rightSide = new Line(this.upperRight, this.lowerRight);
    }

    /**
     * .
     * getWidth method gets the width of this rectangle
     *
     * @return the width of the rectangle as double variable
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * .
     * getHeight gets the height of this rectangle
     *
     * @return the height of the rectangke as double variable
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * .
     * getUpperLeft method gets the upper-left point of this rectangle (the start point)
     *
     * @return the upper-left point as a geometryprimitives.Point variable
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * .
     * getUpperRight gets the upper-right point of this rectangle
     *
     * @return the upper-right point
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * .
     * getLowerLeft gets the lower-left point of this rectangle
     *
     * @return the lower-left point
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }

    /**
     * .
     * getLowerRight gets the lower-right point of this rectangle
     *
     * @return the lower-right point
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /**
     * .
     * getUpperSide method gets the upper-side line of this rectangle
     *
     * @return the upper-side line of this rectangle
     */
    public Line getUpperSide() {
        return this.upperSide;
    }

    /**
     * .
     * getLowerSide method gets the lower-side line of this rectangle
     *
     * @return the lower-side line of this rectangle
     */
    public Line getLowerSide() {
        return this.lowerSide;
    }

    /**
     * .
     * getLeftSide method gets the left-side line of this rectangle
     *
     * @return the left-side line of this rectangle
     */
    public Line getLeftSide() {
        return this.leftSide;
    }

    /**
     * .
     * getRightSide method gets the right-side line of this rectangle
     *
     * @return the right-side line of this rectangle
     */
    public Line getRightSide() {
        return this.rightSide;
    }


    /**
     * .
     * isFoggingWithLine method checks if a specified line is fogging with the rectangle's sides
     *
     * @param line the specified line
     * @return true if the line is fogging, false otherwise
     */
    public boolean isFoggingWithLine(Line line) {
        return line.isFogging(this.upperSide) || line.isFogging(this.lowerSide)
                || line.isFogging(this.leftSide) || line.isFogging(this.rightSide);
        //return true if the line is fogging, false otherwise
    }

    /**
     * .
     * intersectionPoints methods gets the intersection points between this rectangle and a line
     * the points will be returned as a list. if there are not intersection points, the list would be empty
     *
     * @param line the line that intersect (or not) with the rectangle
     * @return list of all the intersection point (possibly empty)
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectPoints = new ArrayList<Point>(); //the list of the intersection points as an array list
        if (isFoggingWithLine(line)) { //checks is the line fogs with the rectangle
            return intersectPoints; //return an empty list
        } else {
            //checks if intersection points exist and add them to the list
            if (line.intersectionWith(upperSide) != null) {
                intersectPoints.add(upperSide.intersectionWith(line));
            }
            if (line.intersectionWith(lowerSide) != null) {
                intersectPoints.add(lowerSide.intersectionWith(line));
            }
            if (line.intersectionWith(leftSide) != null) {
                intersectPoints.add(leftSide.intersectionWith(line));
            }
            if (line.intersectionWith(rightSide) != null) {
                intersectPoints.add(rightSide.intersectionWith(line));
            }
            return intersectPoints; //returning the list, could be empty (size 0)
        }
    }

}
