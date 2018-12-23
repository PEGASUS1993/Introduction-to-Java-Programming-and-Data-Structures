import java.util.Scanner;

public class Exercise07_18 {
  public static void main (String[] args) {
    Scanner input = new Scanner(System.in);
    
    double[] myList = new double[10];
    System.out.print("Enter 10 numbers: ");

    for (int i = 0; i < myList.length; i++) {
      myList[i] = input.nextDouble();
    }
    
    bubbleSort(myList);

    //prints the sorted list
    System.out.println("My list after sort is: ");
    printList(myList);
  }

  static void printList(double[] list) {
    for (int i = 0; i < list.length; i++)
      System.out.println(list[i]);
  }

  static void bubbleSort(double[] list) {
    boolean changed = true;
    do {
      changed = false;
      for (int j = 0; j < list.length - 1; j++)
        if (list[j] > list[j+1]) {
          //swap list[j] wiht list[j+1]
          double temp = list[j];
          list[j] = list[j + 1];
          list[j + 1] = temp;
          changed = true;
        }
    } while (changed);
  }
}
