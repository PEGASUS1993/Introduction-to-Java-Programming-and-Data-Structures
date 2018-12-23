import java.io.File;
import java.util.Scanner;

public class Exercise12_32 {
  private static String[][][] names = new String[10][2][5];
  
  public static void main(String[] args) {
    readNames();
    displayRankingSummary();
  }
  
  public static void displayRankingSummary() {
    System.out.printf("%-5s", "Year");

    for (int i = 1; i <= 5; i++)
      System.out.printf("%-10s", "Rank " + i);

    for (int i = 1; i <= 5; i++)
      System.out.printf("%-10s", "Rank " + i);
    
    System.out.println();
    
    for (int i = 9; i >= 0; i--) {
      System.out.printf("%-5d", 2001 + i);
      for (int j = 1; j >= 0; j--)
        for (int k = 0; k < 5; k++)
          System.out.printf("%-10s", names[i][j][k]);
      System.out.println();
    }
  }
  
  public static void readNames() {
     for (int i = 0; i < 10; i++) {
      String filename;
      if (i == 9)
        filename = "babynamesranking2010.txt";
      else
        filename = "babynamesranking200" + (i + 1) + ".txt";
      
      try (Scanner input = new Scanner(new File(filename))) {        
        for (int j = 0; j < 5; j++) {
          input.nextInt(); // Skip the ranking
          names[i][0][j] = input.next();
          input.nextInt(); // Skip the number of boy's name
          names[i][1][j] = input.next();
          input.nextInt(); // Skip the number of girl's name
        }
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
