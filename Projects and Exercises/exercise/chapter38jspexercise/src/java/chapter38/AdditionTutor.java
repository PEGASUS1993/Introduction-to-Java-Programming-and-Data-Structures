package chapter38;

public class AdditionTutor {
  public int number1[] = new int[10];
  public int number2[] = new int[10];
  
  public AdditionTutor() {
    refresh();
  }
  
  public void refresh() {   
    for (int i = 0; i < 10; i++) {
      number1[i] = (int)(Math.random() * 10 + 20);
      number2[i] = (int)(Math.random() * 10 + 3);
    }
  }
}
