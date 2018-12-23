import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.rmi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Exercise40_02RemoteInterfaceClient
    extends JFrame implements ActionListener {
  // Text field for receiving radius
  private JTextField jtfAnnualInterestRate = new JTextField();
  private JTextField jtfNumOfYears = new JTextField();
  private JTextField jtfLoanAmount = new JTextField();
  private JButton jbtSubmit= new JButton("Submit");

  // Text area to display contents
  private JTextArea jta = new JTextArea();

  // IO streams
  private DataOutputStream osToServer;
  private DataInputStream isFromServer;

  public static void main(String[] args) {
    new Exercise40_02RemoteInterfaceClient();
  }

  public Exercise40_02RemoteInterfaceClient() {
    // Panel p1 to hold the labels
    JPanel p1 = new JPanel();
    p1.setLayout(new GridLayout(3, 1));
    p1.add(new JLabel("Annual Interest Rate"));
    p1.add(new JLabel("Number Of Years"));
    p1.add(new JLabel("Loan Amount"));

    // Panel p2 to hold the text fields
    Panel p2 = new Panel();
    p2.setLayout(new GridLayout(3, 1));
    p2.add(jtfAnnualInterestRate);
    p2.add(jtfNumOfYears);
    p2.add(jtfLoanAmount);

    // Panel p to hold p1, p2 and a button
    JPanel p = new JPanel();
    p.setLayout(new BorderLayout());
    p.add(p1, BorderLayout.WEST);
    p.add(p2, BorderLayout.CENTER);
    p.add(jbtSubmit, BorderLayout.EAST);

    jtfAnnualInterestRate.setHorizontalAlignment(JTextField.RIGHT);
    jtfNumOfYears.setHorizontalAlignment(JTextField.RIGHT);
    jtfLoanAmount.setHorizontalAlignment(JTextField.RIGHT);

    setLayout(new BorderLayout());
    add(p, BorderLayout.NORTH);
    add(new JScrollPane(jta), BorderLayout.CENTER);

    jbtSubmit.addActionListener(this); // Register listener

    jta.setWrapStyleWord(true);
    jta.setLineWrap(true);

    setTitle("Exercise25_03Client");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true); // It is necessary to show the frame here!

    initializeRMI();
  }

  private Exercise40_02RemoteInterface student;
  private boolean isStandalone; // Is applet or application

    /** Initialize RMI */
  protected void initializeRMI() {
    String host = "localhost";
    // if (!isStandalone) host = getCodeBase().getHost();

    try {
      Registry registry = LocateRegistry.getRegistry(host);
      student = (Exercise40_02RemoteInterface)
        registry.lookup("Exercise40_02RemoteInterfaceImpl");
      System.out.println("Server object " + student + " found");
    }
    catch(Exception ex) {
      System.out.println(ex);
    }
  }

  @Override 
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() instanceof JButton) {
      try {
        // Get the annual interest rate from the text field
        double annualInterestRate =
          Double.parseDouble(jtfAnnualInterestRate.getText().trim());

        // Get the number of years from the text field
        int numOfYears =
          Integer.parseInt(jtfNumOfYears.getText());

        // Get the loan amount from the text field
        double loanAmount =
          Double.parseDouble(jtfLoanAmount.getText().trim());

        // Get monthly payment from the server
        double monthlyPayment = student.monthlyPayment(loanAmount,
          annualInterestRate, numOfYears);

        // Get total payment from the server
        double totalPayment = student.totalPayment(loanAmount,
          annualInterestRate, numOfYears);

       jta.append("Annual Interest Rate: " + annualInterestRate +
            " Number of Years: " + numOfYears + " Loan Amount: " +
            loanAmount + "\n");
       jta.append(" monthlyPayment: " + monthlyPayment + " " +
            " totalPayment: " + totalPayment + '\n');
      }
      catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }
}
