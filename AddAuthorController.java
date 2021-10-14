package iPublisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddAuthorController implements Initializable
{
    @FXML
    Button cancelBtn;

    @FXML
    Button saveBtn;

    @FXML
    TextArea addressArea;

    @FXML
    TextField nameField;

    @FXML
    TextField contractField;

    @FXML
    TextField emailField;

    @FXML
    ComboBox publishingUnitBox;

    // Some local variable declarations
    // The data variable is used to populate the ComboBoxs
    final ObservableList<String> data = FXCollections.observableArrayList();
    // To reference the models inside the controller
    private AuthorAdapter authorAdapter;
    private PublishingUnitAdapter publishingUnitAdapter;

    public void setModel(AuthorAdapter author, PublishingUnitAdapter pu) {
        authorAdapter = author;
        publishingUnitAdapter = pu;
        buildComboBoxData();
    }
    @FXML
    public void cancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void save() {
        try{

            authorAdapter.insertAuthor(nameField.getText(), contractField.getText(),emailField.getText(),addressArea.getText(),publishingUnitBox.getValue()+"");
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();


    }
    public void buildComboBoxData() {
        try {
            data.addAll(publishingUnitAdapter.getPublishingUnitNames());
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        publishingUnitBox.setItems(data);
    }

    private void displayAlert(String msg) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
            Parent ERROR = loader.load();
            AlertController controller = (AlertController) loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.getIcons().add(new Image("file:src/TennisBallGames/WesternLogo.png"));
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

        }
    }
}
