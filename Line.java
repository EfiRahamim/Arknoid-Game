package geometryprimitives;

/**
 * @author Efraim Rahamim <efirahamim@gmail.com>
 * @version 1.1
 * @since 27.04.2020
 * ID 315392621
 * geometryprimitives.Line class represents a line in space
 */
public class Line {
    //the two points that define the line and the slope of the line
    private Point point1, point2;
    private Double slope;
    private Double[] equation = new Double[2]; //represents the equation of the line by it's slope and a constant number
    //according to the equation formula: y=mx+c
    private boolean isVerticalToX = false;

    /**
     * .
     * constructor with 2 points that are located on the line
     * define the line by the values of those two points
     *
     * @param start the first point that located on the line
     * @param end   the second point that located on the line
     */
    public Line(Point start, Point end) {
        //define the line by the values of those two points
        this.point1 = new Point(start.getX(), start.getY());
        this.point2 = new Point(end.getX(), end.getY());
        //initialize the "slope" and "isVertical" variables
        //checks if the line is perpendicular to the X axis and not a single point
        if ((this.point1.getX() == this.point2.getX()) && (this.length() > 0)) {
            //the line is perpendicular to the X axis
            this.isVerticalToX = true;
            //resetting the slope of the line to be 'null'
            this.slope = null;
            //resetting the equation of the line to be null
            this.equation[0] = null;
            this.equation[1] = null;
        } else if (this.length() == 0) { //checks if the line is a single point
            this.slope = null;
            this.equation[0] = null;
            this.equation[1] = null;
        } else { //calculates the slope by using a formula
            this.slope = ((this.point1.getY() - this.point2.getY()) / (this.point1.getX() - this.point2.getX()));
            //updating the equation of the line
            this.equation[0] = this.slope;
            this.equation[1] = ((-1) * (this.slope) * (this.point1.getX()) + (this.point1.getY()));
        }


    }

    /**
     * .
     * constructor with 4 values of x&y's that represent two points
     *
     * @param x1 the first x value
     * @param y1 the first y value
     * @param x2 the second x value
     * @param y2 the second y value
     */
    public Line(double x1, double y1, double x2, double y2) {
        //define the line by those 4 values
        this.point1 = new Point(x1, y1);
        this.point2 = new Point(x2, y2);
        //initialize the "slope" and "isVertical" variables
        //checks if the line is perpendicular to the X axis and not a single point
        if ((this.point1.getX() == this.point2.getX()) && (this.length() > 0)) {
            //the line is perpendicular to the X axis
            this.isVerticalToX = true;
            //resetting the slope of the line to be 'null'
            this.slope = null;
            //resetting the equation of the line to be null
            this.equation[0] = null;
            this.equation[1] = null;
        } else if (this.length() == 0) { //checks if the line is a single point
            this.slope = null;
            this.equation[0] = null;
            this.equation[1] = null;
        } else { //calculates the slope by using a formula
            this.slope = ((this.point1.getY() - this.point2.getY()) / (this.point1.getX() - this.point2.getX()));
            //updating the equation of the line
            this.equation[0] = this.slope;
            this.equation[1] = ((-1) * (this.slope) * (this.point1.getX()) + (this.point1.getY()));
        }

    }


    /**
     * .
     * length - calculates the length of the line by using the 'distance' method in the "geometryprimitives.Point" class
     *
     * @return the length of the line
     */
    public double length() {
        //checks if the points of the line are the same point
        if (point1.equals(point2) || point2.equals(point1)) {
            return 0;
        } else {
            //the distance between those points represents the length of the line
            double length = point1.distance((point2));
            return length;
        }
    }


    /**
     * .
     * middle calculates the middle point values in this line
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double midX, midY;
        //checks if the line is a single point
        if (this.length() == 0) {
            //the middle point is the point itself
            midX = this.point1.getX();
            midY = this.point1.getY();
        } else {
            //calculating the middle values
            midX = (this.point1.getX() + this.point2.getX()) / 2;
            midY = (this.point1.getY() + this.point2.getY()) / 2;
        }
        //declaring the new middle point and initializes it with the middle values
        Point midPoint = new Point(midX, midY);
        return midPoint;

    }

    /**
     * .
     * start returns the start point of the line
     *
     * @return the start point of this line
     */
    public Point start() {
        return point1;
    }

