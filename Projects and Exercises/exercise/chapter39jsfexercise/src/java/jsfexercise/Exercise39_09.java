package jsfexercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Mando
 */
@Named (value = "exercise39_09")
@ApplicationScoped

public class Exercise39_09 {
    String id;
    String lastName;
    String firstName;
    String mi;
    String address;
    String city;
    String state;
    String telephone;
    PreparedStatement preparedStatementForView;
    PreparedStatement preparedStatementForInsert;
    PreparedStatement preparedStatementForUpdate;
    String status;

    /** Creates a new instance of Exercise44_09 */
    public Exercise39_09() {
        id = lastName = firstName = mi = address = city = state = telephone = "";
        initializeJdbc();
    }

    private void initializeJdbc() {        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/javabook", "scott", "tiger");
            System.out.println("Database connected");

            String queryStringForView = "select lastName, firstName, "
                    + "mi, address, city, state, telephone from Staff "
                    + "where id = ?";

            preparedStatementForView =
                    connection.prepareStatement(queryStringForView);

            String queryStringForInsert = "insert into Staff ( id, lastName, "
                    + "firstName, mi, address, city, state, telephone) values "
                    + "( ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatementForInsert =
                    connection.prepareStatement(queryStringForInsert);

            String queryStringForUpdate = "update Staff set lastName = ?, "
                    + "firstName = ?, mi = ?, address = ?, city = ?, state = ?,"
                    + " telephone = ? where id = ?";

            preparedStatementForUpdate =
                    connection.prepareStatement(queryStringForUpdate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setMi(String mi) {
        this.mi = mi;
    }

    public String getMi() {
        return mi;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return status;
    }

    public void view() {
        System.out.println(id);
        try {
            preparedStatementForView.setString(1, id);
            ResultSet rset = preparedStatementForView.executeQuery();

            if (rset.next()) {
                lastName = rset.getString(1);
                firstName = rset.getString(2);
                mi = rset.getString(3);
                address = rset.getString(4);
                city = rset.getString(5);
                state = rset.getString(6);
                telephone = rset.getString(7);
            } else {
                System.out.println("Not found");
            }
            status = "Data retrieved";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insert() {
        try {
            preparedStatementForInsert.setString(1, id);
            preparedStatementForInsert.setString(2, lastName);
            preparedStatementForInsert.setString(3, firstName);
            preparedStatementForInsert.setString(4, mi);
            preparedStatementForInsert.setString(5, address);
            preparedStatementForInsert.setString(6, city);
            preparedStatementForInsert.setString(7, state);
            preparedStatementForInsert.setString(8, telephone);
            preparedStatementForInsert.executeUpdate();
            status = "Inserted";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update() {
        try {
            preparedStatementForUpdate.setString(8, id);
            preparedStatementForUpdate.setString(1, lastName);
            preparedStatementForUpdate.setString(2, firstName);
            preparedStatementForUpdate.setString(3, mi);
            preparedStatementForUpdate.setString(4, address);
            preparedStatementForUpdate.setString(5, city);
            preparedStatementForUpdate.setString(6, state);
            preparedStatementForUpdate.setString(7, telephone);
            preparedStatementForUpdate.executeUpdate();
            status = "Updated";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void clear() {
        status = "Cleared";
        id = lastName = firstName = mi = address = city = state = telephone = "";
    }
}
