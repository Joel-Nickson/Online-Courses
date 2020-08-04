import java.util.ArrayList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
  private Node root;
  private int size = 0;
  private boolean drawLines = false;

  private static class Node {
    private Point2D point;
    private RectHV rect;
    private Node left, right;

    // for debugging
    // public int index;

    public Node(Point2D p, RectHV r, boolean vertical) {
      this.point = p;
      if (r == null) {
        this.rect = new RectHV(0.0, 0.0, 1.0, 1.0);
      } else {
        this.rect = r;
      }
    }
  }

  // construct an empty tree of points
  public KdTree() {
    root = null;
  }

  // is the set empty?
  public boolean isEmpty() {
    return root == null;
  }

  // number of points in the set
  public int size() {
    return size;
  }

  // add the point to the tree (if it is not already in the tree)
  public void insert(Point2D p) {
    if (p == null) {
      throw new java.lang.NullPointerException();
    }

    if (isEmpty()) {
      root = insert(root, p, null, true);
    } else {
      root = insert(root, p, root.rect, true);
    }
  }

  private Node insert(Node n, Point2D p, RectHV r, boolean vertical) {
    if (n == null) {
      size++;
      Node tmpNode = new Node(p, r, vertical);

      // tmpNode.index = size;
      return tmpNode;
    }

    if (n.point.equals(p)) {
      return n;
    }

    if (vertical) {
      if (Point2D.X_ORDER.compare(p, n.point) < 0) {
        // p is on the left of the parent node
        if (n.left == null) {
          n.left = insert(n.left, p, 
              new RectHV(r.xmin(), r.ymin(), n.point.x(), r.ymax()), !vertical);
        } else {
          n.left = insert(n.left, p, n.left.rect, !vertical);
        }
      } else {
        // p is on the right of the parent node
        if (n.right == null) {
          n.right = insert(n.right, p, 
              new RectHV(n.point.x(), r.ymin(), r.xmax(), r.ymax()), !vertical);
        } else {
          n.right = insert(n.right, p, n.right.rect, !vertical);
        }
      }
    } else {
      if (Point2D.Y_ORDER.compare(p, n.point) < 0) {
        // p is below the parent node (hence go left)
        if (n.left == null) {
          n.left = insert(n.left, p, 
              new RectHV(r.xmin(), r.ymin(), r.xmax(), n.point.y()), !vertical);
        } else {
          n.left = insert(n.left, p, n.left.rect, !vertical);
        }
      } else {
        // p is above the parent node (hence go right)
        if (n.right == null) {
          n.right = insert(n.right, p, 
              new RectHV(r.xmin(), n.point.y(), r.xmax(), r.ymax()), !vertical);
        } else {
          n.right = insert(n.right, p, n.right.rect, !vertical);
        }
      }
    }

    return n;
  }

  // does the tree contains point p?
  public boolean contains(Point2D p) {
    return contains(root, p, true);
  }

  private boolean contains(Node n, Point2D p, boolean vertical) {
    if (n == null) {
      return false;
    }

    if (n.point.equals(p)) {
      return true;
    }

    if (vertical) {
      if (Point2D.X_ORDER.compare(p, n.point) < 0) {
        return contains(n.left, p, !vertical);
      } else {
        return contains(n.right, p, !vertical);
      }
    } else {
      if (Point2D.Y_ORDER.compare(p, n.point) < 0) {
        return contains(n.left, p, !vertical);
      } else {
        return contains(n.right, p, !vertical);
      }
    }
  }

  // draw all points to standard draw
  public void draw() {
    draw(root, true);
  }

  private void draw(Node r, boolean vertical) {
    if (r == null) {
      return;
    }

    draw(r.left, !vertical);

    if (drawLines) {
      if (vertical) {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.005);

        (new Point2D(r.point.x(), r.rect.ymin())).
        drawTo(new Point2D(r.point.x(), r.rect.ymax()));

      } else {
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.005);

        (new Point2D(r.rect.xmin(), r.point.y())).
        drawTo(new Point2D(r.rect.xmax(), r.point.y()));
      }
    }

    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.setPenRadius(0.01);
    r.point.draw();

    // StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 16));
    // StdDraw.textRight(r.point.x(), r.point.y(), Integer.toString(r.index));

    draw(r.right, !vertical);
  }

  // all points that are inside the rectangle
  public Iterable<Point2D> range(RectHV rect) {
    ArrayList<Point2D> pointsInRange = new ArrayList<Point2D>();
    range(root, rect, pointsInRange);
    return pointsInRange;
  }

  private void range(Node n, RectHV rect, ArrayList<Point2D> pointsInRange) {

    if (n == null) {
      return;
    }

    if (!rect.intersects(n.rect)) {
      return;
    }
    // point is in rectangle
    if (rect.contains(n.point)) {
      pointsInRange.add(n.point);
    }

    // go left and right
    range(n.left, rect, pointsInRange);
    range(n.right, rect, pointsInRange);
  }

  // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    if (isEmpty()) {
      return null;
    }
    return nearest(root, p, root.point, true);
  }

  private Point2D nearest(Node n, Point2D q, 
      Point2D bestCandidate, boolean vertical) {
    if (n == null) {
      return bestCandidate;
    }

    if (n.rect.distanceSquaredTo(q) > bestCandidate.distanceSquaredTo(q)) {
      return bestCandidate;
    }

    Point2D tmpBestCandidate = bestCandidate;

    if (q.distanceSquaredTo(n.point) < q.distanceSquaredTo(bestCandidate)) {
      tmpBestCandidate = n.point;
    }

    boolean goLeft = vertical ? 
        Point2D.X_ORDER.compare(q, n.point) < 0 : 
          Point2D.Y_ORDER.compare(q, n.point) < 0;

    tmpBestCandidate = 
        nearest(goLeft ? n.left : n.right, q, tmpBestCandidate, !vertical);
    tmpBestCandidate = 
        nearest(goLeft ? n.right : n.left, q, tmpBestCandidate, !vertical);

    return tmpBestCandidate;
  }

  // unit testing of the methods (optional)
  public static void main(String[] args) {
  }
}