import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }
    public static void main(String[] args) {
    }

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
	}
    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY;
        if (this.x == that.x) return Double.POSITIVE_INFINITY;
        if (this.y == that.y) return +0.0;
        return (double) (that.y - this.y) / (that.x - this.x);
    }

    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y == that.y) return this.x - that.x;
        return this.y - that.y;
    }

    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        return new SlopeOrder();
    }

    private class SlopeOrder implements Comparator<Point> {
        @Override
        public int compare(Point a, Point b) {
            if (this.slopeTo(a) > this.slopeTo(b)) return +1;
            if (this.slopeTo(a) < this.slopeTo(b)) return -1;
            return 0;
        }
    }
}
