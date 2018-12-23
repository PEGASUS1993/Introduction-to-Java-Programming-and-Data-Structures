public class Exercise11_02 {
  public static void main(String[] args) {
    Person person = new Person("Peter");
    Student student = new Student("Susan");
    Employee employee = new Employee("Eva");
    Faculty faculty = new Faculty("Frank");
    Staff staff = new Staff("Shane");
    
    System.out.println(person);
    System.out.println(student);
    System.out.println(employee);
    System.out.println(faculty);
    System.out.println(staff);
  }
}

class Person {
  protected String name;
  protected String address;
  protected String phoneNumber;
  protected String email;

  Person(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
  @Override
  public String toString() {
    return "Person: " + name;
  }
}

class Student extends Person {
  public static int FRESHMAN = 1;
  public static int SOPHOMORE = 2;
  public static int JUNIOR = 3;
  public static int SENIOR = 4;

  protected int status;

  Student(String name) {
    super(name);
  }
  
  @Override
  public String toString() {
    return "Student: " + getName();
  }
}

class Employee extends Person {
  protected String office;
  protected int salary;
  protected java.util.Calendar dateHired;

  Employee(String name) {
    super(name);
  }
  
  @Override
  public String toString() {
    return "Employee: " + getName();
  }
}

class Faculty extends Employee {
  public static int LECTURER = 1;
  public static int ASSISTANT_PROFESSOR = 2;
  public static int ASSOCIATE_PROFESSOR = 3;
  public static int PROFESSOR = 4;

  protected String officeHours;
  protected int rank;

  Faculty(String name) {
    super(name);
  }
  
  @Override
  public String toString() {
    return "Faculty: " + name;
  }
}

class Staff extends Employee {
  protected String title;

  Staff(String name) {
    super(name);
  }
  
  @Override
  public String toString() {
    return "Staff: " + getName();
  }
}
