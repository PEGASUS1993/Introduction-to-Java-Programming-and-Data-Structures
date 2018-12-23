import java.util.*;

public class Exercise11_05 {
  public static void main(String[] args) {
    Course course1 = new Course("Data Structures");
    Course course2 = new Course("Database Systems");

    course1.addStudent("Peter Jones");
    course1.addStudent("Brian Smith");
    course1.addStudent("Anne Kennedy");

    course2.addStudent("Peter Jones");
    course2.addStudent("Steve Smith");

    System.out.println("Number of students in course1: "
        + course1.getNumberOfStudents());
    String[] students = course1.getStudents();
    for (int i = 0; i < course1.getNumberOfStudents(); i++)
      System.out.print(students[i] + ", ");

    System.out.println();
    System.out.print("Number of students in course2: "
        + course2.getNumberOfStudents());
  }
}

class Course {
  private String courseName;
  private ArrayList<String> students = new ArrayList<>();
    
  public Course(String courseName) {
    this.courseName = courseName;
  }
  
  public void addStudent(String student) {
    students.add(student);
  }
  
  public String[] getStudents() {
    String[] result = new String[students.size()];
    for (int i = 0; i < students.size(); i++) {
      result[i] = students.get(i);
    }
    return result;
    
    // Or alternatively 
//    String[] result = new String[students.size()];
//    return students.toArray(result);
  }

  public int getNumberOfStudents() {
    return students.size();
  }  

  public String getCourseName() {
    return courseName;
  }  
  
  public void dropStudent(String student) {
    students.remove(student);
  }
}
