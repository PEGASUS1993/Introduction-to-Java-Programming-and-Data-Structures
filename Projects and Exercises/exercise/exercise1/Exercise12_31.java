import java.util.*;
import java.io.*;

public class Exercise12_31 { 
  private static String[][] boyNames = new String[10][1000];
  private static String[][] girlNames = new String[10][1000];

  private static void readNames() {
    for (int i = 0; i < 10; i++) {
      String filename;
      if (i == 9)
        filename = "babynamesranking2010.txt";
      else
        filename = "babynamesranking200" + (i + 1) + ".txt";
      
      try (Scanner input = new Scanner(new File(filename))) {        
        int j = 0;
        while (input.hasNext()) {
          input.nextInt(); // Skip the ranking
          boyNames[i][j] = input.next();
          input.nextInt(); // Skip the number of boy's name
          girlNames[i][j] = input.next();
          input.nextInt(); // Skip the number of girl's name
          j++;
        }
      }     
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
  
  /** Main method */
  public static void main(String[] args) {
    readNames();
    
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the year: ");
    int year = Integer.parseInt(input.nextLine());
    System.out.print("Enter the gender: ");
    char gender = input.nextLine().charAt(0);
    System.out.print("Enter the name: ");
    String name = input.nextLine();
    
    if (gender == 'M') {
      int index = search(boyNames, year, name);
      if (index >= 0)
        System.out.println("Boy name " + name + " is ranked #" + 
            (index + 1) + " in year " + year);
      else
        System.out.println("Boy name " + name 
            + " is not ranked in year " + year);
    }
    else {
      int index = search(girlNames, year, name);
      if (index >= 0)
        System.out.println("Girl name " + name + " is ranked #" + 
            (index + 1) + " in year " + year);
      else
        System.out.println("Girl name " + name 
            + " is not ranked in year " + year);
    }
  }
  
  public static int search(String[][] names, int year, String name) {
    for (int i = 0; i < 1000; i++)
      if (names[year - 2001][i].equals(name))
        return i;
    
    return -1;
  }
}

/* Read data from the Web
import java.util.*;
import java.net.URL;

public class Exercise12_31 { 
  private static String[][] boyNames = new String[10][1000];
  private static String[][] girlNames = new String[10][1000];

  private static void readNames() {
    Scanner input = null;
    for (int i = 0; i < 10; i++) {
      String filename;
      if (i == 9)
        filename = "babynamesranking2010.txt";
      else
        filename = "babynamesranking200" + (i + 1) + ".txt";
      
      filename = "http://liveexample.pearsoncmg.com/data/" + filename;
      
      try {
        URL url = new URL(filename);
        input = new Scanner(url.openStream());    
        int j = 0;
        while (input.hasNext()) {
          input.nextInt(); // Skip the ranking
          boyNames[i][j] = input.next();
          input.nextInt(); // Skip the number of boy's name
          girlNames[i][j] = input.next();
          input.nextInt(); // Skip the number of girl's name
          j++;
        }
      }     
      catch (Exception ex) {
        ex.printStackTrace();
      }
      finally {
        input.close();
      }
    }
  }
  
  public static void main(String[] args) {
    readNames();
    
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the year: ");
    int year = Integer.parseInt(input.nextLine());
    System.out.print("Enter the gender: ");
    char gender = input.nextLine().charAt(0);
    System.out.print("Enter the name: ");
    String name = input.nextLine();
    
    if (gender == 'M') {
      int index = search(boyNames, year, name);
      if (index >= 0)
        System.out.println("Boy name " + name + " is ranked #" + 
            (index + 1) + " in year " + year);
      else
        System.out.println("Boy name " + name 
            + " is not ranked in year " + year);
    }
    else {
      int index = search(girlNames, year, name);
      if (index >= 0)
        System.out.println("Girl name " + name + " is ranked #" + 
            (index + 1) + " in year " + year);
      else
        System.out.println("Girl name " + name 
            + " is not ranked in year " + year);
    }
  }
  
  public static int search(String[][] names, int year, String name) {
    for (int i = 0; i < 1000; i++)
      if (names[year - 2001][i].equals(name))
        return i;
    
    return -1;
  }
}
*/