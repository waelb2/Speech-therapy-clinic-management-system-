package Models.DossierPatient;

import Databases.DossierPatientDB;
import Models.BilanOrthophonique.BilanOrthophoniqueSchema;
import Models.FicheDeSuivi.FicheDeSuiviSchema;
import Models.RendezVous.RendezVousSchema;
import com.example.tp_poo.HelloApplication;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class DossierPatientModel implements DossierPatientDB {
    private TreeMap<Integer, DossierPatientSchema> dossiers = new TreeMap<>();

    public DossierPatientModel(TreeMap<Integer, DossierPatientSchema> dossiers) {
        this.dossiers = dossiers;
    }
    public DossierPatientSchema createDossierPatient(int id, TreeSet<RendezVousSchema> rendezVousPatient, ArrayList<BilanOrthophoniqueSchema> bO, ArrayList<FicheDeSuiviSchema> fichesDesSuivis ){
        DossierPatientSchema dossier = new DossierPatientSchema(id, rendezVousPatient, bO, fichesDesSuivis);
        dossiers.put(id, dossier);
        return dossier;
    }
    public DossierPatientSchema createDossierPatient(DossierPatientSchema dossierPatient){
        dossiers.put(dossierPatient.getId(), dossierPatient);
        return dossierPatient;
    }
    public DossierPatientSchema findDossierPatient(int id){
        return dossiers.get(id);
    }
    public DossierPatientSchema deleteDossierPatient(int id ){
        if(!dossiers.containsKey(id)){
            return  null;
        }
        return dossiers.remove(id);
    }

    @Override
    public boolean DossierPatientExist(int id) {
        return dossiers.containsKey(id);
    }

    @Override
    public DossierPatientSchema updateDossierPatient(DossierPatientSchema dossierPatient) {
        if(!dossiers.containsKey(dossierPatient.getId())){
            return null; // needs an exception here
        }
        dossiers.put((dossierPatient.getId()), dossierPatient);
        return dossierPatient;
    }
    public void saveDossierPatient(){
        // save dossiersPatients to patients.dat
        try (ObjectOutputStream objectOutputStream= new ObjectOutputStream(new FileOutputStream(HelloApplication.currentUserDir + "/MesPatients/dossiersPatients.dat"))) {
            objectOutputStream.writeObject(dossiers);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public  void loadDossierPatient(){
        // Check if the file exists
        File file = new File(HelloApplication.currentUserDir + "/MesPatients/dossiersPatients.dat");
        if (file.exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                dossiers = (TreeMap<Integer, DossierPatientSchema>) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                // Handle exceptions
                System.out.println(e.getMessage());
            }
        } else {
            // File doesn't exist, initialize rendez-vous as an empty TreeMap
            dossiers = new TreeMap<>();
        }
    }
}
