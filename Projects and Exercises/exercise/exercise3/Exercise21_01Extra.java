import java.util.*;

public class Exercise21_01Extra {
  public static void main(String[] args) {
    System.out.print("Enter a string: ");
    Scanner input = new Scanner(System.in);
    String s = input.nextLine();
    
    Character[] list1 = {'A', 'E', 'I', 'O', 'U'};
    
    Set<Character> vowels = new HashSet<>(Arrays.asList(list1));
    Set<Character> vowelsInString = new HashSet<>();
    Set<Character> consonantsInString = new HashSet<>();
    
    int numbeOfVowels = 0;
    int numbeOfConsonants = 0;
    
    for (int i = 0; i < s.length(); i++) {
      char ch = Character.toUpperCase(s.charAt(i));      
      if (Character.isLetter(ch)) {
        if (vowels.contains(ch)) {
          if (!vowelsInString.contains(ch)) {
            vowelsInString.add(ch);
            numbeOfVowels++;
          }
        }
        else if (!consonantsInString.contains(ch)) {
          consonantsInString.add(ch);
          numbeOfConsonants++;
        }
      }
    }

    System.out.println("The number of vowels is " + numbeOfVowels); 
    System.out.println("The number of consonants is " + numbeOfConsonants);
  }
}