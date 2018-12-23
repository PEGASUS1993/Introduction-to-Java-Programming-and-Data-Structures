public class Exercise05_03 {
  public static void main(String[] args) {
    System.out.printf("%-14s%10s\n", "Kilograms", "Pounds");
    System.out.println("------------------------");

    int kilograms = 1;
    while (kilograms <= 199) {
      System.out.printf("%-14d%10.1f\n", kilograms, kilograms * 2.2);
      kilograms += 2;
    }
    
/** Alternatively use for loop    
    for (int kilograms = 1; kilograms <= 199; kilograms += 2) {
      System.out.println(kilograms + "\t\t" + kilograms * 2.2);
    }
*/
  }
}
