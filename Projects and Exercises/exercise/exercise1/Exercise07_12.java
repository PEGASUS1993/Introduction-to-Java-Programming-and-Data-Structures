import java.util.Scanner;

public class Exercise07_12 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Enter 10 numbers: ");
    double[] myList = new double[10];
    for (int i = 0; i < myList.length; i++)
      myList[i] = input.nextDouble();

    reverse(myList);

    System.out.println("The reversal of the input is");
    for (int i = 0; i < myList.length; i++)
      System.out.print(myList[i] + " ");
  }

  public static double[] reverse(double[] list) {
    for (int i = 0, j = list.length - 1; i < list.length / 2; i++, j--) {
      double temp = list[i];
      list[i] = list[j];
      list[j] = temp;
    }

    return list;
  }
}
