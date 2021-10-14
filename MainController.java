package iPublisher;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Abdelkader
 */
public class MainController implements Initializable {

    private PublishingUnitAdapter publishingUnit;
    private AuthorAdapter author;
    private TitleAdapter title;

    private Connection conn;
    @FXML
    private MenuBar mainMenu;

    public void showAbout() throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("About.fxml"));
        Parent About = (Parent) fxmlLoader.load();

        Scene scene = new Scene(About);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("About Us");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void exit() {

        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addPublishingUnit() throws Exception {
        // create Teams model
        publishingUnit = new PublishingUnitAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPublishingUnit.fxml"));
        Parent addPublishingUnit = (Parent) fxmlLoader.load();
        AddPublishingUnitController addPublishingUnitController = (AddPublishingUnitController) fxmlLoader.getController();
        addPublishingUnitController.setModel(publishingUnit);

        Scene scene = new Scene(addPublishingUnit);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Add New Publishing Unit");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void editPublishingUnit() throws Exception {
        // create Teams model
        publishingUnit = new PublishingUnitAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditPublishingUnit.fxml"));
        Parent editPublishingUnit = (Parent) fxmlLoader.load();
        EditPublishingUnitController editPublishingUnitController = (EditPublishingUnitController) fxmlLoader.getController();
        editPublishingUnitController.setModel(publishingUnit);

        Scene scene = new Scene(editPublishingUnit);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Edit/ Delete Publishing Unit");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void addAuthor() throws Exception {
        // Toggle the comments below after you finish the requirement of Task #3

        // create Teams model
        author = new AuthorAdapter(conn, false);
        // create matches model
        publishingUnit = new PublishingUnitAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddAuthor.fxml"));
        Parent addAuthor = (Parent) fxmlLoader.load();
        AddAuthorController addAuthorController = (AddAuthorController) fxmlLoader.getController();
        addAuthorController.setModel(author, publishingUnit);

        Scene scene = new Scene(addAuthor);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Add New Author");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void addTitle() throws Exception {
        // Toggle the comments below after you finish the requirement of Task #3

        // create Teams model
        title = new TitleAdapter(conn, false);
        // create matches model
        author = new AuthorAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddTitle.fxml"));
        Parent addTitle = (Parent) fxmlLoader.load();
        AddTitleController addTitleController = (AddTitleController) fxmlLoader.getController();
        addTitleController.setModel(author, title);

        Scene scene = new Scene(addTitle);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Add New Match");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void editTitle() throws Exception {
        // Toggle the comments below after you finish the requirement of Task #3

        // create Teams model
        title = new TitleAdapter(conn, false);
        // create matches model
        author = new AuthorAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditTitle.fxml"));
        Parent editTitle = (Parent) fxmlLoader.load();
        EditTitleController editTitleController = (EditTitleController) fxmlLoader.getController();
        editTitleController.setModel(author, title);

        Scene scene = new Scene(editTitle);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Edit Title");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }
    @FXML
    public void editAuthor() throws Exception {
        // Toggle the comments below after you finish the requirement of Task #3

        // create Teams model
        publishingUnit = new PublishingUnitAdapter(conn, false);
        // create matches model
        author = new AuthorAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditAuthor.fxml"));
        Parent editAuthor = (Parent) fxmlLoader.load();
        EditAuthorController editAuthorController = (EditAuthorController) fxmlLoader.getController();
        editAuthorController.setModel(author, publishingUnit);

        Scene scene = new Scene(editAuthor);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Edit Title");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void resetDB() {
        try {
            // create Publishing Unit model
            publishingUnit = new PublishingUnitAdapter(conn, true);
            displayAlert("PublishingUnit table has been created");

        } catch (SQLException ex) {
            System.out.println("Pu");
            displayAlert("ERROR: " + ex.getMessage());
        }
        try {
            // create Author model
            author = new AuthorAdapter(conn, true);
            displayAlert("Author table has been reset");

        } catch (SQLException ex) {
            System.out.println("Author");
            displayAlert("ERROR: " + ex.getMessage());
        }
        try {
            // create Title model
            title = new TitleAdapter(conn, true);
            displayAlert("Title table has been reset");

        } catch (SQLException ex) {
            System.out.println("Title");
            displayAlert("ERROR: " + ex.getMessage());
        }

    }

    private void displayAlert(String msg) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
            Parent ERROR = loader.load();
            AlertController controller = (AlertController) loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // Create a named constant for the URL
            // NOTE: This value is specific for Java DB
            String DB_URL = "jdbc:derby:iPublisherDB;create=true";
            // Create a connection to the database
            conn = DriverManager.getConnection(DB_URL);

        } catch (SQLException ex) {
            displayAlert(ex.getMessage());
        }

    }

}

