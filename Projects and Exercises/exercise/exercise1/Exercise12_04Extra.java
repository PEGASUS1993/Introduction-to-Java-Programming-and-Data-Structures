import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Exercise12_04Extra { 
  private static ArrayList<String> boyNames = new ArrayList<String>();
  private static ArrayList<Integer> boyCounts = new ArrayList<Integer>();
  private static ArrayList<String> girlNames = new ArrayList<String>();
  private static ArrayList<Integer> girlCounts = new ArrayList<Integer>();
  
  public static void main(String[] args) throws IOException {
    readNames();
    sort(boyNames, boyCounts);
    displayNames();
  }
  
  private static void readNames() {
    try {
      int j = 0;
      for (int i = 0; i < 10; i++) {
        String filename;
        if (i == 9)
          filename = "Babynamesranking2010.txt";
        else
          filename = "Babynamesranking200" + (i + 1) + ".txt";
        Scanner input = new Scanner(new File(filename));
        
        while (input.hasNext()) {
          input.nextInt(); // Skip the ranking
          String boyName = input.next();
          int boyCount = input.nextInt(); 
          processName(boyNames, boyCounts, boyName, boyCount);
          
          String girlName = input.next();
          int girlCount = input.nextInt(); 
          processName(girlNames, girlCounts, girlName, girlCount);
        }
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  
  private static void processName(ArrayList<String> names, ArrayList<Integer> counts, 
      String name, int count) {
    if (names.contains(name))
      counts.set(names.indexOf(name), counts.get(names.indexOf(name)) + count);
    else {
      names.add(name);
      counts.add(count);
    }
  }
  
  // Sort in decreasing order
  private static void sort(ArrayList<String> names, ArrayList<Integer> counts) {
    for (int i = 0; i < counts.size() - 1; i++) {
      int currentMin = counts.get(i);
      int currentMinIndex = i;

      for (int j = i + 1; j < counts.size(); j++) {
        if (currentMin < counts.get(j)) {
          currentMin = counts.get(j);
          currentMinIndex = j;
        }
      }

      // Swap list[i] with list[currentMinIndex] if necessary;
      if (currentMinIndex != i) {
        counts.set(currentMinIndex, counts.get(i));
        counts.set(i, currentMin);
        
        // Swap the corresponding names as well
        String temp = names.get(currentMinIndex);
        names.set(currentMinIndex, names.get(i));
        names.set(i, temp);        
      }
    }    
  }
  
  private static void displayNames() {
    System.out.println("Boys Ranking, Name, and Count: ");
    for (int i = 0; i < boyNames.size(); i++) {
      System.out.print(i + 1 + " ");
      System.out.print(boyNames.get(i) + " ");
      System.out.println(boyCounts.get(i) + " ");
    }
    
    System.out.println("Girls Ranking, Name, and Count: ");
    for (int i = 0; i < girlNames.size(); i++) {
      System.out.print(i + 1 + " ");
      System.out.print(girlNames.get(i) + " ");
      System.out.println(girlCounts.get(i) + " ");
    }
  }
}