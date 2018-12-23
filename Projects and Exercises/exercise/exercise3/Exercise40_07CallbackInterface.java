import java.rmi.*;

public interface Exercise40_07CallbackInterface extends Remote {
    // Server sends messages to the clients
    public void receive(java.lang.String message) throws RemoteException;
}
