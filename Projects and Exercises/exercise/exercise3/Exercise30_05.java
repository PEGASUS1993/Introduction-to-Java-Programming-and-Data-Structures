import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Exercise30_05 {
  public static void main(String[] args) {
    int[] value = new int[100];
    
    for (int i = 0; i < 100; i++) {
      value[i] = (int)(Math.random() * 10);
    }
    
    IntStream.of(value).mapToObj(e -> "" + e).collect(Collectors.groupingBy(e -> e, 
      TreeMap::new, Collectors.counting())).forEach((k, v) ->
      System.out.println(k + " occurs " + v + (v == 1 ? " time " : " times")));;
  }
}
