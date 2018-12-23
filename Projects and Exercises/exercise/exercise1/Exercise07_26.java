
public class Exercise07_26 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Enter values for list1
    System.out.print("Enter list1 size and contents: ");
    int size1 = input.nextInt();
    int[] list1 = new int[size1];
    
    for (int i = 0; i < list1.length; i++) 
      list1[i] = input.nextInt();
    
    // Enter values for list2
    System.out.print("Enter list2 size and contents: ");
    int size2 = input.nextInt();
    int[] list2 = new int[size2];
    
    for (int i = 0; i < list2.length; i++) 
      list2[i] = input.nextInt();
    
    if (equals(list1, list2)) {
      System.out.println("Two lists are strictly identical");
    }
    else {
      System.out.println("Two lists are not strictly identical");
    }
  }
  
  public static boolean equals(int[] list1, int[] list2) {
    if (list1.length != list2.length)
      return false;
    
    for (int i = 0; i < list1.length; i++) 
      if (list1[i] != list2[i])
        return false;
    
    return true;
  }
}
