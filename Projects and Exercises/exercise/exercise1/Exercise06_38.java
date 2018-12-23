public class Exercise06_38 {
  public static void main(String[] args) {
    for (int i = 1; i <= 100; i++) {
      if (i % 10 == 0)
        System.out.println(RandomCharacter.getRandomCharacter('A', 'Z'));
      else
        System.out.print(RandomCharacter.getRandomCharacter('A', 'Z'));
    }

    for (int i = 1; i <= 100; i++) {
      if (i % 10 == 0)
        System.out.println(RandomCharacter.getRandomCharacter('0', '9'));
      else
        System.out.print(RandomCharacter.getRandomCharacter('0', '9'));
    }
  }
}
