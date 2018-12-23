package chapter37;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Exercise37_07 extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html";
    private PreparedStatement pstmtGetResult;
    private Connection conn;

    public void init() throws ServletException {
        initializeJdbc();
    }

    /**Initialize database connection*/
    private void initializeJdbc() {
        try {
                // Explicitly load a MySQL driver
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver loaded");
      
            // Establish a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
            System.out.println("Database connected");

            pstmtGetResult = conn.prepareStatement("select name, score from csci4990 where ssn = ?");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**Process the HTTP Get request*/
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();

        String ssn = "";
        try {
            ssn = request.getParameter("ssn");
            String table = request.getParameter("course");

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select name, score from "
                    + table + " where ssn = '" + ssn + "'");

            if (rs == null) {
                out.println("Not found");
            } else {
                while (rs.next()) {
                    out.println(rs.getString(1));
                    out.println(rs.getDouble(2));
                }
            }
        } catch (Exception e) {
            out.println(e.getMessage());
        }

        out.close();
    }

    /**Clean up resources*/
    public void destroy() {
    }
}
