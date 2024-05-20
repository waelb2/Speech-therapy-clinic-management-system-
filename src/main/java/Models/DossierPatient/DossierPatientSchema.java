package Models.DossierPatient;

import Models.BilanOrthophonique.BilanOrthophoniqueSchema;
import Models.FicheDeSuivi.FicheDeSuiviSchema;
import Models.RendezVous.RendezVousSchema;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.function.ToDoubleBiFunction;

public class DossierPatientSchema {
    private TreeSet<RendezVousSchema> rendezVousPatient; //TODO: definir un comparateur pour rendezVous afin de trier les rendez vous de chaque patient et ainsi que les rendezVous du medecin (le tri se fera temporellement)
    private ArrayList<BilanOrthophoniqueSchema> bO;
    private  ArrayList<FicheDeSuiviSchema> fichesDesSuivis;

    public DossierPatientSchema(TreeSet<RendezVousSchema> rendezVousPatient, ArrayList<BilanOrthophoniqueSchema> bO, ArrayList<FicheDeSuiviSchema> fichesDesSuivis) {
        this.rendezVousPatient = rendezVousPatient;
        this.bO = bO;
        this.fichesDesSuivis = fichesDesSuivis;
    }

    public DossierPatientSchema() {
    }


}
