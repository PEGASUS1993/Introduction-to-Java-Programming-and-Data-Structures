import java.util.Scanner;

public class Exercise04_24 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the first city: ");
    String s1 = input.nextLine();    
    System.out.print("Enter the second city: ");
    String s2 = input.nextLine(); 
    System.out.print("Enter the third city: ");
    String s3 = input.nextLine(); 
    
    if (s1.compareTo(s2) > 0) {
      // Swap s1 with s2
      String temp = s1;
      s1 = s2;
      s2 = temp;
    }
  
    if (s2.compareTo(s3) > 0) {
      // Swap s2 with s3
      String temp = s2;
      s2 = s3;
      s3 = temp;
    }  
  
    if (s1.compareTo(s2) > 0) {
      // Swap s1 with s2
      String temp = s1;
      s1 = s2;
      s2 = temp;
    }
  
    System.out.print("The three cities in alphabetical order are " 
      + s1 + " " + s2 + " " + s3);
  }
}