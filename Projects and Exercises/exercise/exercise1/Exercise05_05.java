public class Exercise05_05 {
  public static void main(String[] args) {
    System.out.printf("%-10s%10s    |  %-10s%10s\n", "kilograms", "pounds",
        "pounds", "kilograms");
    System.out.println("------------------------------------------------");

    // Use while loop
    int kilograms = 1;
    int pounds = 20;
    while (kilograms <= 199) {
      System.out.printf("%-10d%10.1f    |  %-10d%10.2f\n", kilograms,
        kilograms * 2.2, pounds, pounds / 2.2);
      kilograms += 2;
      pounds += 5;
    }

// Alternative 
//    do {
//      System.out.printf("%-10d%10.1f    |  %-10d%10.2f\n", kilograms,
//        kilograms * 2.2, pounds, pounds / 2.2);
//      kilograms += 2;
//      pounds += 5;
//    } while (kilograms <= 199);
    
    /**
     * Alternatively use for loop int kilograms = 1; int pounds = 20; for (int
     * count = 1; count <= 100; kilograms += 2, pounds += 5, count++) {
     * System.out.println(kilograms + "\t\t" + kilograms * 2.2 + "\t|\t" +
     * pounds + "\t\t" + pounds / 2.2); }
     */
  }
}
