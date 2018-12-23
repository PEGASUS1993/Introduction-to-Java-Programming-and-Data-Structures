import java.util.Scanner;

public class Exercise07_13Extra {
  /** Main method */
  public static void main(String[] args) {
    // Create a Scanner
    Scanner input = new Scanner(System.in);

    // Get number of students
    System.out.print("Enter number of students: ");
    int numberOfStudents = input.nextInt();

    double[] scores = new double[numberOfStudents]; // Array scores
    double best = 0; // The best score

    // Read scores and find the best score
    System.out.print("Enter " + numberOfStudents + " scores: ");
    for (int i = 0; i < scores.length; i++) {
      scores[i] = input.nextDouble();
      
      if (scores[i] > best)
        best = scores[i];
    }

    // Declare and initialize output string
    char grade; // The grade

    int count[] = new int[5];
    
    // Assign and display grades
    for (int i = 0; i < scores.length; i++) {
      if (scores[i] >= best - 10) {
        grade = 'A';
        count[0]++;
      }
      else if (scores[i] >= best - 20) {
        grade = 'B';
        count[1]++;
      }
      else if (scores[i] >= best - 30) {
        grade = 'C';
        count[2]++;
      }
      else if (scores[i] >= best - 40) {
        grade = 'D';
        count[3]++;
      }
      else {
        grade = 'F';
        count[4]++;
      }

      System.out.println("Student " + i + " score is " +
        scores[i] + " and grade is " + grade);
    }
    
    // Display a horizontal histogram
    for (int i = 0; i < count.length; i++) {
      if (i != 4)
        System.out.print((char)('A' + i) + ": ");
      else 
        System.out.print("F: ");
      
      for (int j = 0; j < count[i]; j++) {
        System.out.print("*");
      }
      System.out.println();
    }
  }
}
