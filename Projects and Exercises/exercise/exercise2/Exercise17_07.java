import java.io.*;

public class Exercise17_07 {
  public static void main(String[] args) {
    int total = 0;
    int count = 0;

    try (
      ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(
        new FileInputStream("Exercise17_06.dat")));
    ) {
      while (true) {
        Loan loan = (Loan)(input.readObject());
        total += loan.getLoanAmount();
        count++;
      }
    }
    catch (EOFException ex) {
      System.out.println("Number of loan objects is " + count);
      System.out.println("Total loan amount is " + total);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
