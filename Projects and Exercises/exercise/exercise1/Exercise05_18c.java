// Solution 1
//public class Exercise05_18c {
//  /** Print Pattern C */
//  public static void main(String[] args) {
//    for (int i = 1; i <= 6; i++) {
//      // Print leading space
//      for (int j = 6 - i; j >= 1; j--)
//        System.out.print("  ");
//      
//      for (int j = i; j >= 1; j--)
//        System.out.print(j + " ");
//      System.out.println();
//    }
//  }
//}
//

// Solution 2
// Here is a simpler version
public class Exercise05_18c {
  /** Print Pattern C */
  public static void main(String[] args) {
    for (int i = 1; i <= 6; i++) {
      for (int j = 6; j >= 1; j--)
        System.out.print(j <= i ? j + " " : "  ");
      System.out.println();
    }
  }
}

