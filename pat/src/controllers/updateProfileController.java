package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Insurance;
import models.Patient;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class updateProfileController {
    @FXML private TextField prenomInput;
    @FXML private TextField nomInput;
    @FXML private TextField cinInput;
    @FXML private TextField telephoneInput;
    @FXML private TextField adresseInput;
    @FXML private ComboBox<String> villeCombo;
    @FXML private DatePicker dateNaissancePicker;
    @FXML private ComboBox<String> sexeCombo;
    @FXML private ComboBox<Insurance> assuranceCombo;
    @FXML private Label messageLabel;
    @FXML private Button saveUpdateButton;

    private PatientDao patientDao;
    private Patient currentPatient;
    private List<String> cities = Arrays.asList(
        "Casablanca", "Rabat", "Fes", "Marrakesh", "Tangier", "Salé", "Meknes",
        "Agadir", "Oujda", "Kenitra", "Tetouan", "Temara", "Safi", "Mohammedia",
        "Khouribga", "Beni Mellal", "El Jadida", "Taza", "Nador", "Settat",
        "Larache", "Ksar El Kebir", "Khemisset", "Guelmim", "Berrechid", "Taourirt",
        "Berkane", "Sidi Slimane", "Errachidia", "Sidi Kacem", "Khenifra",
        "Essaouira", "Tiznit", "Taroudant", "Ouarzazate", "Al Hoceima"
    );

    public updateProfileController() {
        this.patientDao = new PatientDAOImpl();
    }

    @FXML
    private void initialize() {
        try {
            // Initialize sex options
            sexeCombo.getItems().addAll("M", "F");
            
            // Initialize city options
            villeCombo.getItems().addAll(cities);
            
            // Load insurance options
            List<Insurance> insurances = patientDao.getAllInsurances();
            assuranceCombo.getItems().addAll(insurances);
            
            // Set cell factory for insurance combo box
            assuranceCombo.setCellFactory(param -> new ListCell<Insurance>() {
                @Override
                protected void updateItem(Insurance item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getNameInsurance() + " (" + item.getPercentage() + "%)");
                    }
                }
            });
            
            // Set button cell factory
            assuranceCombo.setButtonCell(new ListCell<Insurance>() {
                @Override
                protected void updateItem(Insurance item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getNameInsurance() + " (" + item.getPercentage() + "%)");
                    }
                }
            });
        } catch (Exception e) {
            if (messageLabel != null) {
                messageLabel.setText("Error initializing form: " + e.getMessage());
                messageLabel.setStyle("-fx-text-fill: red;");
            }
            e.printStackTrace();
        }
    }

    public void initData(Patient patient) {
        this.currentPatient = patient;
        
        try {
            // Populate fields with patient data
            nomInput.setText(patient.getNom());
            prenomInput.setText(patient.getPrenom());
            cinInput.setText(patient.getCin());
            telephoneInput.setText(patient.getTel());
            adresseInput.setText(patient.getAdresse());
            villeCombo.setValue(patient.getVille());
            
            // Set birthdate
            dateNaissancePicker.setValue(patient.getBirthDate().toLocalDate());
            
            // Set sex
            sexeCombo.setValue(patient.getSexe());
            
            // Set insurance
            for (Insurance insurance : assuranceCombo.getItems()) {
                if (insurance.getIdInsurance() == patient.getIdInsurance()) {
                    assuranceCombo.setValue(insurance);
                    break;
                }
            }
        } catch (Exception e) {
            if (messageLabel != null) {
                messageLabel.setText("Error loading patient data: " + e.getMessage());
                messageLabel.setStyle("-fx-text-fill: red;");
            }
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSaveUpdateButton(ActionEvent event) {
        if (!validateInputs()) {
            return;
        }

        try {
            updatePatientData();
            if (patientDao.updatePatient(currentPatient)) {
                messageLabel.setText("Profile updated successfully!");
                messageLabel.setStyle("-fx-text-fill: green;");
            } else {
                messageLabel.setText("Failed to update profile");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (SQLException e) {
            messageLabel.setText("Error updating profile: " + e.getMessage());
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    private boolean validateInputs() {
        if (nomInput.getText().isEmpty() || prenomInput.getText().isEmpty() || 
            cinInput.getText().isEmpty() || telephoneInput.getText().isEmpty() || 
            adresseInput.getText().isEmpty() || villeCombo.getValue() == null || 
            dateNaissancePicker.getValue() == null || sexeCombo.getValue() == null || 
            assuranceCombo.getValue() == null) {
            
            messageLabel.setText("Please fill in all fields");
            messageLabel.setStyle("-fx-text-fill: red;");
            return false;
        }
        return true;
    }

    private void updatePatientData() {
        currentPatient.setNom(nomInput.getText());
        currentPatient.setPrenom(prenomInput.getText());
        currentPatient.setCin(cinInput.getText());
        currentPatient.setTel(telephoneInput.getText());
        currentPatient.setAdresse(adresseInput.getText());
        currentPatient.setVille(villeCombo.getValue());
        currentPatient.setBirthDate(dateNaissancePicker.getValue().atStartOfDay());
        currentPatient.setSexe(sexeCombo.getValue());
        currentPatient.setIdInsurance(assuranceCombo.getValue().getIdInsurance());
    }
}
