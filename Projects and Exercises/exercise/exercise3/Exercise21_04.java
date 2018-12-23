import java.util.Scanner;
import java.util.HashSet;
import java.util.Arrays;

public class Exercise21_04 {
  public static void main(String[] args) throws Exception {
    HashSet<Character> set1 = new HashSet<>(Arrays.asList(
      new Character[]{'A', 'E', 'I', 'O', 'U'}));

    System.out.print("Enter a filename: ");
    Scanner input = new Scanner(System.in);
    String filename = input.nextLine();
    input = new Scanner(new java.io.File(filename));
    
    int countVowels = 0;
    int countConsonants = 0;
    
    while (input.hasNext()) {
      String s = input.nextLine().toUpperCase();
      for (int i = 0; i < s.length(); i++) {
        if (set1.contains(s.charAt(i))) 
          countVowels++;  
        else if (Character.isLetter(s.charAt(i))) 
          countConsonants++;
      }
    }
    
    System.out.println("The number of vowels is " + countVowels + " and consonanats is " +
      countConsonants);
  }
}
