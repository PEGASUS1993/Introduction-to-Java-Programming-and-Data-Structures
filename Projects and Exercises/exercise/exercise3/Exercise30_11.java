import java.util.*;

public class Exercise30_11 {
  public static void main(String[] args) {
    System.out.print("Enter a hex number: ");
    Scanner input = new Scanner(System.in);
    String binary = input.nextLine();

    char[] temp = binary.toCharArray();
    ArrayList<Character> list = new ArrayList<>();
    for (int i = 0; i < temp.length; i++) {
      list.add(temp[i]);
    }

    System.out.println(list.stream().mapToInt(ch -> {
      if (ch <= '9' && ch >= '0')
        return (int)(ch - '0');
      else
        return (int)(ch - 'A') + 10;
    }).reduce(0, (e1, e2) -> e1 * 16 + e2));
  }
}
