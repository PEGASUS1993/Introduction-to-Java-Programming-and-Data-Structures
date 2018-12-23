public class Exercise07_27 {
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
      System.out.println("Two lists are identical");
    }
    else {
      System.out.println("Two lists are not identical");
    }
  }
  
  public static boolean equals(int[] list1, int[] list2) {   
    // Hint: (1) first check if the two have the same size. 
    // (2) Sort list1 and list2 using the sort method.
    // (3) Compare the corresponding elements from list1 and list2. 
    //    return false, if not match. Return true if all matches. 

    if (list1.length != list2.length)
      return false;
    
    java.util.Arrays.sort(list1);
    java.util.Arrays.sort(list2);
    for (int i = 0; i < list1.length; i++) 
      if (list1[i] != list2[i])
        return false;
    
    return true;
  }
  
  public static boolean equalsAlterntive(int[] list1, int[] list2) {   

    java.util.Arrays.sort(list1);
    java.util.Arrays.sort(list2);
        
    return java.util.Arrays.equals(list1, list2);
  }
}
