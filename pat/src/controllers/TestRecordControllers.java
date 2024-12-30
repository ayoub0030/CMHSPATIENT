package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import controllers.DatabaseConnection;
import models.TestRecord;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TestRecordControllers {
	@FXML
	private ImageView logoImageView;

	@FXML
	private Label titleLabel;

	@FXML
	private Label descriptionLabel;

    @FXML
    private TableView<TestRecord> tableView;

    @FXML
    private TableColumn<TestRecord, String> testNameColumn;

    @FXML
    private TableColumn<TestRecord, String> testResultsColumn;

    @FXML
    private TableColumn<TestRecord, LocalDate> testDateColumn;

    @FXML
    private TableColumn<TestRecord, String> statusColumn;

    @FXML
    private TableColumn<TestRecord, String> doctorColumn;

    @FXML
    private TableColumn<TestRecord, Button> prescriptionColumn;

    private ObservableList<TestRecord> dataList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
    	 Image logo = new Image("file:../images/heartbeat.jpg");
    	 if (logo.isError()) {
             System.out.println("Error loading image.");
         } else {
        	 ImageView logoImageView = new ImageView(logo);
         }
         logoImageView.setImage(logo);
         //titleLabel.getStyleClass().add("title-label"); // Ajoutez une classe CSS

    	tableView.getStylesheets().add(getClass().getResource("/views/style.css").toExternalForm());


        // Configurer les colonnes du TableView
        testNameColumn.setCellValueFactory(new PropertyValueFactory<>("testName"));
        testResultsColumn.setCellValueFactory(new PropertyValueFactory<>("testResults"));
        testDateColumn.setCellValueFactory(new PropertyValueFactory<>("testDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        doctorColumn.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        prescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("prescription"));
        prescriptionColumn.setCellFactory(col -> new TableCell<>(){
            @Override
            protected void updateItem(Button button, boolean empty) {
                super.updateItem(button, empty);
                if (empty || button == null) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        });
        adjustTableWidthOnWindowResize();
        int patientId = 1; 
        loadDataFromDatabase(patientId);
        tableView.setItems(dataList);
        adjustColumnWidths();
        adjustTableHeight();
    }
    private void adjustTableHeight() {
        tableView.setFixedCellSize(25); // Hauteur d'une cellule
        int maxRows = 10;
        tableView.prefHeightProperty().bind(
            Bindings.min(
                tableView.fixedCellSizeProperty().multiply(Bindings.size(tableView.getItems()).add(1.01)),
                tableView.fixedCellSizeProperty().multiply(maxRows + 1.01)
            )
        );
        tableView.minHeightProperty().bind(tableView.prefHeightProperty());
        tableView.maxHeightProperty().bind(tableView.prefHeightProperty());
        descriptionLabel.maxWidthProperty().bind(
                tableView.widthProperty().subtract(20) // Réduction de 20px pour une marge
            );

            descriptionLabel.setWrapText(true); // Activer le wrapping pour gérer les textes longs
        
    }


    private void adjustColumnWidths() {
        // Appliquer une politique de redimensionnement uniforme
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Ajuster la largeur relative de chaque colonne
        testNameColumn.setMaxWidth(1f * Integer.MAX_VALUE * 20); // 20% de la largeur totale
        testResultsColumn.setMaxWidth(1f * Integer.MAX_VALUE * 15); // 15%
        testDateColumn.setMaxWidth(1f * Integer.MAX_VALUE * 20); // 20%
        statusColumn.setMaxWidth(1f * Integer.MAX_VALUE * 15); // 15%
        doctorColumn.setMaxWidth(1f * Integer.MAX_VALUE * 20); // 20%
        prescriptionColumn.setMaxWidth(1f * Integer.MAX_VALUE * 10); // 10%
    }

    private void adjustTableWidthOnWindowResize() {
        // Récupération de la scène principale
        tableView.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.widthProperty().addListener((obs, oldWidth, newWidth) -> {
                    double windowWidth = newWidth.doubleValue();
                    double margin = 81 * 2; // 81px à gauche et à droite
                    tableView.setPrefWidth(windowWidth - margin);
                });
            }
        });
    }
    private void loadDataFromDatabase(int patientId) {
        String query = """
            SELECT 
                Test.Name AS TestName,
                TestResult.ResultValue AS TestResults,
                TestResult.ResultDate AS TestDate,
                ordonnancetest.Status,
                CONCAT(Doctor.nom, ' ', Doctor.prenom) AS DoctorName
            FROM 
                TestResult
            JOIN 
                Test ON TestResult.IDTest = Test.IDTest
            JOIN 
                Doctor ON TestResult.IDDoctor = Doctor.IDdoctor
            JOIN 
                Patient ON TestResult.IDPatient = Patient.IDPatient
			join
				ordonnancetest on ordonnancetest.idpatient=Patient.IDPatient
            WHERE 
                Patient.IDPatient = ?;
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, patientId); // Remplacez par l'ID du patient que vous souhaitez afficher
            ResultSet rs = pstmt.executeQuery();

            // Effacer les données actuelles avant d'ajouter de nouvelles données
            dataList.clear();

            // Parcourir les résultats et les ajouter à la liste
            while (rs.next()) {
                String testName = rs.getString("TestName");
                String testResults = rs.getString("TestResults");
                LocalDate testDate = rs.getDate("TestDate").toLocalDate();
                String status = rs.getString("Status");
                String doctorName = rs.getString("DoctorName");
                Button prescriptionButton = new Button();
                
                

                Image icon=new Image("file:../../images/Download02.jpg");
                ImageView imageView = new ImageView(icon);
                imageView.setFitWidth(20);
                imageView.setFitHeight(20);
                prescriptionButton.setGraphic(imageView);
                prescriptionButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                dataList.add(new TestRecord(testName, testResults, testDate, status, doctorName,prescriptionButton));
            }
            if (dataList.isEmpty()) {
                System.out.println("Aucun résultat trouvé pour le patient ID: " + patientId);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des données : " + e.getMessage());
            e.printStackTrace();
        }
    }

}
