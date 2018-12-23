import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

public class Exercise30_17 {
  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.out.println(
        "Usage: java Exercise30_17 fullfilename");
      System.exit(1);
    }

    String filename = args[0];

    HashSet<Character> consonantSet = new HashSet<>(Arrays.asList(
        new Character[]{'A', 'E', 'I', 'O', 'U'}));
    
    long countLetters = Files.lines(new File(filename).toPath()).map(line -> 
      Stream.of(Exercise30_10.toCharacterArray(line.toCharArray())))
      .reduce((e1, e2) -> Stream.concat(e1, e2)).get().filter(e -> Character.isLetter(e)).count();
    
    long countConsonants = countLetters - Files.lines(new File(filename).toPath()).map(line -> 
    Stream.of(Exercise30_10.toCharacterArray(line.toCharArray())))
    .reduce((e1, e2) -> Stream.concat(e1, e2)).get().filter(e -> consonantSet.contains(e)).count();
    
    long countVowels = countLetters - countConsonants ;
    System.out.println("The number of vowels is " + countVowels + " and consonanats is " +
        countConsonants);
  }
}
