package jsfexercise;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Exercise39_05 {
  private final int SIZE = 10;
  private int[] first = new int[SIZE];
  private int[] second = new int[SIZE];
  private String[] useranswer = new String[SIZE];
  private String[] eval = new String[SIZE];
  private int count = 0;

  public Exercise39_05() {
    generateNumbers();
  }
  
  public void generateNumbers() {
    for (int i = 0; i < SIZE; i++) {
      first[i] = (int)(Math.random() * 20) + 10;
      second[i] = (int)(Math.random() * 10);
      useranswer[i] = "";
    }
  }

  public String getQuestion(Long i) {
    return first[i.intValue()] + " + " + second[i.intValue()] + " = ";
  }

  public String getEvaluation() {
    count = 0;
    for (int i = 0; i < SIZE; i++) {
      if (useranswer[i].trim().length() == 0)
        eval[i] = "You did not give an answer";
      else if (first[i] + second[i] == Integer.parseInt(useranswer[i])) {
        eval[i] =  "Correct";
        count++;
      } else {
        eval[i] = "Wrong";
      }
    }

    return "Exercise39_05a";
  }

  public String getResult(Long i) {
    return first[i.intValue()] + " + " + second[i.intValue()] + " = " + useranswer[i.intValue()] + "\t" + eval[i.intValue()];
  }

  public String getCount() {
    return "There are  " + count + " correct guesses";
  }

  public String[] getUseranswer() {
    return useranswer;
  }

  public void setUseranswer(String[] value) {
    useranswer = value;
  }
}
