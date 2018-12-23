public class Exercise13_13 {
  /** Main method */
  public static void main(String[] args) {
    Course1 course1 = new Course1("DS");
    course1.addStudent("S1");
    course1.addStudent("S2");
    course1.addStudent("S3");

    Course1 course2 = (Course1) course1.clone();
    course2.addStudent("S4");
    course2.addStudent("S5");
    course2.addStudent("S6");

    System.out.println("Students in course1: ");
    for (int i = 0; i < course1.getNumberOfStudents(); i++) 
      System.out.print(course1.getStudents()[i] + " ");
    System.out.println("\nStudents in course1: ");
    for (int i = 0; i < course2.getNumberOfStudents(); i++) 
      System.out.print(course2.getStudents()[i] + " ");
  }
}

class Course1 implements Cloneable {
  private String courseName;
  private String[] students = new String[100];
  private int numberOfStudents;

  public Course1(String courseName) {
    this.courseName = courseName;
  }

  public void addStudent(String student) {
    students[numberOfStudents] = student;
    numberOfStudents++;
  }

  public String[] getStudents() {
    return students;
  }

  public int getNumberOfStudents() {
    return numberOfStudents;
  }

  public String getCourse1Name() {
    return courseName;
  }

  public void dropStudent(String student) {
    // Left as an exercise in Exercise 10.9
  }

  public Object clone() {
    try {
      Course1 c = (Course1) super.clone();
      c.students = new String[100];
      System.arraycopy(students, 0, c.students, 0, 100); 
      // The contents in the array are strings. Strings are immutable. 
      // So it is OK to use arraycopy here. If students is an array of mutable objects, 
      // you need to perform deep-deep clones.
      c.numberOfStudents = numberOfStudents;
      return c;
    } catch (CloneNotSupportedException ex) {
      return null;
    }
  }
}