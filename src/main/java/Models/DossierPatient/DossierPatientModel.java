package Models.DossierPatient;

import Databases.DossierPatientDB;
import Models.BilanOrthophonique.BilanOrthophoniqueSchema;
import Models.FicheDeSuivi.FicheDeSuiviSchema;
import Models.RendezVous.RendezVousSchema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class DossierPatientModel implements DossierPatientDB {
    private TreeMap<Integer, DossierPatientSchema> dossiers = new TreeMap<>();

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
}
