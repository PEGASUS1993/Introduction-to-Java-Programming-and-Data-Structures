import java.util.Scanner;

public class Exercise07_33 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Enter a year: ");
    int year = input.nextInt();

    if (year >= 1900) 
      year -= 1900;
    else
      year = 1900 - year;
    
    String[] animals = {"rat", "ox", "tiger", "rabbit", "dragon", 
      "snake", "horse", "sheep", "monkey", "rooster", "dog", "pig"};

    System.out.println(animals[year % 12]);
  }
}
