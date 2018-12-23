public class Exercise05_11Extra {
  public static void main(String[] args) {
    System.out.printf("%-15s%-15s\n", "RealNumber", "SquareRoot");
    System.out.println("-------------------------------");

    for (int num = 0; num <= 20; num++) {
      System.out.printf("%-15d%-15.4f\n", num, Math.sqrt(num));
    }
  }
}
