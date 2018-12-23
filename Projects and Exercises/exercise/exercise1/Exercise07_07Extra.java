import java.util.Scanner;

public class Exercise07_07Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter list1: ");
    int n1 = input.nextInt();    
    int[] list1 = new int[n1];
    for (int i = 0; i < list1.length; i++)
      list1[i] = input.nextInt();
    
    System.out.print("Enter list2: ");
    int n2 = input.nextInt();    
    int[] list2 = new int[n2];
    for (int i = 0; i < list2.length; i++)
      list2[i] = input.nextInt();
    
    int[] list = commonElements(list1, list2);
    
    System.out.print("The common elements are ");
    for (int i = 0; i < list.length; i++)
      System.out.print(list[i] + " ");
  }
  
  public static int[] commonElements(int[] list1, int[] list2) {
    int[] temp = new int[Math.min(list1.length, list2.length)];
    
    int j = 0;
    for (int i = 0; i < list2.length; i++)
      if (index(list1, list2[i]) >= 0)
        temp[j++] = list2[i];
    
    int[] result = new int[j];
    System.arraycopy(temp, 0, result, 0, j);
    return result;
  }
  
  public static int index(int[] list, int element) {
    for (int i = 0; i < list.length; i++)
      if (list[i] == element)
        return i;
    
    return -1;
  }  
}
