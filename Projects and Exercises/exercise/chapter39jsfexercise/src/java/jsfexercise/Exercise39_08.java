/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfexercise;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.*;

@ManagedBean
@SessionScoped
public class Exercise39_08 {
  private String answer = "";
  private String guess = "";
  private String[][] stateCapitals = {{"Alabama", "Montgomery"},
    {"Alaska", "Juneau"}, {"Arizona", "Phoenix"},
    {"Arkansas", "Little Rock"}, {"California", "Sacramento"},
    {"Colorado", "Denver"}, {"Connecticut", "Hartford"},
    {"Delaware", "Dover"}, {"Florida", "Tallahassee"},
    {"Georgia", "Atlanta"}, {"Hawaii", "Honolulu"}, {"Idaho", "Boise"},
    {"Illinois", "Springfield"}, {"Maryland", "Annapolis"},
    {"Minnesota", "Saint Paul"},
    {"Iowa", "Des Moines"}, {"Maine", "Augusta"},
    {"Kentucky", "Frankfort"}, {"Indiana", "Indianapolis"},
    {"Kansas", "Topeka"}, {"Louisiana", "Baton Rouge"},
    {"Oregon", "Salem"}, {"Oklahoma", "Oklahoma City"},
    {"Ohio", "Columbus"}, {"North Dakota", "Bismark"},
    {"New York", "Albany"}, {"New Mexico", "Santa Fe"},
    {"New Jersey", "Trenton"}, {"New Hampshire", "Concord"},
    {"Nevada", "Carson City"}, {"Nebraska", "Lincoln"},
    {"Montana", "Helena"}, {"Missouri", "Jefferson City"},
    {"Mississippi", "Jackson"}, {"Massachusettes", "Boston"},
    {"Michigan", "Lansing"}, {"Pennslyvania", "Harrisburg"},
    {"Rhode Island", "Providence"}, {"South Carolina", "Columbia"},
    {"South Dakota", "Pierre"}, {"Tennessee", "Nashville"},
    {"Texas", "Austin"}, {"Utah", "Salt Lake City"},
    {"Vermont", "Montpelier"}, {"Virginia", "Richmond"},
    {"Washington", "Olympia"}, {"West Virginia", "Charleston"},
    {"Wisconsin", "Madison"}, {"Wyoming", "Cheyenne"}
  };
  List list = Arrays.asList(stateCapitals);

  public Exercise39_08() {
    Collections.shuffle(list);
  }

  public String getAnswer() {
    evaluateGuess();
    return answer;
  }

  public String getGuess() {
    return guess;
  }

  public void setGuess(String guess) {
    this.guess = guess;
  }

  public String getQuestion() {
    System.out.println("i is " + i);
    return "What is the capital of " + stateCapitals[i][0] + "?";
  }

  //Evaluates the guess, forms the correct answer, and resets
  //the guess
  private void evaluateGuess() {
    answer = "";

    if (guess.toLowerCase().equals(stateCapitals[0][1].toLowerCase())) {
      answer += "Yes. The capital of " + stateCapitals[0][0]
              + " is " + stateCapitals[0][1] + ".";
      guess = "";
    } else {
      answer += "No. The capital of " + stateCapitals[0][0]
              + " is " + stateCapitals[0][1] + ".";
      guess = "";
    }
  }
  int i = 0;

  public String next() {
    if (getGuess().trim().length() == 0) {
      return null;
    } else {
      return "Exercise39_08a";
    }
  }

  public String goToMainPage() {
    i = (i + 1) % stateCapitals.length;
    return "Exercise39_08";
  }
}
