package models;

import java.time.LocalDateTime;

public class Patient {

    private int idPatient;
    private String nom;
    private String prenom;
    private String sexe;
    private LocalDateTime birthDate;
    private String adresse;
    private String tel;
    private int idInsurance;
    private String cin;
    private String ville;
    private String password;

    // Constructeurs
    public Patient() {}

    public Patient(int idPatient, String nom, String prenom, String sexe, 
                  LocalDateTime birthDate, String adresse, String tel, 
                  int idInsurance, String cin, String ville, String password) {
        this.idPatient = idPatient;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.birthDate = birthDate;
        this.adresse = adresse;
        this.tel = tel;
        this.idInsurance = idInsurance;
        this.cin = cin;
        this.ville = ville;
        this.password = password;
    }

  

    // Getters and Setters
    public int getIdPatient() { return idPatient; }
    public void setIdPatient(int idPatient) { this.idPatient = idPatient; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }

    public LocalDateTime getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDateTime birthDate) { this.birthDate = birthDate; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }

    public int getIdInsurance() { return idInsurance; }
    public void setIdInsurance(int idInsurance) { this.idInsurance = idInsurance; }

    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "Patient{" +
                "idPatient=" + idPatient +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", sexe='" + sexe + '\'' +
                ", birthDate=" + birthDate +
                ", adresse='" + adresse + '\'' +
                ", tel='" + tel + '\'' +
                ", idInsurance=" + idInsurance +
                ", cin='" + cin + '\'' +
                ", ville='" + ville + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
// package models;
// 
// import java.time.LocalDateTime;
// 
// public class Patient {
//     private int idPatient;
//     private String nom;
//     private String prenom;
//     private String sexe;
//     private LocalDateTime birthDate;
//     private String adresse;
//     private String tel;
//     private int idInsurance;
//     private String cin;
//     private String ville;
//     private String password;
// 
//     // Constructeur
//     public Patient() {}
// 
//     public Patient(int idPatient, String nom, String prenom, String sexe, 
//                   LocalDateTime birthDate, String adresse, String tel, 
//                   int idInsurance, String cin, String ville, String password) {
//         this.idPatient = idPatient;
//         this.nom = nom;
//         this.prenom = prenom;
//         this.sexe = sexe;
//         this.birthDate = birthDate;
//         this.adresse = adresse;
//         this.tel = tel;
//         this.idInsurance = idInsurance;
//         this.cin = cin;
//         this.ville = ville;
//         this.password = password;
//     }
// 
//     // Getters et Setters
//     public int getIdPatient() { return idPatient; }
//     public void setIdPatient(int idPatient) { this.idPatient = idPatient; }
// 
//     public String getNom() { return nom; }
//     public void setNom(String nom) { this.nom = nom; }
// 
//     public String getPrenom() { return prenom; }
//     public void setPrenom(String prenom) { this.prenom = prenom; }
// 
//     public String getSexe() { return sexe; }
//     public void setSexe(String sexe) { this.sexe = sexe; }
// 
//     public LocalDateTime getBirthDate() { return birthDate; }
//     public void setBirthDate(LocalDateTime birthDate) { this.birthDate = birthDate; }
// 
//     public String getAdresse() { return adresse; }
//     public void setAdresse(String adresse) { this.adresse = adresse; }
// 
//     public String getTel() { return tel; }
//     public void setTel(String tel) { this.tel = tel; }
// 
//     public int getIdInsurance() { return idInsurance; }
//     public void setIdInsurance(int idInsurance) { this.idInsurance = idInsurance; }
// 
//     public String getCin() { return cin; }
//     public void setCin(String cin) { this.cin = cin; }
// 
//     public String getVille() { return ville; }
//     public void setVille(String ville) { this.ville = ville; }
// 
//     public String getPassword() { return password; }
//     public void setPassword(String password) { this.password = password; }
// }
// 
// code1 ta3 salma wa9ila 
// package models;
// 
// public class Patient {
// 
// 	 private int idPatient;
// 	    private String nom;
// 	    private String prenom;
// 	    private String sexe;
// 	    private String birthDate; 
// 	    private String adresse;
// 	    private String tel;
// 	    private int idInsurance;
// 	    private String cin;
// 	    private String ville;
// 
// 	    public Patient(int idPatient, String nom, String prenom, String sexe, String birthDate, String adresse, String tel, int idInsurance, String cin, String ville) {
// 	        this.idPatient = idPatient;
// 	        this.nom = nom;
// 	        this.prenom = prenom;
// 	        this.sexe = sexe;
// 	        this.birthDate = birthDate;
// 	        this.adresse = adresse;
// 	        this.tel = tel;
// 	        this.idInsurance = idInsurance;
// 	        this.cin = cin;
// 	        this.ville = ville;
// 	    }
// 
// 	    public Patient() {}
// 
// 	    public int getIdPatient() {
// 	    	return idPatient;
// 	    	}
// 	    public void setIdPatient(int idPatient) { 
// 	    	this.idPatient = idPatient; 
// 	    	}
// 
// 	    public String getNom() {
// 	    	return nom;
// 	    	}
// 	    
// 	    public void setNom(String nom) {
// 	    	this.nom = nom; 
// 	    	}
// 
// 	    public String getPrenom() { 
// 	    	return prenom;
// 	    	}
// 	    public void setPrenom(String prenom) { 
// 	    	this.prenom = prenom;
// 	    	}
// 
// 	    public String getSexe() { 
// 	    	return sexe;
// 	    	}
// 	    
// 	    public void setSexe(String sexe) { 
// 	    	this.sexe = sexe;
// 	    	}
// 
// 	    public String getBirthDate() { 
// 	    	return birthDate;
// 	    	
// 	    }
// 	    public void setBirthDate(String birthDate) { 
// 	    	this.birthDate = birthDate;
// 	    	}
// 
// 	    public String getAdresse() { 
// 	    	return adresse; 
// 	    	}
// 	    public void setAdresse(String adresse) { 
// 	    	this.adresse = adresse;
// 	    	}
// 
// 	    public String getTel() { 
// 	    	return tel; 
// 	    	}
// 	    
// 	    
// 	    public void setTel(String tel) {
// 	    	this.tel = tel;
// 	    	}
// 
// 	    public int getIdInsurance() { 
// 	    	return idInsurance;
// 	    	}
// 	    
// 	    public void setIdInsurance(int idInsurance) {
// 	    	this.idInsurance = idInsurance; 
// 	    	}
// 
// 	    public String getCin() { 
// 	    	return cin; 
// 	    	}
// 	    
// 	    public void setCin(String cin) {
// 	    	this.cin = cin;
// 	    	}
// 
// 	    public String getVille() {
// 	    	return ville;
// 	    	
// 	    }
// 	    public void setVille(String ville) { 
// 	    	this.ville = ville;
// 	    	}
// 
// 	    @Override
// 	    public String toString() {
// 	        return "Patient{" +
// 	                "idPatient=" + idPatient +
// 	                ", nom='" + nom + '\'' +
// 	                ", prenom='" + prenom + '\'' +
// 	                ", sexe='" + sexe + '\'' +
// 	                ", birthDate='" + birthDate + '\'' +
// 	                ", adresse='" + adresse + '\'' +
// 	                ", tel='" + tel + '\'' +
// 	                ", idInsurance=" + idInsurance +
// 	                ", cin='" + cin + '\'' +
// 	                ", ville='" + ville + '\'' +
// 	                '}';
// 	    }
// 	}