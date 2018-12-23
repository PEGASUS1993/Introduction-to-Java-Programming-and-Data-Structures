public class Exercise05_06 {
  public static void main(String[] args) {
    System.out.printf("%10s%10s    |  %10s%10s\n", "Miles", "Kilometers",
        "Kilometers", "Miles");
    System.out.println("---------------------------------------------");

    // Use while loop
    int miles = 1;
    int kilometers = 20;
    int count = 1;
    while (count <= 10) {
      System.out.printf("%10d%10.3f    |  %10d%10.3f\n", miles, miles * 1.609,
          kilometers, kilometers / 1.609);
      miles++;
      kilometers += 5;
      count++;
    }

    /*
     * Use for loop int miles = 1; int kilometers = 20; for (int count = 1;
     * count <= 10; miles++, kilometers += 5, count++) {
     * System.out.printf("%10d%10.3f    |  %10d%10.3f\n", miles, miles * 1.609,
     * kilometers, kilometers / 1.609); }
     */
  }
}
