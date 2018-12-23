package chapter37;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exercise37_13 extends HttpServlet {

  private Connection connection;
  private PreparedStatement preparedInsertStatement;
  private PreparedStatement preparedViewStatement;
  private PreparedStatement preparedUpdateStatement;
  String IDValue = "";
  String lastNameValue = "";
  String firstNameValue = "";
  String miValue = "";
  String addressValue = "";
  String cityValue = "";
  String stateValue = "";
  String telephoneValue = "";

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    try {
      //connect to database
      initializeJDBC();

      //show results
      if (request.getParameter("go").equals("View")) {
        if (request.getParameter("ID").isEmpty()) {
          printForm(response);
        } else {
          try {
            //SQL statement
            String SQLView = "SELECT lastName, "
                    + "firstName, mi, address, city, state, telephone "
                    + "FROM Staff WHERE Staff.id = ? ";

            //create prepared statement
            preparedViewStatement = connection.prepareStatement(SQLView);
            preparedViewStatement.setInt(1, Integer.parseInt(request.getParameter("ID")));
            ResultSet rset = preparedViewStatement.executeQuery();

            if (rset.next()) {
              IDValue = request.getParameter("ID");
              lastNameValue = rset.getString(1);
              firstNameValue = rset.getString(2);
              miValue = rset.getString(3);
              addressValue = rset.getString(4);
              cityValue = rset.getString(5);
              stateValue = rset.getString(6);
              telephoneValue = rset.getString(7);

              //recreate html form with data returned from database
              printForm(response);

            } else {
              out.println("ID not found. Use browser back button to return to form.");
            }
          } catch (SQLException ex) {
            out.println(ex.getMessage());
          }
        }
      } else if (request.getParameter("go").equals("Insert")) {
        if (request.getParameter("ID").isEmpty()) {
          printForm(response);
        } else {
          try {
            //SQL Strings
            String SQLInsert = "INSERT INTO Staff "
                    + "(id, lastName, firstName, mi, "
                    + "address, city, state, telephone) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            IDValue = request.getParameter("ID");
            lastNameValue = request.getParameter("lastName");
            firstNameValue = request.getParameter("firstName");
            miValue = request.getParameter("mi");
            addressValue = request.getParameter("address");
            cityValue = request.getParameter("city");
            stateValue = request.getParameter("state");
            telephoneValue = request.getParameter("telephone");

            preparedInsertStatement = connection.prepareStatement(SQLInsert);
            preparedInsertStatement.setInt(1, Integer.parseInt(request.getParameter("ID")));
            preparedInsertStatement.setString(2, request.getParameter("lastName"));
            preparedInsertStatement.setString(3, request.getParameter("firstName"));
            preparedInsertStatement.setString(4, request.getParameter("mi"));
            preparedInsertStatement.setString(5, request.getParameter("address"));
            preparedInsertStatement.setString(6, request.getParameter("city"));
            preparedInsertStatement.setString(7, request.getParameter("state"));
            preparedInsertStatement.setString(8, request.getParameter("telephone"));
            preparedInsertStatement.execute();

            //recreate html form with data returned from database
            printForm(response);
          } catch (SQLException ex) {
            out.println(ex.getMessage());

          }
        }
      } else if (request.getParameter("go").equals("Update")) {
        if (request.getParameter("ID").isEmpty()
                || request.getParameter("lastName").isEmpty()
                || request.getParameter("firstName").isEmpty()
                || request.getParameter("mi").isEmpty()
                || request.getParameter("address").isEmpty()
                || request.getParameter("city").isEmpty()
                || request.getParameter("state").isEmpty()
                || request.getParameter("telephone").isEmpty()) {
          printForm(response);
        } else {
          try {
            //SQL statement
            String SQLUpdate = "UPDATE Staff SET "
                    + "lastName = ?, firstName = ?,"
                    + " mi = ?, address = ?, city = ?,"
                    + " state = ?, telephone = ? "
                    + "WHERE id = ?";

            IDValue = request.getParameter("ID");
            lastNameValue = request.getParameter("lastName");
            firstNameValue = request.getParameter("firstName");
            miValue = request.getParameter("mi");
            addressValue = request.getParameter("address");
            cityValue = request.getParameter("city");
            stateValue = request.getParameter("state");
            telephoneValue = request.getParameter("telephone");

            //create prepared statement
            preparedUpdateStatement = connection.prepareStatement(SQLUpdate);
            preparedUpdateStatement.setString(1, request.getParameter("lastName"));
            preparedUpdateStatement.setString(2, request.getParameter("firstName"));
            preparedUpdateStatement.setString(3, request.getParameter("mi"));
            preparedUpdateStatement.setString(4, request.getParameter("address"));
            preparedUpdateStatement.setString(5, request.getParameter("city"));
            preparedUpdateStatement.setString(6, request.getParameter("state"));
            preparedUpdateStatement.setString(7, request.getParameter("telephone"));
            preparedUpdateStatement.setInt(8, Integer.parseInt(request.getParameter("ID").toString()));
            preparedUpdateStatement.executeUpdate();

            //recreate html form with data returned from database
            printForm(response);
          } catch (SQLException ex) {
            out.println("<html><body>");
            out.println(ex.getMessage());
            out.println("</body></html>");
          }
        }
      } else {
        IDValue = "";
        lastNameValue = "";
        firstNameValue = "";
        miValue = "";
        addressValue = "";
        cityValue = "";
        stateValue = "";
        telephoneValue = "";

        //recreate html form with data returned from database
        printForm(response);
      }
      //major try catch block
    } finally {
      out.close();
    }
  }

  private void initializeJDBC() {
    try {
      // Explicitly load a MySQL driver
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver loaded");

      //Establish Connection
      connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void printForm(HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Servlet Exercise41_13</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<table><tr><td>");
    out.println("<fieldset><legend>Staff Information</legend>");
    out.println("<form action=\"Exercise41_13\" method=\"GET\">");
    out.println("ID: <input type=\"text\" name=\"ID\" size=\"9\" value=\"" + IDValue + "\">");
    out.println("<br><br>");
    out.println("Last Name: <input type=\"text\" name=\"lastName\" size=\"15\" value=\"" + lastNameValue + "\">");
    out.println("First Name: <input type=\"text\" name=\"firstName\" size=\"15\" value=\"" + firstNameValue + "\">");
    out.println("MI: <input type=\"text\" name=\"mi\" size=\"1\" value=\"" + miValue + "\"><br><br>");
    out.println("Address: <input type=\"text\" name=\"address\" size=\"20\" value=\"" + addressValue + "\"><br><br>");
    out.println("City: <input type=\"text\" name=\"city\" size=\"20\" value=\"" + cityValue + "\">");
    out.println("State: <input type=\"text\" name=\"state\" size=\"2\" value=\"" + stateValue + "\"><br><br>");
    out.println("Telephone: <input type=\"text\" name=\"telephone\" size=\"10\" value=\"" + telephoneValue + "\"><br>");
    out.println("</fieldset>");
    out.println("</td></tr>");
    out.println("<tr align=\"center\"><td>");
    out.println("<button type=\"submit\" name=\"go\" value=\"View\">View</button>");
    out.println("<button type=\"submit\" name=\"go\" value=\"Insert\">Insert</button>");
    out.println("<button type=\"submit\" name=\"go\" value=\"Update\">Update</button>");
    out.println("<button type=\"submit\" name=\"go\" value=\"Clear\">Clear</button>");
    out.println("</td></tr>");
    out.println("</form>");
    out.println("</table>");
    out.println("</body>");
    out.println("</html>");
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>
}
