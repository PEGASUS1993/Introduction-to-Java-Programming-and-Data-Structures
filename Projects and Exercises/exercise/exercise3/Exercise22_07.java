import java.util.*;
import javafx.geometry.Point2D;

/** This program works, but the design is not good. Redesign it */
public class Exercise22_07 {
  public static void main(String[] args) {
    double[][] points = new double[1000][2];
    for (int i = 0; i < points.length; i++) {
      points[i][0] = Math.random() * 100;
      points[i][1] = Math.random() * 100;
    }

    long startTime = System.currentTimeMillis();
    Pair pair = closestPairBruteForce(points);
    System.out.println("shortest distance is " + pair.getDistance());
    System.out.println(pair);
    long endTime = System.currentTimeMillis();
    System.out.println("Time spent on the brute-force algorithm is "
      + (endTime - startTime) + " milliseconds");
      
    startTime = System.currentTimeMillis();
    Pair closestPair = getClosestPair(points);
    System.out.println("shortest distance is " + closestPair.getDistance());
    System.out.println(closestPair);
    endTime = System.currentTimeMillis();
    System.out.println("Time spent on the divide-and-conquer algorithm is "
      + (endTime - startTime) + " milliseconds");
  }

  /** Return the distance of the closest pair of points */
  public static Pair getClosestPair(double[][] points) {
    Point2D[] pointsOrderedOnX = new Point2D[points.length];

    for (int i = 0; i < pointsOrderedOnX.length; i++)
      pointsOrderedOnX[i] = new Point2D(points[i][0], points[i][1]);
    
    return getClosestPair(pointsOrderedOnX);
  }

  /** Return the distance of the closest pair of points */
  public static Pair getClosestPair(Point2D[] points) {
    Arrays.sort(points, Comparator.comparing(Point2D::getX).thenComparing(Point2D::getY));

    // Locate the identical points if exists
    Pair pair = checkIdentical(points);
    if (pair != null)
      return pair; // The distance between the identical points is 0

    Point2D[] pointsOrderedOnY = points.clone();
    Arrays.sort(pointsOrderedOnY, Comparator.comparing(Point2D::getY).thenComparing(Point2D::getX));

    return distance(points, 0, points.length - 1, pointsOrderedOnY);
  }

  /** Locate the identical points if exist */
  public static Pair checkIdentical(Point2D[] pointsOrderedOnX) {
    Pair pair = new Pair();
    for (int i = 0; i < pointsOrderedOnX.length - 1; i++) {
      if (pointsOrderedOnX[i].getX() == pointsOrderedOnX[i + 1].getX() && 
          pointsOrderedOnX[i].getY() == pointsOrderedOnX[i + 1].getY()) {
        pair.p1 = pointsOrderedOnX[i];
        pair.p2 = pointsOrderedOnX[i + 1];
        return pair;
      }
    }

    return null;
  }

  /** Return the distance of the closest pair of points 
    * in pointsOrderedOnX[low..high]. This is a recursive method. 
    * pointsOrderedOnX and pointsOrderedOnY are not changed in the 
    * subsequent recursive calls.
    */
  public static Pair distance(Point2D[] pointsOrderedOnX, 
      int low, int high, Point2D[] pointsOrderedOnY) {
    if (low >= high) // Zero or one point in the set
      return null;
    else if (low + 1 == high) {
      // Two points in the set
      Pair pair = new Pair();
      pair.p1 = pointsOrderedOnX[low];
      pair.p2 = pointsOrderedOnX[high];
      return pair;
    }

    // Step 2
    int mid = (low + high) / 2;
    Pair pair1 = distance(pointsOrderedOnX, low, mid, pointsOrderedOnY);
    Pair pair2 = distance(pointsOrderedOnX, mid + 1, high, pointsOrderedOnY);

    double d;
    Pair pair = null;
    if (pair1 == null && pair2 == null) {
      d = Double.MAX_VALUE;
    } else if (pair1 == null) {
      d = pair2.getDistance();
      pair = pair2;
    } else if (pair2 == null) {
      d = pair1.getDistance();
      pair = pair1;
    } else {
      d = Math.min(pair1.getDistance(), pair2.getDistance());
      if (pair1.getDistance() <= pair2.getDistance())
        pair = pair1;
      else
        pair = pair2;
    }

    // Obtain stripL and stripR
    ArrayList<Point2D> stripL = new ArrayList<>();
    ArrayList<Point2D> stripR = new ArrayList<>();
    for (int i = 0; i < pointsOrderedOnY.length; i++)
      if (pointsOrderedOnY[i].getX() <= pointsOrderedOnX[mid].getX()
          && pointsOrderedOnY[i].getX() >= pointsOrderedOnX[mid].getX() - d)
        stripL.add(pointsOrderedOnY[i]);
      else
        stripR.add(pointsOrderedOnY[i]);

    // Step 3: Find the closest pair for a point in stripL and
    // a point in stripR
    double d3 = d;
    int r = 0;
    for (int i = 0; i < stripL.size(); i++) {
      while (r < stripR.size() && stripL.get(i).getY() > stripR.get(r).getY() + d)
        r++;

      // Compare a point in stripL with at most six points in stripR
      int r1 = r; // Start from r1 up in stripR
      while (r1 < stripR.size() && stripR.get(r1).getY() <= stripL.get(i).getY() + d) {
        if (d3 > distance(stripL.get(i), stripR.get(r1))) {
          d3 = distance(stripL.get(i), stripR.get(r1));
          pair.p1 = stripL.get(i);
          pair.p2 = stripR.get(r1);
        }
        r1++;
      }
    }

    return pair;
  }

