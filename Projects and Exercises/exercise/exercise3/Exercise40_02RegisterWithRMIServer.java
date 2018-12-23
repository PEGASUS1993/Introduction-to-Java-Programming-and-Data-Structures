/**
 *  Instructions on how to run this exercise:
 *  1. Step RMI registry from the class directory for this exercise
 *     C:\exercise>start rmiregistry
 *  2. Register Server with RMIRegistry
 *     C:\exercise>start java Exercise40_02RegisterWithRMIServer
 *  3. Run a client (multiple times)
 *     C:\exercise>start java Exercise40_02RemoteInterfaceClient
 */
import java.rmi.registry.*;

public class Exercise40_02RegisterWithRMIServer {
  /** Main method */
  public static void main(String[] args) {
    try {
      Exercise40_02RemoteInterface obj = new Exercise40_02RemoteInterfaceImpl();
      Registry registry = LocateRegistry.getRegistry();
      registry.rebind("Exercise40_02RemoteInterfaceImpl", obj);
      System.out.println("Exercise42_02 RMI server " + obj + " registered");
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
