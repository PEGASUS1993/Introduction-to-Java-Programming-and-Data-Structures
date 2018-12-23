/**
 *  Instructions on how to run this exercise:
 *  1. Step RMI registry from the class directory for this exercise
 *     C:\exercise>start rmiregistry
 *  2. Register Server with RMIRegistry
 *     C:\exercise>start java Exercise40_07RemoteInterfaceImpl
 *  3. Run a client (multiple times)
 *     C:\exercise>start java Exercise42_02Client
 */

import java.rmi.registry.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise40_07Client extends JFrame {
  // Chat server
  private Exercise40_07RemoteInterface server;

  // Text field for chat
  private JTextField jtf = new JTextField();

  // Text field for name
  private JTextField jtfName = new JTextField("Enter a name");

  // Text area to display contents
  private JTextArea jta = new JTextArea();

  public Exercise40_07Client() {
    // Panel p1 to hold the label and text field
    JPanel p1 = new JPanel();
    p1.setLayout(new BorderLayout());
    p1.add(new JLabel("Enter text"), BorderLayout.WEST);
    p1.add(jtf, BorderLayout.CENTER);

    // Panel p2 to hold the label and name field
    JPanel p2 = new JPanel();
    p2.setLayout(new BorderLayout());
    p2.add(new JLabel("Name"), BorderLayout.WEST);
    p2.add(jtfName, BorderLayout.CENTER);

    // Panel p to hold p1 and p2
    JPanel p = new JPanel();
    p.setLayout(new BorderLayout());
    p.add(p1, BorderLayout.SOUTH);
    p.add(p2, BorderLayout.NORTH);

    setLayout(new BorderLayout());
    add(p, BorderLayout.NORTH);
    add(new JScrollPane(jta), BorderLayout.CENTER);

    jtf.addActionListener(new ButtonListener()); // Register listener

    jta.setEditable(false); // Disable editing of chat area

    setTitle("RMI Chat Client");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true); // It is necessary to show the frame here!

    try {
      initializeRMI();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  protected boolean initializeRMI() throws Exception {
    String host = "localhost";

    try {
      Registry registry = LocateRegistry.getRegistry(host);
      server = (Exercise40_07RemoteInterface)registry.lookup(
        "ChatServer");
      System.out.println("Server object " + server + " found");
    }
    catch (Exception ex) {
      System.err.println(ex);
    }

    // Create callback for use by the server
    Exercise40_07CallbackInterfaceImpl callBack = new
      Exercise40_07CallbackInterfaceImpl(this);

    // Attempt to connect to the server
    if ((server.connect((Exercise40_07CallbackInterface)callBack)) != false) {
      return true;
    }
    else {
      jta.append("Unable to connect to the chat server\n");
      return false;
    }
  }

  private class ButtonListener implements ActionListener {
    @Override 
    public void actionPerformed(ActionEvent e) {
      try {
        // Get the text from the text field
        String string = jtfName.getText().trim() + ": " +
          jtf.getText().trim();

        // Send the text to the server
        server.receive(string);

        // Clear jtf
        jtf.setText("");
      }
      catch (Exception ex) {
        System.err.println(ex);
      }
    }
  }


  // Receive chat from callback
  public void getMessage(String message) {
    jta.append(message + "\n");
  }

  public static void main(String[] args) {
    new Exercise40_07Client();
  }
}
