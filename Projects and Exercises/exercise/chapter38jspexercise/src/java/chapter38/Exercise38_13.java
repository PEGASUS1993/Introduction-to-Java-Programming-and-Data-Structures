package chapter38;

import java.sql.*;
import java.util.*;

public class Exercise38_13 {

  Statement statement;
  ArrayList<String> questions = new ArrayList<String>();

  /** Initialize database connection */
  public Exercise38_13() {
    initializeJdbc();
    loadQuestions();
  }

  public void increaseYesCount(String question) {
    try {
      statement.executeUpdate(
              "update Poll set yesCount = yesCount + 1 where question = '"
              + question + "'");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void increaseNoCount(String question) {
    try {
      statement.executeUpdate(
              "update Poll set noCount = noCount + 1 where question = '"
              + question + "'");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public int getYesCount(String question) {
    try {
      ResultSet result = statement.executeQuery(
              "select yesCount from Poll where question = '"
              + question + "'");
      result.next();
      return result.getInt(1);
    } catch (Exception ex) {
      return 0;
    }
  }

  public boolean logIPAddress(String IPAddress) {
    // Fill code

    return true;
  }

  public int getNoCount(String question) {
    try {
      ResultSet result = statement.executeQuery(
              "select noCount from Poll where question = '"
              + question + "'");
      result.next();
      return result.getInt(1);
    } catch (Exception ex) {
      return 0;
    }
  }

  private void initializeJdbc() {
    try {
      // Explicitly load a MySQL driver
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver loaded");

      // Establish a connection
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");

      statement = connection.createStatement();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void loadQuestions() {
    try {
      ResultSet resultSet = statement.executeQuery("select question from Poll");
      while (resultSet.next()) {
        questions.add(resultSet.getString(1));
      }

      Collections.sort(questions); // Sort the questions
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public ArrayList getQuestions() {
    return questions;
  }
}