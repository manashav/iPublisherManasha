package iPublisher;

import java.sql.*;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TitleAdapter
{
    Connection connection;

    public TitleAdapter(Connection conn, Boolean reset) throws SQLException
    {
        connection = conn;
        if(reset)
        {
            Statement stmt = connection.createStatement();
            try
            {
                stmt.execute("DROP TABLE Title");
            }
            catch(SQLException ex)
            {
                //na
            }
            finally
            {
                stmt.execute("CREATE TABLE Title ("
                        + "ID VARCHAR(255) NOT NULL PRIMARY KEY, "
                        + "Name VARCHAR (255), " + "Author VARCHAR (255) NOT NULL REFERENCES Author (Name)"+ ")");
            }
            populateSamples();
        }
    }

    private void populateSamples() throws SQLException
    {
        this.insertTitle("123","Sample Book","Sample Author" );
    }

    public void insertTitle(String id, String name, String author) throws SQLException
    {

        String sqlStatement = "INSERT INTO Title VALUES(?,?,?)";

        PreparedStatement prepState = connection.prepareStatement(sqlStatement);
        prepState.setString(1, id);
        prepState.setString(2, name);
        prepState.setString(3, author);
        prepState.executeUpdate();

    }




    //to get the names of the titles for the combo box in author
    public ObservableList<String> getTitleNames() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT Name FROM Title";

        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);

        // loop for the all rs rows and update list
        while(rs.next())
        {
            list.add(rs.getString("Name"));
        }

        return list;
    }

    public void editTitleInfo(String id, String name, String author) throws SQLException {

        //edit the content in this class
        String sqlStatement = "UPDATE Title" +
                " SET ID = ?, Author = ?" +
                " WHERE " +
                "Name = ? ";

        //need to use Prepared Statement since I need to use variables
        PreparedStatement prepState = connection.prepareStatement(sqlStatement);
        prepState.setString(1, id);
        prepState.setString(2, author);
        prepState.setString(3, name);
        prepState.executeUpdate();

        String sqlStatement1 = "UPDATE Title" +
                " SET Name = ?" +
                " WHERE " +
                "ID = ? ";
        PreparedStatement prepState2 = connection.prepareStatement(sqlStatement1);
        prepState2.setString(1,name);
        prepState2.setString(2,id);
        prepState2.executeUpdate();
    }

    public void deleteTitle(String name) throws SQLException
    {
        String sqlStatement = "DELETE FROM Title" +
                " WHERE Name = ?";
        PreparedStatement prepState = connection.prepareStatement(sqlStatement);
        prepState.setString(1,name);
        prepState.executeUpdate();
    }

    public ArrayList<String> getTitleInfo(String n) throws SQLException
    {
        ArrayList<String> info = new ArrayList<>();
        String sqlStatement = "SELECT * FROM Title WHERE Name =?";

        PreparedStatement prepState = connection.prepareStatement(sqlStatement);
        prepState.setString(1,n);

        ResultSet rs = prepState.executeQuery();

        while(rs.next())
        {
            info.add(rs.getString("ID"));
            info.add(rs.getString("Name"));
            info.add(rs.getString("Author"));
        }

        return info;
    }
}
