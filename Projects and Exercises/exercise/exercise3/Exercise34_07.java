/*
 Statement for creating a table
 create table Quiz(
   questionId int,
   question varchar(4000),
   choicea varchar(1000),
   choiceb varchar(1000),
   choicec varchar(1000),
   choiced varchar(1000),
   answer varchar(5));
*/
import java.io.*;
import java.util.*;
import java.sql.*;

public class Exercise34_07 {
  private ArrayList<Quiz> chapters = new ArrayList<Quiz>();
  private PreparedStatement pstmt1;

  static class Quiz {
    String question = "";
    String choicea = "";
    String choiceb = "";
    String choicec = "";
    String choiced = "";
    String answer;
    String hint;
  }

  public static void main(String[] args) {
    new Exercise34_07();
  }

  /** Initialize global variables */
  public Exercise34_07() {
    try {
      readTest(chapters);

      initializeJdbc();

      int questionNo = 1;
      for (Quiz question : chapters) {
        storeQuiz(questionNo++, question);
      }

    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void readTest(List<Quiz> testForAChapter) throws
    Exception {
    // Create a buffered reader for reading questions from a file
    BufferedReader in = new BufferedReader(new FileReader(
      "Quiz.txt"));

    // Quiz count
    int questionCount = 0;
    boolean beginningOfQuiz = true; // for the first one

    // Text line from the question file
    String line = "";
    Quiz question = null;

    // Read and process each line from the text file
    loop:while ((line = in.readLine()) != null) {
      // Process a blank line in the text file
      if (line.length() < 1) {
        continue;
      }

      // Determine question statement and multiple choices
      if (line.charAt(0) == 'a' && line.charAt(1) == '.') {
        question.choicea = line.substring(2);
      }
      else if (line.charAt(0) == 'b' && line.charAt(1) == '.') {
        question.choiceb = line.substring(2);
      }
      else if (line.charAt(0) == 'c' && line.charAt(1) == '.') {
        question.choicec = line.substring(2);
      }
      else if (line.charAt(0) == 'd' && line.charAt(1) == '.') {
        question.choiced = line.substring(2);
      }
      else if (line.matches("(\\d)+\\..*")) { // Start a new question
        beginningOfQuiz = true;
        questionCount++; // Increase question count
        question = new Quiz(); // Create a new test
        testForAChapter.add(question); // Add to the list
				question.question += line;
     }
      else if (line.toUpperCase().indexOf("ANSWER") == 0) { // End of question section
        // Extract answer and explanation
        StringTokenizer st = new StringTokenizer(line.substring(7),
                                                 ".\n\r\t ");

        question.answer = st.nextToken().toUpperCase();

        if (st.hasMoreTokens()) {
          question.hint = st.nextToken("\n\r");
        }
      }
      else if (line.charAt(0) == ' ') { // Process spaces before line
        String spaces = "";
        for (int j = 0;
                     ((j < line.length()) && (line.charAt(j) == ' ')); j++) {
          spaces += "&nbsp";
        }
        question.question += spaces;
        question.question += line;
      }
      else {
        if (beginningOfQuiz && Character.isDigit(line.charAt(0)) &&
            line.charAt(1) == '.') {
          question.question += line.substring(2);
          beginningOfQuiz = false;
        }
        else if (beginningOfQuiz && Character.isDigit(line.charAt(0)) &&
                 Character.isDigit(line.charAt(1))
                 && line.charAt(2) == '.') {
          question.question += line.substring(3);
          beginningOfQuiz = false;
        }
        else {
          question.question += line;
        }
      }
    }

    // Close the file
    in.close();
  }

  /** Initialize database connection */
  private void initializeJdbc() {
    try {
      // Load the JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver loaded");

      // Declare driver and connection string
//      String connectionString = "jdbc:odbc:exampleMDBDataSource";
      // For MySQL
      String connectionString = "jdbc:mysql://localhost/javabook";
      // For Oracle
//      String connectionString = "jdbc:oracle:" +
//                                "thin:scott/tiger@liang.armstrong.edu:1521:orcl";
      // Connect to the sample database
      Connection conn = DriverManager.getConnection
                        (connectionString, "scott", "tiger");

      // Create a statement to insert questions
      pstmt1 = conn.prepareStatement("insert into Quiz " +
        "(questionId, question, choicea, choiceb, choicec, choiced, answer)"
        + "values (?, ?, ?, ?, ?, ?, ?)");
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /** Store a question to the database */
  private void storeQuiz(int questionNo,
                             Quiz question) throws SQLException {
    pstmt1.setInt(1, questionNo);
    pstmt1.setString(2, question.question);
    pstmt1.setString(3, question.choicea);
    pstmt1.setString(4, question.choiceb);
    pstmt1.setString(5, question.choicec);
    pstmt1.setString(6, question.choiced);
    pstmt1.setString(7, question.answer);
    pstmt1.executeUpdate();
  }
}
