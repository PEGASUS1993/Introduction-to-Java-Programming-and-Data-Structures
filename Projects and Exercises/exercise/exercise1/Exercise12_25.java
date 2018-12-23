import java.util.Scanner;

public class Exercise12_25 {
  public static void main(String[] args) throws Exception {   
    java.net.URL url = 
      new java.net.URL("http://cs.armstrong.edu/liang/data/Salary.txt"); 
    
    try (Scanner input = new Scanner(url.openStream())) {
      int countAssistant = 0;
      int countAssociate = 0;
      int countFull = 0;
      int count = 0;
      double totalAssistant = 0;
      double totalAssociate = 0;
      double totalFull = 0;
      double total = 0;
      while (input.hasNext()) {
        String line = input.nextLine();
        String[] items = line.split(" ");
        
        if (items[2].equals("assistant")) {
          countAssistant++;
          totalAssistant += Double.parseDouble(items[3]);
        }
        else if (items[2].equals("associate")) {
          countAssociate++;
          totalAssociate += Double.parseDouble(items[3]);
        }
        else if (items[2].equals("full")) {
          countFull++;
          totalFull += Double.parseDouble(items[3]);
        }
        
        count++;
        total += Double.parseDouble(items[3]);     
      }
      
      System.out.printf("Total salary for assistant professor is %.2f\n", totalAssistant);
      System.out.printf("Total salary for associate professor is %.2f\n", totalAssociate);
      System.out.printf("Total salary for full professor is %.2f\n", totalFull);
      System.out.printf("Total salary for all professors is %.2f\n", total);
      System.out.printf("Average salary for assistant professor is %.2f\n", totalAssistant / countAssistant);
      System.out.printf("Average salary for associate professor is %.2f\n", totalAssociate / countAssociate);
      System.out.printf("Average salary for full professor is %.2f\n", totalFull / countFull);
      System.out.printf("Average salary for all professors is %.2f\n", total / count);
    }
  }
}