  /** Compute the distance between two points p1 and p2 */
  public static double distance(Point2D p1, Point2D p2) {
    return distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
  }

  /** Compute the distance between points (x1, y1) and (x2, y2) */
  public static double distance(double x1, double y1, 
      double x2, double y2) {
    return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
  }

//  /** Define a class for a point with x- and y- coordinates */
//  static class Point implements Comparable<Point> {
//    double x;
//    double y;
//
//    Point(double x, double y) {
//      this.x = x;
//      this.y = y;
//    }
//
//    @Override
//    public int compareTo(Point p2) {
//      if (this.x < p2.x)
//        return -1;
//      else if (this.x == p2.x) {
//        // Secondary order on y-coordinates
//        if (this.y < p2.y)
//          return -1;
//        else if (this.y == p2.y)
//          return 0;
//        else
//          return 1;
//      } else
//        return 1;
//    }
//  }

  /**
   * A comparator for comparing points on their y-coordinates. If y-coordinates
   * are the same, compare their x-coordinator.
   */
//  static class CompareY implements java.util.Comparator<Point> {
//    public int compare(Point p1, Point p2) {
//      if (p1.y < p2.y)
//        return -1;
//      else if (p1.y == p2.y) {
//        // Secondary order on x-coordinates
//        if (p1.x < p2.x)
//          return -1;
//        else if (p1.x == p2.x)
//          return 0;
//        else
//          return 1;
//      } else
//        return 1;
//    }
//  }

  /** A class to represent two points */
  public static class Pair {
    Point2D p1;
    Point2D p2;

    public double getDistance() {
/*      if (p1 == null || p2 == null)
        return Double.MAX_VALUE;
      else */
        return distance(p1, p2);
    }

    @Override
    public String toString() {
      return "(" + p1.getX() + ", " + p1.getY() + ") and (" + p2.getX() + ", " + p2.getY() + ")";
    }
  }

  public static Pair closestPairBruteForce(double[][] points) {
    Point2D[] p = new Point2D[points.length];

    for (int i = 0; i < p.length; i++)
      p[i] = new Point2D(points[i][0], points[i][1]);
    
    return closestPairBruteForce(p);
  }
  
  public static Pair closestPairBruteForce(Point2D[] points) {
    Pair pair = new Pair();
    
    if (points.length < 2)
      return null;
    
    // p1 and p2 are the indices in the points array
    pair.p1 = points[0];
    pair.p2 = points[1];
    double shortestDistances = distance(pair.p1, pair.p2); // Initialize shortestDistances

    // Compute distance for every two points
    for (int i = 0; i < points.length; i++) {
      for (int j = i + 1; j < points.length; j++) {
        double distance = distance(points[i], points[j]); // Find distance

        if (distance < shortestDistances) {
          pair.p1 = points[i];
          pair.p2 = points[j];
          shortestDistances = distance; // Update shortestDistances
        }
      }
    }

    return pair;
  }
}
