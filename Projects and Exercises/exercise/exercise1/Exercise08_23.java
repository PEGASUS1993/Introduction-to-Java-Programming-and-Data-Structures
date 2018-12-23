import java.util.Scanner;

public class Exercise08_23 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int[][] matrix = new int[6][6];
    
    // Prompt the user for input
    System.out.println("Enter a 6-by-6 matrix: ");
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        matrix[i][j] = input.nextInt();
      }
    }
    
    if (isEvenParity(matrix))
      System.out.println("All rows and columns are even");
    else {
      int[] location = locateACell(matrix);
      System.out.println("The flipped cell is at ("
        + location[0] + ", " + location[1] + ")"); 
    }
  }
  
  public static boolean isEvenParity(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      int sum = 0;
      for (int j = 0; j < matrix[i].length; j++)
        sum += matrix[i][j];
      if (sum % 2 != 0)
        return false;
    }
    
    for (int j = 0; j < matrix[0].length; j++) {
      int sum = 0;
      for (int i = 0; i < matrix.length; i++)
        sum += matrix[i][j];
      if (sum % 2 != 0)
        return false;
    }
        
    return true;  
  }
  
  public static int[] locateACell(int[][] matrix) {
    int[] result = new int[2];
    for (int i = 0; i < matrix.length; i++) {
      int sum = 0;
      for (int j = 0; j < matrix[i].length; j++)
        sum += matrix[i][j];
      if (sum % 2 != 0) {
        result[0] = i;
        break;
      }
    }
    
    for (int j = 0; j < matrix[0].length; j++) {
      int sum = 0;
      for (int i = 0; i < matrix.length; i++)
        sum += matrix[i][j];
      if (sum % 2 != 0) {
        result[1] = j;
        break;
      }
    }
        
    return result;  
  }
}
