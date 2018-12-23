/**
 *  Instructions on how to run this exercise:
 *  1. Step RMI registry from the class directory for this exercise
 *     C:\exercise>start rmiregistry
 *  2. Register Server with RMIRegistry
 *     C:\exercise>start java Exercise40_07RemoteInterfaceImpl
 *  3. Run a client (multiple times)
 *     C:\exercise>start java Exercise40_07Client
 */

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.*;

public class Exercise40_07RemoteInterfaceImpl extends
  UnicastRemoteObject implements Exercise40_07RemoteInterface {

  // Stores callbacks for sending chat to all cients
  private ArrayList callBacks = new ArrayList();

  public Exercise40_07RemoteInterfaceImpl() throws RemoteException {
    super();
  }

  public Exercise40_07RemoteInterfaceImpl(int port) throws
    RemoteException {
    super(port);
  }

  public boolean connect(Exercise40_07CallbackInterface client) throws
    RemoteException {
    callBacks.add(client);
    client.receive("Connected to RMI Chat Server");
    return true;
  }

  public void receive(String message) throws RemoteException {
    // Log chat to the server
    System.out.println(message);

    // Send to all clients
    sendToAll(message);
  }

  // Used to send message to all clients
  void sendToAll(String message) {
    // Go through arraylist and send message to each client
    for (int i = 0; i < callBacks.size(); i++) {
      Exercise40_07CallbackInterface client = (
        Exercise40_07CallbackInterface)callBacks.get(i);
      try {
        // Write message
        client.receive(message);
      }
      catch (ConnectException ex) {
        // Removes client if there is a ConnectException
        callBacks.remove(client);
      }
      catch (Exception ex) {
        System.err.println(ex);
      }
    }
  }

  public static void main(String[] args) {
    try {
      Exercise40_07RemoteInterface server = new
        Exercise40_07RemoteInterfaceImpl();
      Registry registry = LocateRegistry.getRegistry();
      registry.rebind("ChatServer", server);
      System.out.println("ChatServer " + server + " registered");
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}