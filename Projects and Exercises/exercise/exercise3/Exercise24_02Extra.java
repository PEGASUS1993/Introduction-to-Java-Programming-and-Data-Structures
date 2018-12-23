import java.util.*;

public class Exercise24_02Extra {
  public static void main(String[] args) {
    // Create a list
    List<String> list = new LinkedList<>();

    // Add elements to the list
    list.add("America"); // Add it to the list
    System.out.println("(1) " + list);

    list.add(0, "Canada"); // Add it to the beginning of the list
    System.out.println("(2) " + list);

    list.add("Russia"); // Add it to the end of the list
    System.out.println("(3) " + list);

    list.add("France"); // Add it to the end of the list
    System.out.println("(4) " + list);

    list.add(2, "Germany"); // Add it to the list at index 2
    System.out.println("(5) " + list);

    list.add(5, "Norway"); // Add it to the list at index 5
    System.out.println("(6) " + list);

    // Remove elements from the list
    list.remove("Canada"); // Same as list.remove(0) in this case
    System.out.println("(7) " + list);

    list.remove(2); // Remove the element at index 2
    System.out.println("(8) " + list);

    list.remove(list.size() - 1); // Remove the last element
    System.out.print("(9) " + list + "\n(10) ");

    for (String s: list)
      System.out.print(s.toUpperCase() + " ");
    
    System.out.print("\n(11) ");
    list.forEach(e -> System.out.print(e.toLowerCase() + " "));
    
    System.out.print("\n(12) ");
    list.stream().sorted().forEach(e -> System.out.print(e + " "));
    
    list.clear();
    System.out.println("\n(13) The list size is " + list.size());
  }
}
