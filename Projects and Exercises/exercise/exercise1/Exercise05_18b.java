public class Exercise05_18b {
  /** Print Pattern II */
  public static void main(String[] args) {
    for (int i = 1; i <= 6; i++) {
      for (int j = 1; j <= 7 - i; j++)
        System.out.print(j + " ");
      System.out.println();
    }
  }
}
