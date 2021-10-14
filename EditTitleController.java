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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditTitleController implements Initializable
{
    @FXML
    Button cancelBtn;

    @FXML
    Button saveBtn;

    @FXML
    Button deleteBtn;


    @FXML
    TextField nameField;

    @FXML
    TextField idField;

    @FXML
    ComboBox titleBox;

    @FXML
    ComboBox authorBox;

    // Some local variable declarations
    // The data variable is used to populate the ComboBoxs
    final ObservableList<String> data = FXCollections.observableArrayList();
    final ObservableList<String> titles = FXCollections.observableArrayList();

    private ArrayList<String> info = new ArrayList<>();
    // To reference the models inside the controller
    private AuthorAdapter authorAdapter;
    private TitleAdapter titleAdapter;

    public void setModel(AuthorAdapter author, TitleAdapter title) {
        authorAdapter = author;
        titleAdapter = title;
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
            //insert the match information into the table by calling the appropriate method
            titleAdapter.editTitleInfo(idField.getText(), nameField.getText(), authorBox.getValue()+"");
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
            titleAdapter.deleteTitle(nameField.getText());
        }
        catch(SQLException ex)
        {
            displayAlert("ERROR: " + ex.getMessage());
        }

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    public void buildComboBoxData() {
        try {
            data.addAll(authorAdapter.getAuthorNames());
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
        try
        {
            titles.addAll(titleAdapter.getTitleNames());
        }
        catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    @FXML
    public void autoFill() throws SQLException
    {
        try
        {
            String name = titleBox.getValue()+"";
            info = titleAdapter.getTitleInfo(name);
            idField.setText(info.get(0));
            nameField.setText(info.get(1));
            authorBox.setValue(info.get(2));
        }
        catch(SQLException ex)
        {
            displayAlert("ERROR: "+ex.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        authorBox.setItems(data);
        titleBox.setItems(titles);
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
