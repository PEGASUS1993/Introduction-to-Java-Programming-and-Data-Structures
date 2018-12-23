public class Exercise23_14 {
  public static void main(String[] args) {
    System.out.print("File size");
    int size = 5000000;
    for (int i = 0; i < 5; i++) {
      System.out.print("   " + size);
      size += 5000000;
    }
    System.out.println();
    
    System.out.printf("%-10s%10d%10d%10d%10d%10d\n", "Time", 253, 1251, 4273, 10932, 30932);
  }
}
