import java.util.*;

public class Exercise11_04Extra {
  public static void main(String[] args) {
    System.out.print("Enter the arguments: ");
    Scanner input = new Scanner(System.in);
    String line = input.nextLine().trim();
    
    ArrayList<String> arguments = getArguments(line);
//    System.out.println(arguments);
    for (String s: arguments) {
      System.out.println(s);
    }
  }
  
  public static ArrayList<String> getArguments(String line) {
    ArrayList<String> arguments = new ArrayList<>();
    String argument = "";
    boolean isInQuote = false;
    for (int i = 0; i < line.length(); i++) {
      if (line.charAt(i) == ' ' && !isInQuote) {
        if (argument.length() > 0) {
          arguments.add(argument);
          argument = "";
        }
      }
      else if (line.charAt(i) == ' ' && isInQuote) {
        argument += " "; 
      }
      else if (line.charAt(i) == '"' && isInQuote) {
        if (argument.trim().length() > 0) {
          arguments.add(argument.trim());
          argument = "";
          isInQuote = false;
        }
      }
      else if (line.charAt(i) == '"' && !isInQuote) {
        isInQuote = true;
      }
      else {
        argument += line.charAt(i);
      }
    }
    
    // Add the last argument to arguments
    if (argument.trim().length() > 0) {
      arguments.add(argument.trim());
    }
    
    return arguments;
  }
}
