import java.util.*;
import java.util.stream.Stream;

public class Exercise30_10 {
  public static void main(String[] args) {
    System.out.print("Enter a binary number: ");
    Scanner input = new Scanner(System.in);
    String binary = input.nextLine();
    
    System.out.println(Stream.of(toCharacterArray(binary.toCharArray())).mapToInt(ch -> (int)(ch - '0')).reduce(0, (e1, e2) -> e1 * 2 + e2));
  }
  
  public static Character[] toCharacterArray(char[] list) {
    Character[] result = new Character[list.length];
    for (int i = 0; i < result.length; i++) {
      result[i] = list[i];
    }
    return result;
  }
}
