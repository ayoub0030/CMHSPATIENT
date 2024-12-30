package controllers;

import models.Insurance;
import models.Patient;

import java.sql.SQLException;
import java.util.List;

public class PatientService {
    private final PatientDao patientDao;

    public PatientService() {
        this.patientDao = new PatientDaoImpl();
    }

    public List<Patient> getAllPatients() throws SQLException {
        return patientDao.getAllPatients();
    }

    public Patient getPatientById(int id) throws SQLException {
        return patientDao.getPatientById(id);
    }

    public boolean updatePatient(Patient patient) throws SQLException {
        return patientDao.updatePatient(patient);
    }
    
    public  List<Insurance> getAllInsurances() {
    	return patientDao.getAllInsurances();
    }
    
}
