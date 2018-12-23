package jsfexercise;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Fix
 */
@ManagedBean
@RequestScoped
public class Exercise39_07 {

  private int ages[] = {1, 2, 4, 8, 16};
  private Boolean truth[] = {false, false, false, false, false};
  private Integer answer = 0;

  public Exercise39_07() {
  }

  public void setTruth(Boolean[] truth) {
    this.truth = truth;
  }

  public Boolean[] getTruth() {
    return truth;
  }

  public void setAnswer(Integer answer) {
    this.answer = answer;
  }

  public Integer getAnswer() {
    if (answer == 0) {
      return null;
    } else {
      return answer;
    }
  }

  public void guessBDay() {
    answer = 0;
    for (int x = 0; x < 5; x++) {
      if (truth[x]) {
        answer = ages[x] + answer;
      }
    }
  }
}
