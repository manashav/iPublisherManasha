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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditPublishingUnitController implements Initializable
{

    @FXML
    Button cancelBtn;

    @FXML
    Button saveBtn;

    @FXML
    Button deleteBtn;

    @FXML
    ComboBox publisherBox;

    @FXML
    TextArea addressArea;

    @FXML
    TextField nameField;

    @FXML
    TextField regionField;

    final ObservableList<String> data = FXCollections.observableArrayList();
    private ArrayList<String> info = new ArrayList<>();
    private PublishingUnitAdapter publishingUnitAdapter;

    public void setModel(PublishingUnitAdapter publishingUnit) {

        publishingUnitAdapter = publishingUnit;
        buildComboBoxData();
    }

    @FXML
    public void cancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void save() {
        try {
            publishingUnitAdapter.editPublishingUnitInfo(nameField.getText(),addressArea.getText(),regionField.getText());
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void delete()
    {
        try
        {
            publishingUnitAdapter.deletePublishingUnit(nameField.getText());
        }
        catch(SQLException ex)
        {
            displayAlert("ERROR: " + ex.getMessage());
        }

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void autoFill() throws SQLException
    {
        try
        {
            String name = publisherBox.getValue()+"";
            info = publishingUnitAdapter.getPublishingUnitInfo(name);
            nameField.setText(info.get(0));
            addressArea.setText(info.get(1));
            regionField.setText(info.get(2));
        }
        catch(SQLException ex)
        {
            displayAlert("ERROR: "+ex.getMessage());
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

            stage.getIcons().add(new Image("file:src/TennisBallGames/WesternLogo.png"));
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

        }
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
        publisherBox.setItems(data);
    }
}
