public class Exercise05_04 {
  public static void main(String[] args) {
    System.out.println("Miles\t\tKilometers");
    System.out.println("-------------------------------");

    // Use while loop
    int miles = 1; 
    while (miles <= 10) {
      System.out.println(miles + "\t\t" + miles * 1.609);
      miles++;
    }
   
/** Alternatively use for loop    
    for (int miles = 1; miles <= 10; miles++) {
      System.out.println(miles + "\t\t" + miles * 1.609);
    }
*/
  }
}
