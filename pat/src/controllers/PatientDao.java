package controllers;

import models.Insurance;
import models.Patient;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public interface PatientDao {
    List<Patient> getAllPatients() throws SQLException;
    Patient getPatientById(int id) throws SQLException;
    boolean updatePatient(Patient patient) throws SQLException;
    List<Insurance> getAllInsurances();
    boolean insertPatient(Patient patient) throws SQLException;
    Patient verifyCredentials(String cin, String password) throws SQLException;
}

class PatientDAOImpl implements PatientDao {
    @Override
    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patient";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Patient patient = new Patient(
                    resultSet.getInt("IDPatient"),
                    resultSet.getString("Nom"),
                    resultSet.getString("Prenom"),
                    resultSet.getString("Sexe"),
                    resultSet.getTimestamp("BirthDate").toLocalDateTime(),
                    resultSet.getString("Adresse"),
                    resultSet.getString("Tel"),
                    resultSet.getInt("IDInsurance"),
                    resultSet.getString("CIN"),
                    resultSet.getString("Ville"),
                    resultSet.getString("password")
                );
                patients.add(patient);
            }
        }
        return patients;
    }

    @Override
    public Patient getPatientById(int id) throws SQLException {
        String query = "SELECT * FROM patient WHERE IDPatient = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Patient(
                        resultSet.getInt("IDPatient"),
                        resultSet.getString("Nom"),
                        resultSet.getString("Prenom"),
                        resultSet.getString("Sexe"),
                        resultSet.getTimestamp("BirthDate").toLocalDateTime(),
                        resultSet.getString("Adresse"),
                        resultSet.getString("Tel"),
                        resultSet.getInt("IDInsurance"),
                        resultSet.getString("CIN"),
                        resultSet.getString("Ville"),
                        resultSet.getString("password")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public boolean updatePatient(Patient patient) throws SQLException {
        String query = "UPDATE patient SET Nom = ?, Prenom = ?, Sexe = ?, BirthDate = ?, Adresse = ?, Tel = ?, IDInsurance = ?, CIN = ?, Ville = ?, password = ? WHERE IDPatient = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, patient.getNom());
            preparedStatement.setString(2, patient.getPrenom());
            preparedStatement.setString(3, patient.getSexe());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(patient.getBirthDate()));
            preparedStatement.setString(5, patient.getAdresse());
            preparedStatement.setString(6, patient.getTel());
            preparedStatement.setInt(7, patient.getIdInsurance());
            preparedStatement.setString(8, patient.getCin());
            preparedStatement.setString(9, patient.getVille());
            preparedStatement.setString(10, patient.getPassword());
            preparedStatement.setInt(11, patient.getIdPatient());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public List<Insurance> getAllInsurances() {
        List<Insurance> insurances = new ArrayList<>();
        String query = "SELECT * FROM insurance";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int idInsurance = rs.getInt("IDInsurance");
                String nameInsurance = rs.getString("NameInsurance");
                double percentage = rs.getDouble("Pourcentage");
                insurances.add(new Insurance(idInsurance, nameInsurance, percentage));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insurances;
    }

    @Override
    public boolean insertPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patient (Nom, Prenom, Sexe, BirthDate, Adresse, Tel, IDInsurance, CIN, Ville, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            if (conn == null) {
                System.err.println("Error: Could not establish database connection");
                return false;
            }
            
            System.out.println("Preparing to insert patient with CIN: " + patient.getCin());
            pstmt.setString(1, patient.getNom());
            pstmt.setString(2, patient.getPrenom());
            pstmt.setString(3, patient.getSexe());
            pstmt.setTimestamp(4, Timestamp.valueOf(patient.getBirthDate()));
            pstmt.setString(5, patient.getAdresse());
            pstmt.setString(6, patient.getTel());
            pstmt.setInt(7, patient.getIdInsurance());
            pstmt.setString(8, patient.getCin());
            pstmt.setString(9, patient.getVille());
            pstmt.setString(10, patient.getPassword());
            
            System.out.println("Executing SQL: " + pstmt.toString());
            int result = pstmt.executeUpdate();
            System.out.println("Insert result: " + result + " rows affected");
            return result > 0;
        }
    }

    @Override
    public Patient verifyCredentials(String cin, String password) throws SQLException {
        String sql = "SELECT * FROM patient WHERE CIN = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            if (conn == null) {
                System.err.println("Error: Could not establish database connection");
                return null;
            }
            
            pstmt.setString(1, cin);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Patient patient = new Patient();
                    patient.setIdPatient(rs.getInt("idPatient"));
                    patient.setNom(rs.getString("Nom"));
                    patient.setPrenom(rs.getString("Prenom"));
                    patient.setSexe(rs.getString("Sexe"));
                    patient.setBirthDate(rs.getTimestamp("BirthDate").toLocalDateTime());
                    patient.setAdresse(rs.getString("Adresse"));
                    patient.setTel(rs.getString("Tel"));
                    patient.setIdInsurance(rs.getInt("IDInsurance"));
                    patient.setCin(rs.getString("CIN"));
                    patient.setVille(rs.getString("Ville"));
                    patient.setPassword(rs.getString("password"));
                    return patient;
                }
            }
        }
        return null;
    }
}


