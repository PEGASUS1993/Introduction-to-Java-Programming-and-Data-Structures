public class Exercise05_26 {
  public static void main(String[] args) {
    double e = 1;
    double item = 1;

    for (int i = 1; i <= 100000; i++) {
      item = item / i;
      e += item;

      if (i == 10000 || i == 20000 || i == 30000 || i == 40000 ||
          i == 50000 || i == 60000 || i == 70000 || i == 80000 ||
          i == 90000 || i == 100000)
      System.out.println("The e is " + e + " for i = " + i);
    }
  }
}
