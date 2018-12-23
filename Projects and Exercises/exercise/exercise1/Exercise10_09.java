public class Exercise10_09 {
  public static void main(String[] args) {
    CourseNew course1 = new CourseNew("Data Structures");
    CourseNew course2 = new CourseNew("Database Systems");

    course1.addStudent("Peter Jones");
    course1.addStudent("Brian Smith");
    course1.addStudent("Anne Kennedy");
    course1.addStudent("Susan Kennedy");
    course1.addStudent("John Kennedy");
    course1.addStudent("Kim Johnson");
    course1.addStudent("S1");
    course1.addStudent("S2");
    course1.addStudent("S3");
    course1.addStudent("S4");
    course1.addStudent("S5");
    course1.addStudent("S6");
    course1.addStudent("S7");
      
    course2.addStudent("Peter Jones");
    course2.addStudent("Steve Smith");

    System.out.println("Number of students in course1: "
      + course1.getNumberOfStudents());
    String[] students = course1.getStudents();
    for (int i = 0; i < students.length; i++)
      System.out.print(students[i] + ", ");
    
    System.out.println();
    System.out.print("Number of students in course2: "
      + course2.getNumberOfStudents());
    
    course1.dropStudent("S1");
    System.out.println("Number of students in course1: "
      + course1.getNumberOfStudents());
    students = course1.getStudents();
    for (int i = 0; i < course1.getNumberOfStudents(); i++)
      System.out.print(students[i] + (i < course1.getNumberOfStudents() - 1 ? ", " : " "));
    
    course1.clear();
    System.out.println("\nNumber of students in course1: "
      + course1.getNumberOfStudents());
  }
}

class CourseNew {
  private String name;
  private String[] students = new String[2];
  private int numberOfStudents;

  public CourseNew(String name) {
    this.name = name;
  }

  public void addStudent(String student) {
    if (numberOfStudents == students.length) {
      increaseSize();
    }

    students[numberOfStudents] = student;
    numberOfStudents++;
  }
  
  private void increaseSize() {
    String[] temp = new String[students.length * 2 + 1];
    System.arraycopy(students, 0, temp, 0, students.length);
    students = temp;
  }

  public String[] getStudents() {
    String[] result = new String[numberOfStudents];
    for (int i = 0; i < numberOfStudents; i++)
      result[i] = students[i];   
    return result;
  }

  public int getNumberOfStudents() {
    return numberOfStudents;
  }
  
  /** Clear the course */
  public void clear() {
    for (int i = 0; i < numberOfStudents; i++) 
      students[i] = null;
    numberOfStudents = 0;
  }
  
  /** Remove a student from the course */
  public void dropStudent(String student) {
    for (int i = 0; i < numberOfStudents; i++) {
      if (students[i].equals(student)) {
        // Move students[i + 1] to students[i], etc.
        for (int k = i + 1; k < numberOfStudents; k++) {
          students[k - 1] = students[k];
        }
        
        numberOfStudents--;
        break;
      }
    }
  }
}


