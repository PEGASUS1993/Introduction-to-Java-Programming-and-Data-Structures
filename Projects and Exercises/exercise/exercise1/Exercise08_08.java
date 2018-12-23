import java.util.Scanner;

public class Exercise08_08 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the number of points: ");
    int numberOfPoints = input.nextInt();
     
    // Create an array to store points
    double[][] points = new double[numberOfPoints][2];
    System.out.print("Enter " + numberOfPoints + " points: ");
    for (int i = 0; i < points.length; i++) {
      points[i][0] = input.nextDouble();
      points[i][1] = input.nextDouble();
    }

    // p1 and p2 are the indices in the points array
    int p1 = 0, p2 = 1; // Initial two points
    double shortestDistances = distance(points[p1][0], points[p1][1], 
      points[p2][0], points[p2][1]); // Initialize shortestDistances

    // Store a closest pair in (indices[i][0], indices[i][1]) 
    // The pair of the points for (indices[i][0], indices[i][1]) is
    // points[indices[i][0]] and points[indices[i][1]] 
    int[][] indices = new int[points.length][2];
    int k = 0;
    
    // Compute distance for every two points
    for (int i = 0; i < points.length; i++) {
      for (int j = i + 1; j < points.length; j++) {
        double distance = distance(points[i][0], points[i][1],
          points[j][0], points[j][1]); // Find distance

        if (distance < shortestDistances) {
          // Add a closest pair 
          k = 0; // Reset k for new closest pairs
          indices[k][0] = i;
          indices[k][1] = j;
          shortestDistances = distance; // Update shortestDistances 
        }
        else if (distance == shortestDistances) {
          // Add another closest pair with the same minimum distance
          indices[k][0] = i;
          indices[k][1] = j;
          k++;
        }
      }
    }

    // Display all closest pairs
    for (int i = 0; i < k; i++) {
      p1 = indices[i][0]; p2 = indices[i][1]; 
      System.out.println("The closest two points are " +
        "(" + points[p1][0] + ", " + points[p1][1] + ") and (" +
        points[p2][0] + ", " + points[p2][1] + ")");
    }
    
    System.out.println("Their distance is " + shortestDistances);
  }

  /** Compute the distance between two points (x1, y1) and (x2, y2)*/
  public static double distance(
      double x1, double y1, double x2, double y2) {
    return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
  }
}
