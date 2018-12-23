import java.util.ArrayList;
import java.util.Scanner;

public class Exercise11_04 {
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<>();

    Scanner input = new Scanner(System.in);   
    System.out.print("Enter integers (input ends with 0): ");
    int value;
    
    do {
      value = input.nextInt(); // Read a value from the input
      
      if (value != 0) 
        list.add(value); // Add the value if it is not in the list
    } while (value != 0);

    // Display the maximum number
    System.out.print("The maximum number is " + max(list));
  }
  
  public static Integer max(ArrayList<Integer> list) {
    if (list == null || list.size() == 0)
      return null;
    
    int result = list.get(0);
    for (int i = 1; i < list.size(); i++)
      if (result < list.get(i))
        result = list.get(i);
    
    return result;
  }
}
