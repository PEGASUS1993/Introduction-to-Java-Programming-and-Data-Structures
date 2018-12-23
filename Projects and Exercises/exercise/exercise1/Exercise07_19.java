public class Exercise07_19 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Enter values for list1
    System.out.print("Enter the list size of the list: ");
    int size = input.nextInt();
    System.out.print("Enter the contents of the list: ");
    int[] list = new int[size];    
    for (int i = 0; i < list.length; i++) 
      list[i] = input.nextInt();

    System.out.print("The list has " + size + " integers ");
    for (int i = 0; i < list.length; i++)
      System.out.print(list[i] + " ");

    if (isSorted(list))    
      System.out.println("\nThe list is already sorted");
    else
      System.out.println("\nThe list is not sorted");
  }

  public static boolean isSorted(int[] list) {
    for (int i = 0; i < list.length - 1; i++) {
      if (list[i] > list[i + 1]) {
        return false;
      }
    }

    return true;
  }
}
