public class Exercise20_08 {
  public static void main(String[] args) {
    // Generate a lottery
    int lottery = (int)(Math.random() * 1000);

    // Prompt the user to enter a guess
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter your lottery pick (three digits): ");
    int guess = input.nextInt();

    System.out.println("The lottery number is " + lottery);
    
    // Get digits
    int lottery1 = lottery / 100;
    int lottery2 = (lottery % 100) / 10;
    int lottery3 = lottery % 10;

    int guess1 = guess / 100;
    int guess2 = (guess % 100) / 10;
    int guess3 = guess % 10;

    java.util.List<Integer> list1 = new java.util.ArrayList<Integer>();
    list1.add(lottery1);
    list1.add(lottery2);
    list1.add(lottery3);
    java.util.Collections.sort(list1);

    java.util.List<Integer> list2 = new java.util.ArrayList<Integer>();
    list2.add(guess1);
    list2.add(guess2);
    list2.add(guess3);
    java.util.Collections.sort(list2);

    // Check the guess
    if (guess  == lottery)
      System.out.println("Exact match: you win $10,000");
    else if (list1.equals(list2))
      System.out.println("Match all digits: you win $3,000");
    else if (list1.containsAll(list2.subList(0, 2)) ||
      list1.containsAll(list2.subList(1, 3)))
      // Check if two digits in the user input are in the lottery
      System.out.println("Match one digit: you win $2,000");
    else if (list1.contains(guess1) || list1.contains(guess2) || 
             list1.contains(guess3))
      System.out.println("Match one digit: you win $1,000");
    else
      System.out.println("Sorry, no match");
  }
}
