import java.util.Scanner;
import java.io.*;

public class Exercise12_17 {
  public static void main(String[] args) throws Exception {       
    // Find out how many words are in the file
    Scanner fileInput = new Scanner(new File("hangman.txt"));
    int count = 0;
    while (fileInput.hasNext()) {
      fileInput.next();
      count++;
    }
    fileInput.close();

    String[] words = new String[count];
    count = 0;
    // Read the words from the file
    fileInput = new Scanner(new File("hangman.txt"));
    while (fileInput.hasNext()) {
      words[count++] = fileInput.next();
    }
    
    char anotherGame;

    Scanner input = new Scanner(System.in);

    do {
      int index = (int) (Math.random() * words.length);

      String hiddenWord = words[index];
      StringBuilder guessedWord = new StringBuilder();

      for (int i = 0; i < hiddenWord.length(); i++)
        guessedWord.append('*');

      int numberOfCorrectLettersGuessed = 0, numberOfMisses = 0;

      while (numberOfCorrectLettersGuessed < hiddenWord.length()) {
        System.out.print("(Guess) Enter a letter in word " + guessedWord
            + " > ");
        String s = input.nextLine();
        char letter = s.charAt(0);

        if (guessedWord.indexOf(letter + "") >= 0) {
          System.out.println("\t" + letter + " is already in the word");
        } else if (hiddenWord.indexOf(letter) < 0) {
          System.out.println("\t" + letter + " is not in the word");
          numberOfMisses++;
        } else {
          int k = hiddenWord.indexOf(letter);
          while (k >= 0) {
            guessedWord.setCharAt(k, letter);
            numberOfCorrectLettersGuessed++;
            k = hiddenWord.indexOf(letter, k + 1);
          }
        }
      }

      System.out.println("The word is " + hiddenWord + ". You missed "
          + numberOfMisses + ((numberOfMisses <= 1) ? " time" : " times"));

      System.out.print("Do you want to guess for another word? Enter y or n> ");
      anotherGame = input.nextLine().charAt(0);
    } while (anotherGame == 'y');
  }
}
