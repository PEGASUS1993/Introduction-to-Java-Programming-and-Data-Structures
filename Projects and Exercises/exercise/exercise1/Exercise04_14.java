import java.util.Scanner;

public class Exercise04_14 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a letter grade: ");
    char grade = input.nextLine().charAt(0);

    int value = 0;
    if (Character.toUpperCase(grade) == 'A')
      value = 4;
    else if (Character.toUpperCase(grade) == 'B')
      value = 3;
    else if (Character.toUpperCase(grade) == 'C')
      value = 2;
    else if (Character.toUpperCase(grade) == 'D')
      value = 1;
    else if (Character.toUpperCase(grade) == 'F')
      value = 0;
    else {
      System.out.println(grade + " is an invalid grade");
      System.exit(1);
    }

    System.out.println("The numeric value for grade " + grade 
      + " is " + value);
  }  
}