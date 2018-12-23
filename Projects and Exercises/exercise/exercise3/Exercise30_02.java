import java.util.*;
import java.util.stream.Collectors;

public class Exercise30_02 {
  public static void main (String[] args) {
    Scanner input = new Scanner(System.in);
    ArrayList<Integer> list = new ArrayList<>();
    
    System.out.print("Enter the integers between 1 and 100: ");
    
    // Read all numbers
    int number = input.nextInt(); // number read from a file
    while (number != 0) {
      if (number <= 100 && number >= 0)
        list.add(number); 
      number = input.nextInt(); 
    }

    list.stream().collect(Collectors.groupingBy(e -> e, 
      TreeMap::new, Collectors.counting())).forEach((k, v) ->
        System.out.println(k + " occurs " + v + (v == 1 ? " time " : " times")));
  }
}
