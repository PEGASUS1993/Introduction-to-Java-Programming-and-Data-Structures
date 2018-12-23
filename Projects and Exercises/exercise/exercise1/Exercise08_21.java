import java.util.Scanner;

public class Exercise08_21 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Enter the number of cities: ");
    int numberOfCities = input.nextInt();
    
    System.out.print("Enter the coordinates of the cities: ");
    double[][] cities = new double[numberOfCities][2];
    for (int i = 0; i < cities.length; i++) {
      cities[i][0] = input.nextDouble();
      cities[i][1] = input.nextDouble();     
    }
    
    // Find the central city and the total distance from the central city
    //  to all other cities
    double minTotal = totalDistance(cities, 0);
    int minIndex = 0;

    for (int i = 1; i < cities.length; i++) {
      double total = totalDistance(cities, i);
      
      if (minTotal > total) {
        minTotal = total;
        minIndex = i;
      }
    }
    
    System.out.println("The central city is at (" +
      cities[minIndex][0] + ", " + cities[minIndex][1] + ")");
    System.out.println("The total distance to all other cities is " +
      minTotal);
  }

  public static double totalDistance(double[][] cities, int i) {
    double total = 0;
    for (int j = 0; j < cities.length; j++) 
      total += distance(cities[i], cities[j]);
    return total;
  }
  
  public static double distance(double[] c1, double[] c2) {
    return Math.sqrt((c1[0] - c2[0]) * (c1[0] - c2[0]) +
      (c1[1] - c2[1]) * (c1[1] - c2[1]));
  }
}
