public class Exercise05_11 {
  public static void main(String[] args) {
    int count = 1;
    
    int i = 100;
    while (i <= 200) {
      if (i % 5 == 0 ^ i % 6 == 0) {
        System.out.print((count % 10 != 0) ? i + " " : i + "\n");
        count++;
      }
     
      i++;
    }

    /* for loop 
    for (int i = 100; i <= 200; i++)
      if (i % 5 == 0 ^ i % 6 == 0)
        System.out.print((count++ % 10 != 0) ? i + " ": i + "\n");
    */
  }
}
