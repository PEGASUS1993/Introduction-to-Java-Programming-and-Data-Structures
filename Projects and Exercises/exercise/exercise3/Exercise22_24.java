
public class Exercise22_24 {
  public static void main(String[] args) {
    int[] list = {178, 33, 4, 2, -3, 5};
    System.out.println(max(list));
  }
  
  public static int max(int[] list) {
    return max(list, 0, list.length - 1); 
  }

  public static int max(int[] list, int low, int high) {
    if (low == high)
      return list[low];
    else if (low + 1 == high)
      return Math.max(list[low], list[high]);
    else {
      int mid = (low + high) / 2;
      return Math.max(max(list, low, mid - 1), max(list, mid, high));
    }
  }
}
