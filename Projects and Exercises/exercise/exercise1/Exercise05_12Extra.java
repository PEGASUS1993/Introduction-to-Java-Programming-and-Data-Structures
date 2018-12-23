public class Exercise05_12Extra {
  public static void main(String[] args) {
    System.out.println("Degree\tSin\t\tCos");

    for (int degree = 0; degree <= 360; degree += 10) {
      System.out.printf("%-3d\t%6.4f\t\t%6.4f\n", degree,
        Math.sin(degree * Math.PI / 180),
        Math.cos(degree * Math.PI / 180));
    }
  }

  /*
  public static double format(double d, int position) {
    return Math.round(d * Math.pow(10, position)) / Math.pow(10, position);
  } */
}
