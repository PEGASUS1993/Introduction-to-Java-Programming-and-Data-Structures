import java.util.Scanner;

public class Exercise04_07Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a decimal number between 0 and 15: ");
    
    int value = input.nextInt();
    
    String s = "";
    
    int temp = value;
    s = temp % 2 + s;
    temp = temp / 2;
    
    s = temp % 2 + s;
    temp = temp / 2;
    
    s = temp % 2 + s;
    temp = temp / 2;
    
    s = temp % 2 + s;
      
    System.out.println("The binary number for " + value + " is " + s);
  }
}
