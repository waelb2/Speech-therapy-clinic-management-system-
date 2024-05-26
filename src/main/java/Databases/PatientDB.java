package Databases;

import Models.patient.AdultSchema;
import Models.patient.PatientSchema;
import java.time.LocalDate;

public interface PatientDB {
    public AdultSchema createAdult(String nom, String prenom, LocalDate dateDeNaissance,String lieuDeNaissance, String adresse, String diplome, String profession);

    public AdultSchema createAdult(AdultSchema adult);


    public AdultSchema deleteAdult(String nom, String prenom);

    public boolean AdultExist(String nom, String prenom);

    public AdultSchema updateAdult(AdultSchema adult);

    public PatientSchema createEnfant(String nom, String prenom, LocalDate dateDeNaissance,String lieuDeNaissance, String adresse, String classeEtude, String[] numParents);

    public PatientSchema createEnfant(PatientSchema enfant);
    public PatientSchema findPatient(String nom, String prenom);

    public PatientSchema deleteEnfant(String nom, String prenom);

    public boolean EnfantExist(String nom, String prenom);

    public PatientSchema updateEnfant(PatientSchema enfant);
    public void deletePatient(String nom, String prenom);
    public PatientSchema[]  getAllPatients();
    public int countPatients();
}
