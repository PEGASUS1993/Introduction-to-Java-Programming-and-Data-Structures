public class Exercise05_14Extra {
  public static void main(String[] args) {
    double value = Math.random();
    while (value <= 0.75) {
      System.out.println(value);
      value = Math.random();
    }
    System.out.println(value);
  }
}