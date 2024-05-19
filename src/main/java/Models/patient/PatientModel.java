package Models.patient;
import Databases.PatientDB;
import com.example.tp_poo.HelloApplication;

import java.io.*;
import java.util.TreeMap;
import java.time.LocalDate;

public class PatientModel implements PatientDB {
    TreeMap<String, PatientSchema> patients= new TreeMap<>();

    public PatientModel(TreeMap<String, PatientSchema> patients) {
        this.patients = patients;
    }
    @Override
    public  AdultSchema createAdult(String nom, String prenom, LocalDate dateDeNaissance, String adresse, String diplome, String profession){
        AdultSchema adult = new AdultSchema(nom, prenom, dateDeNaissance, adresse, diplome, profession);
        patients.put(nom+"_"+prenom, adult);
        return adult;
    }
    @Override
    public AdultSchema createAdult(AdultSchema adult){
        patients.put(adult.getNom()+"_"+adult.getPrenom(), adult);
        return adult;
    }
    @Override
    public AdultSchema deleteAdult(String nom, String prenom){
        return (AdultSchema) patients.remove(nom+"_"+prenom);
    }
    @Override
    public boolean AdultExist(String nom, String prenom){
        return patients.containsKey(nom+"_"+prenom);
    }
    @Override
    public AdultSchema updateAdult(AdultSchema adult){
        return (AdultSchema) patients.put(adult.getNom()+"_"+adult.getPrenom(), adult);
    }
    @Override
    public PatientSchema createEnfant(String nom, String prenom, LocalDate dateDeNaissance, String adresse, String classeEtude, String[] numParents){
        EnfantSchema enfant = new EnfantSchema(nom, prenom, dateDeNaissance, adresse, classeEtude, numParents);
        patients.put(nom+"_"+prenom, enfant);
        return enfant;
    }
    @Override
    public PatientSchema createEnfant(PatientSchema enfant){
        patients.put(enfant.getNom()+"_"+enfant.getPrenom(), enfant);
        return enfant;
    }
    @Override
    public PatientSchema findPatient(String nom, String prenom){
        return patients.get(nom+"_"+prenom);
    }
    @Override
    public PatientSchema deleteEnfant(String nom, String prenom){
        return patients.remove(nom+"_"+prenom);
    }
    @Override
    public boolean EnfantExist(String nom, String prenom){
        return patients.containsKey(nom+"_"+prenom);
    }
    @Override
    public PatientSchema updateEnfant(PatientSchema enfant){
        return patients.put(enfant.getNom()+"_"+enfant.getPrenom(), enfant);
    }
    public void loadPatients() throws IOException, ClassNotFoundException {
        // Check if the file exists
        File file = new File(HelloApplication.currentUserDir + "/patients.dat");
        if (file.exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                patients = (TreeMap<String, PatientSchema>) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                // Handle exceptions
                System.out.println(e.getMessage());
            }
        } else {
            // File doesn't exist, initialize patients as an empty TreeMap
            patients = new TreeMap<>();
        }
    }


    public void savePatients() throws  IOException {
        // save patients to patients.dat
        try (ObjectOutputStream objectOutputStream= new ObjectOutputStream(new FileOutputStream(HelloApplication.currentUserDir + "/patients.dat"))) {
            objectOutputStream.writeObject(patients);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
