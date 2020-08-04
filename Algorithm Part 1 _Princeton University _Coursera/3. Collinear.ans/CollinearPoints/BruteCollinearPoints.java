import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by spec on 26.04.2017.
 */
public class BruteCollinearPoints {
    private ArrayList<LineSegment> segments;

    public BruteCollinearPoints(Point[] pointsOriginal) {
        checkCornerCases(pointsOriginal);

        Point[] points = Arrays.copyOf(pointsOriginal, pointsOriginal.length);
        Arrays.sort(points);

        segments = new ArrayList<>();
        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                for (int r = q + 1; r < points.length - 1; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]) &&
                                points[p].slopeTo(points[r]) == points[p].slopeTo(points[s])) {
                            segments.add(new LineSegment(points[p], points[s]));
                        }
                    }
                }
            }
        }
    }

    private void checkCornerCases(Point[] points) {
        if (points == null) throw new NullPointerException();

        for (Point p : points) if (p == null) throw new NullPointerException();

        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();
            }
        }
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }

    public int numberOfSegments() {
        return segments.size();
    }
}