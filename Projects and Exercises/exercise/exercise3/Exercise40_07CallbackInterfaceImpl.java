import java.io.*;
import java.rmi.*;
import java.rmi.server.*;

public class Exercise40_07CallbackInterfaceImpl extends
     UnicastRemoteObject implements Exercise40_07CallbackInterface {
  // Client to be called by the server through callback
  private Exercise40_07Client thisClient;

  public Exercise40_07CallbackInterfaceImpl(Object client) throws RemoteException {
    thisClient = (Exercise40_07Client)client;
  }

  public void receive(String message) throws RemoteException {
    thisClient.getMessage(message);
  }
}
