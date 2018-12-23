import java.util.Scanner;

public class Exercise07_01 {
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

    // Assign and display grades
    for (int i = 0; i < scores.length; i++) {
      if (scores[i] >= best - 10)
        grade = 'A';
      else if (scores[i] >= best - 20)
        grade = 'B';
      else if (scores[i] >= best - 30)
        grade = 'C';
      else if (scores[i] >= best - 40)
        grade = 'D';
      else
        grade = 'F';

      System.out.println("Student " + i + " score is " +
        scores[i] + " and grade is " + grade);
    }
  }
}
