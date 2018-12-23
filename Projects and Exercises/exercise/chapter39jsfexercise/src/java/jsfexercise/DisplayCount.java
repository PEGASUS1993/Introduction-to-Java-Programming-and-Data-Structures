package jsfexercise;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.inject.Inject;

@Named(value = "displayCount")
@ApplicationScoped
public class DisplayCount {
  @Inject private Track track;
  
  public String getAllCount() {
    return track.getAllCount();
  }
}