    /**
     * .
     * end returns the end point of the line
     *
     * @return the end point of the line
     */
    public Point end() {
        return point2;
    }

    /**
     * .
     * isPointOnLine - checks if a certain point is located on the line
     *
     * @param point the point we check
     * @return true if the point is on the line, false otherwise
     */
    public boolean isPointOnLine(Point point) { //checks if the distances between the other point to the points of the
        //line equals to the length of the line (by checking that the difference between them is less than epsilon)
        double epsilon = Math.pow(10, -10);
        if ((point.distance(this.start()) + point.distance(this.end()) - this.length()) < epsilon) {
            return true;
        }
        return false;
    }

    /**
     * isIntersecting - checks if this line is intersecting with another line
     * by using the "intersectionWith".
     *
     * @param other the other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        //checking by using the "intersectionWith" method
        Point temp = this.intersectionWith(other);
        return (temp != null); //return true if there is an intersection point, false otherwise
    }

    /**
     * intersectionVerticalToX - checks intersection in cases where both lines are perpendicular to X or one line is
     * vertical to X and the other is a single point/regular line.
     *
     * @param other the other line that intersect
     * @return the intersection point if exist, null otherwise
     */
    public Point intersectionVerticalToX(Line other) {
        //checks if both lines are perpendiculars to the X axis and not fogging
        if ((this.isVerticalToX) && (other.isVerticalToX)) {
            //checks if the lines are the continuation of each other
            if (this.start().equals(other.start()) || this.end().equals(other.start())) {
                return other.start();
            } else if (this.start().equals(other.end()) || this.end().equals(other.end())) {
                return other.end();
            }
            return null; //the lines are not connected
        } else if ((this.isVerticalToX) && (other.length() == 0)) { //checks if one line is vertical to 'X'
            // and the other is a single point
            if (this.isPointOnLine(other.point1)) { //the point is on the line
                return other.point1;
            } else { // the point is not on the line
                return null;
            }
        } else if ((other.isVerticalToX) && (this.length() == 0)) { // checks the opposite case
            if (other.isPointOnLine(this.point1)) { //the point is on the line
                return this.point1;
            } else { // the point is not on the line
                return null;
            }
        } else if ((this.isVerticalToX) && other.length() > 0) { //checks if one line is vertical to X and the other
            // line is regular
            Double intersecX = this.point1.getX();
            Double intersecY = (intersecX * other.slope) + other.equation[1];
            Point checkPoint = new Point(intersecX, intersecY);
            //checks if the intersection point is on the lines
            if (this.isPointOnLine(checkPoint) && other.isPointOnLine(checkPoint)) { //the point is on both lines
                return checkPoint;
            } else { // the intersection point is not on both lines
                return null;
            }
        } else if ((other.isVerticalToX) && this.length() > 0) { //checks the opposite case
            Double intersecX = other.point1.getX();
            Double intersecY = (intersecX * this.slope) + this.equation[1];
            Point checkPoint = new Point(intersecX, intersecY);
            //checks if the intersection point is on the lines
            if (this.isPointOnLine(checkPoint) && other.isPointOnLine(checkPoint)) { //the point is on both lines
                return checkPoint;
            } else { // the intersection point is not on both lines
                return null;
            }
        } else { //the case is not one of those cases
            return null;
        }
    }

    /**
     * .
     * intersectionVerticalToY - checks intersection in cases where one line is vertical to Y and the other
     * is a single point
     *
     * @param other the other line that intersect
     * @return the intersection point if exist, null otherwise
     */
    public Point intersectionVerticalToY(Line other) {
        if ((other.length() == 0) && (this.slope == 0)) { //checks if one line is vertical to 'Y'
            //and the other is a single point
            if (this.isPointOnLine(other.point1)) { //checks if the point is on the line
                return other.point1;
            } else { //the point is not on the line
                return null;
            }
        } else if ((this.length() == 0) && (other.slope == 0)) { //checks the opposite case
            if (other.isPointOnLine(this.point1)) { //checks if the point is on the line
                return this.point1;
            } else { //the point is not on the line
                return null;
            }
        } else { //the case in not one of those cases
            return null;
        }

    }

