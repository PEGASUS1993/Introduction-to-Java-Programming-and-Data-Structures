import java.util.Scanner;

public class Exercise18_22 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a decimal integer: ");
    int decimal = input.nextInt();
    System.out.println(decimal + " is hex " + dec2Hex(decimal));
  }  

  public static String dec2Hex(int value) {
    if (value == 0)
      return "";
    else {
      return dec2Hex(value / 16) + dec2HexChar(value % 16);   
    }
  }
  
  public static char dec2HexChar(int value) {
    if (value >= 10 && value <=15)
      return (char)('A' + value - 10);
    else 
      return (char)('0' + value);
  }
}
