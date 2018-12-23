public class Exercise07_17 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Prompt the user to enter the number of students
    System.out.print("Enter the number of students: ");
    int numberOfStudents = input.nextInt();
    
    String[] names = new String[numberOfStudents];
    double[] scores = new double[numberOfStudents];

    // Enter student name and score
    for (int i = 0; i < scores.length; i++) {
      System.out.print("Enter a student name: ");
      names[i] = input.next();
      System.out.print("Enter a student score: ");
      scores[i] = input.nextDouble();
    }
    
    selectionSort(scores, names);
    
    System.out.println("Names in decreasing order of their scores are");
    for (int i = names.length - 1; i >= 0; i--)
      System.out.println(names[i] + " " + scores[i]);
  }
  
  /** The method for sorting the numbers */
  public static void selectionSort(double[] list, String[] names) {
    for (int i = 0; i < list.length - 1; i++) {
      // Find the minimum in the list[i..list.length-1]
      double currentMin = list[i];
      int currentMinIndex = i;

      for (int j = i + 1; j < list.length; j++) {
        if (currentMin > list[j]) {
          currentMin = list[j];
          currentMinIndex = j;
        }
      }

      // Swap list[i] with list[currentMinIndex] if necessary;
      if (currentMinIndex != i) {
        list[currentMinIndex] = list[i];
        list[i] = currentMin;
        
        // also swap the corresponding names
        String temp = names[currentMinIndex];
        names[currentMinIndex] = names[i];
        names[i] = temp;
      }
    }
  }
}
