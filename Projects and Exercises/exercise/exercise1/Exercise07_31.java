public class Exercise07_31 {
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
    
    int[] list3 = merge(list1, list2);
    
    System.out.print("list1 is ");
    for (int i = 0; i < list1.length; i++) 
      System.out.print(list1[i] + " ");
    System.out.println();
    System.out.print("list2 is ");
    for (int i = 0; i < list2.length; i++) 
      System.out.print(list2[i] + " ");
    System.out.println();
    System.out.print("The merged list is ");
    for (int i = 0; i < list3.length; i++) 
      System.out.print(list3[i] + " ");
    System.out.println();
  }
  
  public static int[] merge(int[] list1, int[] list2) {   
    int[] result = new int[list1.length + list2.length];

    int current1 = 0; // Current index in list1
    int current2 = 0; // Current index in list2
    int current3 = 0; // Current index in result

    while (current1 < list1.length && current2 < list2.length) {
      if (list1[current1] < list2[current2])
        result[current3++] = list1[current1++];
      else
        result[current3++] = list2[current2++];
    }

    while (current1 < list1.length)
      result[current3++] = list1[current1++];

    while (current2 < list2.length)
      result[current3++] = list2[current2++];

    return result;
  }
}

