public class Exercise05_15 {
  public static void main(String[] args) {
    int count = 1;
    for (int i = '!'; i <= '~'; i++) {
      System.out.print((count++ % 10 != 0) ? (char)i + " " :
        (char)i + "\n");
    }

/** Alternative solution
    for (char ch = '!'; ch <= '|'; ch++) {
      System.out.print(ch + (count++ % 10 == 0 ? "\n" : " "));  
    }
*/
  }
}
