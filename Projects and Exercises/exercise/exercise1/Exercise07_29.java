public class Exercise07_29 {
  public static void main(String[] args) {
    int[] deck = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    
    int count = 0;
    for (int i1 = 0; i1 < deck.length; i1++) 
      for (int i2 = i1 + 1; i2 < deck.length; i2++) 
        for (int i3 = i2 + 1; i3 < deck.length; i3++) 
          for (int i4 = i3 + 1; i4 < deck.length; i4++) {
            int a = deck[i1];
            int b = deck[i2];
            int c = deck[i3];
            int d = deck[i4];

            if (a + b + c + d == 24)
              count++;
          }
    
    System.out.println("The number of picks that yields the sum of 24 is "
      + count);
  }
}
