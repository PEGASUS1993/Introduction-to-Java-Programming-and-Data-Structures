// Solution 1
//public class Exercise05_18d {
//  /** Print Pattern D */
//  public static void main(String[] args) {
//    for (int i = 1; i <= 6; i++) {
//      // Print leading space
//      for (int j = i; j > 1; j--)
//        System.out.print("  ");
//      
//      for (int j = 1; j <= 6 + 1 - i; j++)
//        System.out.print(j + " ");
//      System.out.println();
//    }
//  }
//}

// Solution 2: Here is a simpler version
public class Exercise05_18d {
  /** Print Pattern D */
  public static void main(String[] args) {
    for (int i = 1; i <= 6; i++) {
      for (int j = 1; j <= 6; j++)
        System.out.print(i <= j ? j + " " : "  ");
      System.out.println();
    }
  }
}