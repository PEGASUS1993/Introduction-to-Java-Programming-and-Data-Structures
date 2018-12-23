public class Exercise19_06 {
  public static void main(String[] args) {
    Integer[][] numbers = { {1, 2, 3}, {4, 4, 6}
    };
    System.out.println(max(numbers));
  }

  public static<E extends Comparable<E>> E max(E[][] list) {
    E max = list[0][0];

    for (int i = 1; i < list.length; i++) {
      for (int j = 1; j < list[i].length; j++) {
        if (max.compareTo(list[i][j]) < 0) {
          max = list[i][j];
        }
      }
    }

    return max;
  }
}
