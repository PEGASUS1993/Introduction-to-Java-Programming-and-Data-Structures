import java.util.Scanner;

public class Exercise22_05 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a series of numbers ending with 0: ");
    
    int longestSequenceCount = 0;
    int longestSequenceValue = 0;
    int longestSequenceIndex = 0;
    
    int currentLongestSequenceCount = 1;
    int currentLongestSequenceValue = 0;
    
    int value;
    int index = 0;
    int previous = 0;    
    do {
      value = input.nextInt();

      if (value == previous)
        currentLongestSequenceCount++;
      else if (currentLongestSequenceCount > longestSequenceCount) {
        longestSequenceCount = currentLongestSequenceCount;
        longestSequenceValue = currentLongestSequenceValue;
        longestSequenceIndex = index - currentLongestSequenceCount;
        
        currentLongestSequenceCount = 1; // Reset the count
        currentLongestSequenceValue = value; // Reset the value       
      }
      
      previous = value;
      index++;
    }
    while (value != 0);
    
    System.out.println("The longest same number sequence starts at index " 
      + longestSequenceIndex + " with " + longestSequenceCount 
      + " values of " + longestSequenceValue);
  }
}
