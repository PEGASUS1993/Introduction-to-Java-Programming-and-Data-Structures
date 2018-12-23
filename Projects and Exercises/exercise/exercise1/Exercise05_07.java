public class Exercise05_07 {
  public static void main(String[] args) {
    // Use a while loop
    double tuition = 10000;
    int count = 1; 
    while (count <= 10) {
      tuition = tuition * 1.05;
      count++;
    }
  
/*    
    // Alternatively use a for loop
    double tuition = 10000;
    for (int i = 1; i <= 10; i++) {
      tuition = tuition * 1.05;
    }
*/
    System.out.println("Tuition in ten years is " + tuition);

    double sum = tuition;
    for (int i = 2; i <= 4; i++) {
      tuition = tuition * 1.05;
      sum += tuition;
    }

    System.out.println("The four-year tuition in ten years is " + sum);
  }
}
