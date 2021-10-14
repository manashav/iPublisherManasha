package iPublisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class AuthorAdapter
{
    Connection connection;

    public AuthorAdapter(Connection conn, Boolean reset) throws SQLException
    {
        connection = conn;
        if(reset)
        {
            Statement stmt = connection.createStatement();
            try
            {
                stmt.execute("DROP TABLE Title");
                stmt.execute("DROP TABLE Author");
            }
            catch(SQLException ex)
            {
                //na
            }
            finally
            {
                stmt.execute("CREATE TABLE Author ("
                        + "Name VARCHAR(255) NOT NULL PRIMARY KEY, "
                        + "ContractNum CHAR(30), " + "Email VARCHAR (255),"
                        +"Address VARCHAR (255),"
                        +"PublisherUnit VARCHAR (255) NOT NULL REFERENCES PublishingUnit (Name) "+ ")");
            }
            populateSamples();
        }
    }

    private void populateSamples() throws SQLException
    {
        this.insertAuthor("Sample Author", "1", "sample@gmail.com", "123 House", "Head Office");

    }

    public void insertAuthor(String name, String cNum, String email, String address, String pu) throws SQLException
    {
        String sqlStatement = "INSERT INTO Author VALUES(?,?,?,?,?)";

        PreparedStatement prepState = connection.prepareStatement(sqlStatement);
        prepState.setString(1, name);
        prepState.setString(2, cNum);
        prepState.setString(3, email);
        prepState.setString(4,address);
        prepState.setString(5,pu);
        prepState.executeUpdate();
    }




    //to get the names of the publishing unit for the combo box in author
    public ObservableList<String> getAuthorNames() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT Name FROM Author";

        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);

        // loop for the all rs rows and update list
        while(rs.next())
        {
            list.add(rs.getString("Name"));
        }

        return list;
    }

    public void editAuthorInfo(String name, String cNum, String email, String address, String pu) throws SQLException {

        String sqlStatement = "UPDATE Author" +
                " SET ContractNum = ?, Email = ?, Address = ? , PublisherUnit = ?" +
                " WHERE " +
                "Name = ? ";

        //need to use Prepared Statement since I need to use variables
        PreparedStatement prepState = connection.prepareStatement(sqlStatement);
        prepState.setString(1, cNum);
        prepState.setString(2, email);
        prepState.setString(3, address);
        prepState.setString(4, pu);
        prepState.setString(5, name);
        prepState.executeUpdate();

        String sqlStatement1 = "UPDATE Author" +
                " SET Name = ?" +
                " WHERE " +
                "Email = ? ";
        PreparedStatement prepState2 = connection.prepareStatement(sqlStatement1);
        prepState2.setString(1,name);
        prepState2.setString(2,email);
        prepState2.executeUpdate();
    }

    public void deleteAuthor(String name) throws SQLException
    {
        String sqlStatement = "DELETE FROM Author" +
                " WHERE Name = ?";
        PreparedStatement prepState = connection.prepareStatement(sqlStatement);
        prepState.setString(1,name);
        prepState.executeUpdate();
    }

    public ArrayList<String> getAuthorInfo(String n) throws SQLException
    {
        ArrayList<String> info = new ArrayList<>();
        String sqlStatement = "SELECT * FROM Author WHERE Name =?";

        PreparedStatement prepState = connection.prepareStatement(sqlStatement);
        prepState.setString(1,n);

        ResultSet rs = prepState.executeQuery();

        while(rs.next())
        {
            info.add(rs.getString("Name"));
            info.add(rs.getString("ContractNum"));
            info.add(rs.getString("Email"));
            info.add(rs.getString("Address"));
            info.add(rs.getString("PublisherUnit"));
        }
        return info;
    }

}


