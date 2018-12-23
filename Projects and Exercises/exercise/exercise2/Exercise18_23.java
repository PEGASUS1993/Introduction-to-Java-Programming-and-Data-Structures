import java.util.Scanner;

public class Exercise18_23 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a binary number: ");
    String binary = input.nextLine();
    System.out.println(binary + " is decimal " + bin2Dec(binary));
  }  

  public static int bin2Dec(String binaryString) {
    return bin2Dec(binaryString, 0, binaryString.length() - 1);
  }
  
  public static int bin2Dec(String binaryString, int low, int high) {
    if (high < low)
      return 0;
    else 
      return bin2Dec(binaryString, low, high - 1) * 2
        + (binaryString.charAt(high) - '0');
  }
}
