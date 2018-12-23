import java.util.*;

public class Exercise11_06 {
  public static void main(String[] args) {
    ArrayList<Object> list = new ArrayList<>();
    list.add(new Loan());
    list.add("ABC");
    list.add(new Date());

    for (int i = 0; i < list.size(); i++)
      System.out.println(list.get(i));
  }
}
