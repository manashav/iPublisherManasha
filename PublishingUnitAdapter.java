package iPublisher;

import java.sql.*;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PublishingUnitAdapter
{
    Connection connection;

    public PublishingUnitAdapter(Connection conn, Boolean reset) throws SQLException
    {
        connection = conn;
        if(reset)
        {
            Statement stmt = connection.createStatement();
            try
            {
                stmt.execute("DROP TABLE Title");
                stmt.execute("DROP TABLE Author");
                stmt.execute("DROP TABLE PublishingUnit");

            }
            catch(SQLException ex)
            {
                //na
            }
            finally
            {
                stmt.execute("CREATE TABLE PublishingUnit ("
                        + "Name VARCHAR(255) NOT NULL PRIMARY KEY, "
                        + "Address VARCHAR (255), " + "Region VARCHAR (255)"+ ")");
            }
            populateSamples();
        }
    }

    private void populateSamples() throws SQLException
    {
        this.insertPublishingUnit("Head Office", "123 Road", "London");
        this.insertPublishingUnit("Toronto Office", "123 Circle", "Toronto");
        this.insertPublishingUnit("Ottawa Office", "123 Avenue", "Ottawa");
        this.insertPublishingUnit("Vancouver Office", "123 Lane", "Vancouver");
        this.insertPublishingUnit("Montreal Office", "123 Blvd", "Montreal");
        this.insertPublishingUnit("Waterloo Office", "123 Sqr", "Waterloo");
    }

    public void insertPublishingUnit(String name, String address, String region) throws SQLException
    {

        String sqlStatement = "INSERT INTO PublishingUnit VALUES(?,?,?)";

        PreparedStatement prepState = connection.prepareStatement(sqlStatement);
        prepState.setString(1, name);
        prepState.setString(2, address);
        prepState.setString(3, region);
        prepState.executeUpdate();

    }



    //to get the names of the publishing unit for the combo box in author
    public ObservableList<String> getPublishingUnitNames() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT Name FROM PublishingUnit";

        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);

        // loop for the all rs rows and update list
        while(rs.next())
        {
            list.add(rs.getString("Name"));
        }

        return list;
    }

    public void editPublishingUnitInfo(String name, String address, String region) throws SQLException {

        //to edit the content in it
        String sqlStatement = "UPDATE PublishingUnit" +
                " SET Address = ?, Region = ?" +
                " WHERE " +
                "Name = ? ";

        //need to use Prepared Statement since I need to use variables
        PreparedStatement prepState = connection.prepareStatement(sqlStatement);
        prepState.setString(1, address);
        prepState.setString(2, region);
        prepState.setString(3, name);
        prepState.executeUpdate();

        String sqlStatement1 = "UPDATE PublishingUnit" +
                " SET Name = ?" +
                " WHERE " +
                "Address = ? ";
        PreparedStatement prepState2 = connection.prepareStatement(sqlStatement1);
        prepState2.setString(1,name);
        prepState2.setString(2,address);
        prepState2.executeUpdate();
    }

    public void deletePublishingUnit(String name) throws SQLException //to delete the publishing unit
    {
        String sqlStatement = "DELETE FROM PublishingUnit" +
                " WHERE Name = ?";
        PreparedStatement prepState = connection.prepareStatement(sqlStatement);
        prepState.setString(1,name);
        prepState.executeUpdate();
    }

    public ArrayList<String> getPublishingUnitInfo( String n) throws SQLException
    {
        ArrayList<String> info = new ArrayList<>();
        String sqlStatement = "SELECT * FROM PublishingUnit WHERE Name =?";

        PreparedStatement prepState = connection.prepareStatement(sqlStatement);
        prepState.setString(1,n);

        ResultSet rs = prepState.executeQuery();

        while(rs.next())
        {
            info.add(rs.getString("Name"));
            info.add(rs.getString("Address"));
            info.add(rs.getString("Region"));
        }

        return info;
    }


}
