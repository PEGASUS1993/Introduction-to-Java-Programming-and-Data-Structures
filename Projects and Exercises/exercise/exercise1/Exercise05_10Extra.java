import java.util.Scanner;

public class Exercise05_10Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String s = input.nextLine();
    
    int maxOccurrenceCount = 1;
    char maxOccurrenceChar = s.charAt(0);
    int currentOccurrenceCount = 1;
    char currentCh = s.charAt(0);
    for (int i = 0; i < s.length() - 1; i++) {
      if (currentCh == s.charAt(i + 1))
        currentOccurrenceCount++;
      
      if (currentCh != s.charAt(i + 1) || 
          (i == s.length() - 2 && currentCh == s.charAt(i + 1))) 
      {
        if (currentOccurrenceCount > maxOccurrenceCount) {
          maxOccurrenceCount = currentOccurrenceCount;
          maxOccurrenceChar = currentCh;
        }
        currentCh = s.charAt(i + 1);
        currentOccurrenceCount = 1;
      }
    }

    System.out.print("The first longest consecutive repeating character substring is ");
    for (int i = 0; i < maxOccurrenceCount; i++)
      System.out.print(maxOccurrenceChar);
  }
}