import java.rmi.*;

public interface Exercise40_07RemoteInterface extends Remote {
  // Connect clients to the server
  public boolean connect(Exercise40_07CallbackInterface client) throws
    RemoteException;

  // Receive messages from the client
  public void receive(java.lang.String message) throws
    RemoteException;
}
