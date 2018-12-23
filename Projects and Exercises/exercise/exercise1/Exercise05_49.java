import java.util.Scanner;

public class Exercise05_49 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String s = input.nextLine();

    int countVowels = 0;
    int countConsonants = 0;
    for (int i = 0; i < s.length(); i++) {
      char temp = Character.toUpperCase(s.charAt(i));
      if (temp == 'A' || temp == 'E' || temp == 'I' || temp == 'O' || temp == 'U')
        countVowels++;
      else if (Character.isLetter(temp))
        countConsonants++;
    }
  
    System.out.println("The number of vowels is " + countVowels);
    System.out.println("The number of consonants is " + countConsonants);
  }
}