import java.util.ArrayList;

public class Exercise11_18 {
  public static void main(String[] args) {
    for (Character ch: toCharacterArray("Welcome")) {
      System.out.println(ch);
    }
  }
  
  public static ArrayList<Character> toCharacterArray(String s) {
    ArrayList<Character> list = new ArrayList<>();
    for (int i = 0; i < s.length(); i++) {
      list.add(s.charAt(i));
    }
    return list;
  }
}