//the previeouss 2 codes:





// 
// package controllers;
// 
// import models.Insurance;//hada
// import models.Patient;
// 
// import java.sql.SQLException;//hada
// import java.util.List;//hada
// 
// public interface PatientDao {
//     List<Patient> getAllPatients() throws SQLException;
//     Patient getPatientById(int id) throws SQLException;
//     boolean updatePatient(Patient patient) throws SQLException;
//     List<Insurance> getAllInsurances();
// }













//package controllers;
//
//import models.Patient;
//import java.sql.*;
//import java.time.LocalDateTime;
//
//public class PatientDAO {
//    
//    public boolean insertPatient(Patient patient) {
//        String sql = "INSERT INTO patient (Nom, Prenom, Sexe, BirthDate, Adresse, Tel, IDInsurance, CIN, Ville, password) " +
//                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            
//            if (conn == null) {
//                System.err.println("Error: Could not establish database connection");
//                return false;
//            }
//            
//            System.out.println("Preparing to insert patient with CIN: " + patient.getCin());
//            pstmt.setString(1, patient.getNom());
//            pstmt.setString(2, patient.getPrenom());
//            pstmt.setString(3, patient.getSexe());
//            pstmt.setTimestamp(4, Timestamp.valueOf(patient.getBirthDate()));
//            pstmt.setString(5, patient.getAdresse());
//            pstmt.setString(6, patient.getTel());
//            pstmt.setInt(7, patient.getIdInsurance());
//            pstmt.setString(8, patient.getCin());
//            pstmt.setString(9, patient.getVille());
//            pstmt.setString(10, patient.getPassword());
//            
//            System.out.println("Executing SQL: " + pstmt.toString());
//            int result = pstmt.executeUpdate();
//            System.out.println("Insert result: " + result + " rows affected");
//            return result > 0;
//            
//        } catch (SQLException e) {
//            System.err.println("Error inserting patient: " + e.getMessage());
//            System.err.println("SQL State: " + e.getSQLState());
//            System.err.println("Error Code: " + e.getErrorCode());
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public Patient verifyCredentials(String cin, String password) {
//        String sql = "SELECT * FROM patient WHERE CIN = ? AND password = ?";
//        
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            
//            if (conn == null) {
//                System.err.println("Error: Could not establish database connection");
//                return null;
//            }
//            
//            pstmt.setString(1, cin);
//            pstmt.setString(2, password);
//            
//            try (ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    Patient patient = new Patient();
//                    patient.setIdPatient(rs.getInt("idPatient"));
//                    patient.setNom(rs.getString("Nom"));
//                    patient.setPrenom(rs.getString("Prenom"));
//                    patient.setSexe(rs.getString("Sexe"));
//                    patient.setBirthDate(rs.getTimestamp("BirthDate").toLocalDateTime());
//                    patient.setAdresse(rs.getString("Adresse"));
//                    patient.setTel(rs.getString("Tel"));
//                    patient.setIdInsurance(rs.getInt("IDInsurance"));
//                    patient.setCin(rs.getString("CIN"));
//                    patient.setVille(rs.getString("Ville"));
//                    return patient;
//                }
//            }
//        } catch (SQLException e) {
//            System.err.println("Error verifying credentials: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
//