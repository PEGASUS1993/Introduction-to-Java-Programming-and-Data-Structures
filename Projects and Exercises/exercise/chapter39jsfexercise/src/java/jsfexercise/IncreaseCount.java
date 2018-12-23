package jsfexercise;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Named(value = "increaseCount")
@SessionScoped
public class IncreaseCount implements java.io.Serializable {
  @Inject private Track track;
  private String ipAddress;

  public IncreaseCount() {
    HttpServletRequest request = (HttpServletRequest)FacesContext
      .getCurrentInstance().getExternalContext().getRequest();
    this.ipAddress = request.getRemoteAddr(); 
  }
  
  public void click() {
    track.add(ipAddress);
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public int getCount() {
    return track.getCount(ipAddress);
  }
}
