import java.io.*;
import java.net.*;

public class Exercise33_08Client {
  public static void main(String[] args) {
    try {
      // Create a socket to connect to the server
      Socket socket = new Socket("localhost", 8000);
      // Socket socket = new Socket("130.254.204.36", 8000);
      // Socket socket = new Socket("drake.Armstrong.edu", 8000);

      // Create an input stream to receive data from the server
      ObjectInputStream fromServer = new ObjectInputStream(
        socket.getInputStream());

      // Create an output stream to send data to the server
      java.util.ArrayList<Long> numbers
        = (java.util.ArrayList<Long>)(fromServer.readObject());

      for (int i = 0; i < numbers.size(); i++) {
        System.out.println(numbers.get(i) + " ");
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
