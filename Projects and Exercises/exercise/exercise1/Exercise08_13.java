import java.util.Scanner;

public class Exercise08_13 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print
      ("Enter the number of rows and columns of the array: ");
    int numberOfRows = input.nextInt();
    int numberOfColumns = input.nextInt();
    
    double[][] a = new double[numberOfRows][numberOfColumns];
    
    System.out.println("Enter the array: ");
    for (int i = 0; i < a.length; i++)
      for (int j = 0; j < a[i].length; j++)
        a[i][j] = input.nextDouble();
    
    int[] location = locateLargest(a);
    System.out.println("The location of the largest element is at ("
      + location[0] + ", " + location[1] + ")");
  }
  
  public static int[] locateLargest(double[][] a) {
    int[] location = new int[2];
    
    double largest = a[0][0];
    for (int i = 0; i < a.length; i++)
      for (int j = 0; j < a[i].length; j++) {
        if (largest < a[i][j]) {
          largest = a[i][j];
          location[0] = i;
          location[1] = j;
        }
      }
        
    return location;
  }
}
