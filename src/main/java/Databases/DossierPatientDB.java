package Databases;

import Models.BilanOrthophonique.BilanOrthophoniqueSchema;
import Models.DossierPatient.DossierPatientSchema;
import Models.FicheDeSuivi.FicheDeSuiviSchema;
import Models.RendezVous.RendezVousSchema;

import java.util.ArrayList;
import java.util.TreeSet;

public interface DossierPatientDB {
    public DossierPatientSchema createDossierPatient(int id, TreeSet<RendezVousSchema> rendezVousPatient, ArrayList<BilanOrthophoniqueSchema> bO, ArrayList<FicheDeSuiviSchema> fichesDesSuivis );
    public DossierPatientSchema createDossierPatient(DossierPatientSchema dossierPatient);
    public DossierPatientSchema findDossierPatient(int id);
    public DossierPatientSchema deleteDossierPatient(int id);
    public boolean DossierPatientExist(int id);
    public DossierPatientSchema updateDossierPatient(DossierPatientSchema dossierPatient);
}
