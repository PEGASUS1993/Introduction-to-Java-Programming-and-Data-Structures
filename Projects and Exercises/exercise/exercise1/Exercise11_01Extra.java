import java.util.ArrayList;
import java.util.Scanner;

public class Exercise11_01Extra {
  public static ArrayList<String> split(String s, String delimiters) {
    ArrayList<String> result = new ArrayList<>();
    String item = "";

    for (int i = 0; i < s.length(); i++) {
      if (!delimiters.contains(s.charAt(i) + "")) {
        item += s.charAt(i);
      } 
      else {
        // s.charAt(i) is a delimiter. Send item to result
        if (item.length() > 0) {
          result.add(item);
          item = "";
        }
      }
    }

    // The last item
    if (item.length() > 0) {
      result.add(item);
      item = "";
    }

    return result;
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String s = input.nextLine();

    System.out.print("Enter delimiters: ");
    String delimiters= input.nextLine();
  
    ArrayList<String> items = split(s, delimiters);
  
    for (int i = 0; i < items.size(); i++)
      System.out.print(items.get(i) + " ");
  }
}
