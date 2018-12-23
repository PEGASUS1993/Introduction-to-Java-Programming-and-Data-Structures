import java.math.BigDecimal;

public class Exercise10_20 {
  public static void main(String[] args) {
    BigDecimal e = BigDecimal.ONE; // 1
    BigDecimal item = BigDecimal.ONE; // 1

    for (int i = 1; i <= 1000; i++) {
      item = item.divide(new BigDecimal(i + ""), 25, BigDecimal.ROUND_UP);
      e = e.add(item);
      
      if (i % 100 == 0)
        System.out.println("The e is " + e + " for i = " + i);
    }
  }
}
