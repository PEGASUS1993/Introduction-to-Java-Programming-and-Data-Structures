package jsfexercise;

import java.util.ArrayList;
import java.util.Collections;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Exercise39_10 {
  private ArrayList<Integer> deck = new ArrayList<Integer>();
  
  public Exercise39_10() {
    for (int i = 0; i < 52; i++) 
      deck.add(i);
    Collections.shuffle(deck);
  } 
  
  public String getImageName1() {
    return (deck.get(0) + 1) + ".png";
  }
  
  public String getImageName2() {
    return (deck.get(1) + 1) + ".png";
  }

  public String getImageName3() {
    return (deck.get(2) + 1) + ".png";
  }

  public String getImageName4() {
    return (deck.get(3) + 1) + ".png";
  }
  
  public void refresh() {
    Collections.shuffle(deck); 
  }
}
