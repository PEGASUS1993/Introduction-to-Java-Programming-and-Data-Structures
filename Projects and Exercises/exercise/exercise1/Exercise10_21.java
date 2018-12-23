import java.math.*;

public class Exercise10_21 {
  public static void main(String[] args) {
    // bigNum represents Long.MAX_VALUE
    BigInteger bigNum = new BigInteger(Long.MAX_VALUE + "");
    // bigNum is now larger than Long.MAX_VALUE
    bigNum = bigNum.add(BigInteger.ONE);

    int count = 1;
    while (count <= 10) {
      if (bigNum.remainder(new BigInteger("5")).equals(BigInteger.ZERO)
          || bigNum.remainder(new BigInteger("6")).equals(BigInteger.ZERO)) {
        System.out.println(bigNum);
        count++;
      }

      bigNum = bigNum.add(BigInteger.ONE);
    }
  }
}