    /**
     * .
     * intersectionLineAndPoint - checks intersection in cases where on line is regular and the other line
     * is a single point
     *
     * @param other the other line that intersect
     * @return the intersection point if exist, null otherwise
     */
    public Point intersectionLineAndPoint(Line other) {
        if (this.length() > 0 && other.length() == 0) { //checks if one of the lines is a single point
            if (this.isPointOnLine(other.point1)) { //checks if the intersection point is on the line
                return other.point1;
            } else {
                return null;
            }
        } else if (other.length() > 0 && this.length() == 0) { // checks the opposite case
            if (other.isPointOnLine(this.point1)) { //checks if the intersection point is on the line
                return this.point1;
            } else {
                return null;
            }
        } else { // this case is non of those cases
            return null;
        }

    }

    /**
     * .
     * isFogging methos checks if two lines are fogging by comparing their points
     *
     * @param other the other line we want to check if its fogging with this line
     * @return true if the lines are fogging, false otherwise
     */
    public boolean isFogging(Line other) { //checks if the points are equal
        return (this.start().equals(other.start()) && this.end().equals(other.end()))
                || (this.start().equals(other.end()) && this.end().equals(other.start()));
        //return true if the lines are fogging, false otherwise
    }


    /**
     * .
     * intersectionWith - checks if two lines are intersecting by comparing their qualities and slopes
     *
     * @param other the other line
     * @return the intersection point if the lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        if (isFogging(other)) { //checks if the lines are fogging. if so - return null
            return null;
        } else if (this.isVerticalToX || other.isVerticalToX) { //case: at least one of the lines vertical to X
            return this.intersectionVerticalToX(other);
        } else if (((this.slope != null) && (this.slope == 0) && (other.length() == 0))
                || ((other.slope != null) && (other.slope == 0) && (this.length() == 0))) { //case: one of the lines
            //vertical to Y and the other line is a single point
            return this.intersectionVerticalToY(other);
        } else if ((this.length() > 0 && other.length() == 0) || (other.length() > 0 && this.length() == 0)) { //case:
            //one of the lines is regular and the other is a single point
            return this.intersectionLineAndPoint(other);
        } else if (this.length() == 0 && other.length() == 0) { //checks if both lines are single points
            if (this.point1.equals(other.point1)) { //the points are equals
                return this.point1;
            }
        } else if ((this.slope.equals(other.slope))) { //checks if the lines are parallel or fogging in one point
            //by comparing their slopes
            if (this.end().equals(other.start())) { // checks if the lines are fogging in one point
                return this.end();
            } else if (this.start().equals(other.end())) { //checks the opposite case
                return this.start();
            }
            return null;
        } else { //both lines are regular lines
            //finding the X value of the intersection point by comparing the equations
            Double intersecX = ((other.equation[1] - this.equation[1]) / (this.equation[0] - other.equation[0]));
            //finding the Y value of the intersection point by using this line equation
            Double intersecY = ((this.slope) * (intersecX) + (this.equation[1]));
            //declaring the intersection point
            Point intersec = new Point(intersecX, intersecY);
            //checks if the point is on both lines by using the "isPointOnLine" method
            if (this.isPointOnLine(intersec) && other.isPointOnLine(intersec)) {
                //the intersection point is on both lines
                return intersec;
            } else { //the intersection point if not on both lines
                return null;
            }
        }
        return null;

    }


    /**
     * .
     * equals checks if two lines are equal by comparing their start and end points
     *
     * @param other the other line we want to compare
     * @return true if the lines equal, false otherwise
     */
    public boolean equals(Line other) {
        //checking if the start and end's points are equal
        if ((this.start().equals(other.start())) && (this.end().equals(other.end()))) {
            return true;
        }
        return false;

    }

    /**
     * .
     * closestIntersectionToStartOfLine method gets the closest intersection point between this line to a specified
     * rectangle to the start of this line
     *
     * @param rect the specifies rectangle
     * @return the closest point, could be null if there are not intersection points.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //checks if the list of intersection points is empty
        if (rect.intersectionPoints(this).size() == 0) {
            return null;
        } else { //checks for the closest point to the start point of this line
            Point closestPoint = rect.intersectionPoints(this).get(0); //set the first point as the closest point
            Point tempPoint;
            for (int i = 1; i < rect.intersectionPoints(this).size(); i++) { //run on the rest of the list
                tempPoint = rect.intersectionPoints(this).get(i);
                if (tempPoint.distance(this.start()) < closestPoint.distance(this.start())) { //check for the closest
                    closestPoint = tempPoint; //updating the closest point
                }
            }
            return closestPoint;
        }
    }


}
