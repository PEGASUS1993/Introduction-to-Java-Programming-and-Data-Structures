import java.util.Comparator;
import java.util.stream.Stream;

public class Exercise30_09 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Prompt the user to enter the number of students
    System.out.print("Enter the number of students: ");
    int numberOfStudents = input.nextInt();

    Student[] students = new Student[numberOfStudents];
    
    // Enter student name and score
    for (int i = 0; i < students.length; i++) {
      System.out.print("Enter a student name: ");
      String name = input.next();
      System.out.print("Enter a student score: ");
      double score = input.nextDouble();
      students[i] = new Student(name, score);
    }
    
    Stream.of(students).sorted(Comparator.comparing(Student::getScore))
      .forEach(student -> System.out.println(student.getName() + " " + student.getScore()));
  }
  
  static class Student {
    String name;
    double score;
    
    public Student(String name, double score) {
      this.name = name;
      this.score = score;
    }
    
    public String getName() {
      return name;
    }
    
    public double getScore() {
      return score;
    }
  }
}
