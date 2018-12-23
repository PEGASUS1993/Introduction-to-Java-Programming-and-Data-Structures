package chapter38;

import java.sql.*;

public class Exercise38_12 {
    PreparedStatement pstmtIncreaseYes, pstmtIncreaseNo;
    Connection conn;
    Statement statement;

    /** Initialize database connection */
    public Exercise38_12() {
        try {
            // Establish a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");

            // Create a Statement
            pstmtIncreaseYes = conn.prepareStatement(
                    "update Poll set yesCount = yesCount + 1 ");
            pstmtIncreaseNo = conn.prepareStatement(
                    "update Poll set noCount = noCount + 1 ");

            statement = conn.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void increaseYesCount() {
        try {
            statement.executeUpdate("update Poll set yesCount = yesCount + 1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void increaseNoCount() {
        try {
            statement.executeUpdate("update Poll set noCount = noCount + 1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getYesCount() {
        try {
            ResultSet result = statement.executeQuery("select yesCount from Poll");
            result.next();
            return result.getInt(1);
        } catch (Exception ex) {
            return 0;
        }
    }

    public int getNoCount() {
        try {
            ResultSet result = statement.executeQuery("select noCount from Poll");
            result.next();
            return result.getInt(1);
        } catch (Exception ex) {
            return 0;
        }
    }
}
