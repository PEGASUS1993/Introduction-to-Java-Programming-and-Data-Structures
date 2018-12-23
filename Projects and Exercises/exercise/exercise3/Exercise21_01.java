import java.util.*;

public class Exercise21_01 {
  public static void main(String[] args) {
    LinkedHashSet<String> set1 = new LinkedHashSet<>(Arrays.asList(
      new String[]{"George", "Jim", "John", "Blake", "Kevin", "Michael"}));

    LinkedHashSet<String> set1Clone1 = (LinkedHashSet<String>)set1.clone();
    LinkedHashSet<String> set1Clone2 = (LinkedHashSet<String>)set1.clone();

    LinkedHashSet<String> set2 = new LinkedHashSet<String>(Arrays.asList(
      new String[] {"George", "Katie", "Kevin", "Michelle", "Ryan"}));

    set1.addAll(set2);
    set1Clone1.removeAll(set2);
    set1Clone2.retainAll(set2);

    System.out.println("The union of the two sets is " + set1);
    System.out.println("The difference of the two sets is " + set1Clone1);
    System.out.println("The intersection of the two sets is " + set1Clone2);
  }
}
