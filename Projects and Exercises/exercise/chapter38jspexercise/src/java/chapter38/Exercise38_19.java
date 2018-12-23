/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter38;

import java.sql.*;
import javax.servlet.http.HttpServletRequest;

public class Exercise38_19 {

    private String id = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    private String firstname = "";

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    private String mi = "";

    public String getMi() {
        return mi;
    }

    public void setMi(String mi) {
        this.mi = mi;
    }
    private String lastname = "";

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    private String address = "";

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    private String city = "";

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    private String state = "";

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    private String email = "";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private String telephone = "";

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    Connection connection;
    ResultSet result;

    public ResultSet getResult() {
        return result;
    }

    public Exercise38_19() {
        try {
            System.out.println("Contructor trying initialization...");
            initialize();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initialize() throws ClassNotFoundException {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
            System.out.println("Initialized!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void view(HttpServletRequest request) {
        Statement s = null;
        try {
            s = connection.createStatement();
            s.executeQuery("SELECT * FROM Staff WHERE id=" + id);
            result = s.getResultSet();

            if (result.next()) {
                id = result.getString("id");
                firstname = result.getString("firstname");
                mi = result.getString("mi");
                lastname = result.getString("lastname");
                address = result.getString("address");
                city = result.getString("city");
                state = result.getString("state");
                email = result.getString("email");
                telephone = result.getString("telephone");
            }
        } catch (Exception e) {
        } finally {
        }
    }

    public int insert(HttpServletRequest request) {
        PreparedStatement s = null;
        int count = 0;
        try {
            s = connection.prepareStatement("INSERT INTO Staff " + "(id, lastName, firstName, mi, address, city, state, telephone, email) " + "VALUES (?,?,?,?,?,?,?,?,?)");
            s.setString(1, id);
            s.setString(2, lastname);
            s.setString(3, firstname);
            s.setString(4, mi);
            s.setString(5, address);
            s.setString(6, city);
            s.setString(7, state);
            s.setString(8, telephone);
            s.setString(9, email);
            count = s.executeUpdate();
            s.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return count;
        }
    }

    public int update(HttpServletRequest request) {
        PreparedStatement s = null;
        int count = 0;
        try {
            s = connection.prepareStatement("update Staff set lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?, email = ? WHERE id = ?");
            s.setString(9, id);
            s.setString(1, lastname);
            s.setString(2, firstname);
            s.setString(3, mi);
            s.setString(4, address);
            s.setString(5, city);
            s.setString(6, state);
            s.setString(7, telephone);
            s.setString(8, email);
            count = s.executeUpdate();
            s.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return count;
        }

    }

    public void reset() {
        id = "";
        firstname = "";
        mi = "";
        lastname = "";
        address = "";
        city = "";
        state = "";
        email = "";
        telephone = "";

    }
}
