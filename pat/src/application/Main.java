package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static int patientID=1;
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the login view as the starting point
            Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
            Scene scene = new Scene(root);
            
            // Add CSS styling
            scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            
            // Configure the primary stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("CHMS - Patient Management System");
            primaryStage.setResizable(false); // Make window size fixed
            primaryStage.show();
            
        } catch(Exception e) {
            System.err.println("Error starting application: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}