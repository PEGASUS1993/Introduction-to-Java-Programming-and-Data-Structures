import java.io.*;
import java.net.*;
import java.util.*;

public class Last100PrimeServer {
  public static void main(String[] args) {
    try {
      // Create a server socket
      ServerSocket serverSocket = new ServerSocket(8000);
      System.out.println("Last100PrimeServer started at " + new Date() +
        '\n');

      // Number a client
      int clientNo = 1;

      while (true) {
        // Listen for a new connection request
        Socket socket = serverSocket.accept();

        // Display the client number
        System.out.println("Starting thread for client " + clientNo +
          " at " + new Date() + '\n');

        // Find the client's host name, and IP address
        InetAddress inetAddress = socket.getInetAddress();
        System.out.println("Client " + clientNo + "'s host name is "
          + inetAddress.getHostName() + "\n");
        System.out.println("Client " + clientNo + "'s IP Address is "
          + inetAddress.getHostAddress() + "\n");

        // Create a new thread for the connection
        HandleAClient thread = new HandleAClient(socket);

        // Start the new thread
        thread.start();

        // Increment clientNo
        clientNo++;
      }
    }
    catch (IOException ex) {
      System.err.println(ex);
    }
  }

// Inner class
// Define the thread class for handling new connection
  static class HandleAClient extends Thread {
    private final static int NUMBER_OF_PRIMES = 100;

    private Socket socket; // A connected socket

    /** Construct a thread */
    public HandleAClient(Socket socket) {
      this.socket = socket;
    }

    /** Run a thread */
    public void run() {
      try {
        // Get file name from the user
        DataInputStream fromClient =
          new DataInputStream(socket.getInputStream());
        String filename = fromClient.readUTF();

        // Read 100 numbers
        DataInputStream input =
          new DataInputStream(new BufferedInputStream(
          new FileInputStream(filename)));

        int k = 0;
        ArrayList<Long> numbers = new ArrayList<Long>();

        // (input.available() / 8) * 8 ensures getting right data
        input.skip((input.available() / 8) * 8 - 8 * NUMBER_OF_PRIMES);
        while (input.available() > 0) {
          numbers.add(input.readLong());
        }

        input.close();

        ObjectOutputStream outputToClient = new ObjectOutputStream(
          socket.getOutputStream());

        // Send area back to the client
        outputToClient.writeObject(numbers);
      }
      catch (IOException e) {
        System.err.println(e);
      }
    }
  }
}
