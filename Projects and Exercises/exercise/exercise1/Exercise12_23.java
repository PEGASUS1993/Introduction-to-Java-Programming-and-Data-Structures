 import java.util.*;

public class Exercise12_23 {
  public static void main(String[] args) throws Exception {   
    java.net.URL url = 
      new java.net.URL("http://cs.armstrong.edu/liang/data/Scores.txt"); 

    try (Scanner input = new Scanner(url.openStream())) {  
      double sum = 0;
      int count = 0;
      while (input.hasNext()) {
        sum += input.nextDouble();
        count++;
      }
  
      System.out.println("Total is " + sum);
      System.out.println("Average is " + sum / count);
    }
  }
}
