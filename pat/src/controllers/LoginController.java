package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Patient;
import controllers.PatientDao;
import controllers.PatientDaoImpl;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML private TextField cinField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;
    
    private PatientDao patientDao;

    public LoginController() {
        this.patientDao = new PatientDaoImpl();
    }

    @FXML
    private void initialize() {
        // Clear any previous messages
        messageLabel.setText("");
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String cin = cinField.getText();
        String password = passwordField.getText();

        if (cin.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please enter both CIN and password");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            Patient patient = patientDao.verifyCredentials(cin, password);
            if (patient != null) {
                loadUpdateProfilePage(patient);
            } else {
                messageLabel.setText("Invalid CIN or password");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (SQLException e) {
            messageLabel.setText("Error during login: " + e.getMessage());
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    private void loadUpdateProfilePage(Patient patient) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/dashboard.fxml"));
            Parent root = loader.load();
            
            // Get the controller and initialize patient data
            controllers.switchcontroller controller = loader.getController();
            if (controller != null) {
                controller.initData(patient);
            }
            
            // Create and show the new scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.show();
            
            // Close the login window
            ((Stage) cinField.getScene().getWindow()).close();
        } catch (IOException e) {
            messageLabel.setText("Error loading dashboard: " + e.getMessage());
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCreateAccount(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/register.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Create Account");
            stage.show();
            
            // Close the login window
            ((Stage) cinField.getScene().getWindow()).close();
        } catch (IOException e) {
            messageLabel.setText("Error loading registration form");
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }
}
