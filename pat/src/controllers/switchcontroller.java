package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Patient;
import javafx.scene.text.Text;

public class switchcontroller {

    @FXML
    private Button profileButton, folderButton, calendarButton, menuButton, logoutButton;
    
    @FXML
    private StackPane rootPane; 
    
    @FXML
    private Text title;

    private Stage stage;
    private Patient currentPatient;
    
    
    @FXML
    public void initialize() {
        profileButton.setOnMouseClicked(event -> handleProfileButtonAction());
        folderButton.setOnMouseClicked(event -> handleFolderButtonAction());
        calendarButton.setOnMouseClicked(event -> handleCalendarButtonAction());
        menuButton.setOnMouseClicked(event -> handleHomeButtonAction());
        logoutButton.setOnMouseClicked(event -> handleLogoutButtonAction());
    }

    public void initData(Patient patient) {
        this.currentPatient = patient;
        if (patient != null && title != null) {
            title.setText("Hello " + patient.getPrenom() + ", welcome back!");
        }
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        // Validate login logic here
        boolean loginSuccessful = true; // Replace with actual validation

        if (loginSuccessful) {
            switchToScene("dashboard.fxml");
        } else {
            // Show error message
            System.out.println("Login failed");
        }
    }
    @FXML
    private void handletestButtonAction() {
        switchToScene("test.fxml");
    }
    @FXML
    private void handleHomeButtonAction() {
        switchToScene("dashboard.fxml");
    }
   
    @FXML
    private void handleProfileButtonAction() {
        switchToScene("updateProfile.fxml");
    }

    @FXML
    private void handleMenuButtonAction () {
        switchToScene("dashboard.fxml");
    }
    @FXML
    private void handleFolderButtonAction() {
        switchToScene("Scene.fxml");
    }

    
    @FXML
    private void handleCalendarButtonAction() {
//    	switchToScene("Calendar.fxml");
    }

    
    @FXML
    private void handleLogoutButtonAction() {
        switchToScene("login.fxml");
    }

   
    private void switchToScene(String fxmlFile) {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/" + fxmlFile));
            Parent root = loader.load();
            if (stage == null) {
                stage = (Stage) rootPane.getScene().getWindow();
            }
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
