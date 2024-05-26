package Models.DossierPatient;

import Models.BilanOrthophonique.BilanOrthophoniqueSchema;
import Models.FicheDeSuivi.FicheDeSuiviSchema;
import Models.RendezVous.RendezVousSchema;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.function.ToDoubleBiFunction;


public class DossierPatientSchema implements Serializable {
    private int id;

    private TreeSet<RendezVousSchema> rendezVousPatient; //TODO: definir un comparateur pour rendezVous afin de trier les rendez vous de chaque patient et ainsi que les rendezVous du medecin (le tri se fera temporellement)
    private ArrayList<BilanOrthophoniqueSchema> bO;
    private  ArrayList<FicheDeSuiviSchema> fichesDesSuivis;


    public DossierPatientSchema(int id, TreeSet<RendezVousSchema> rendezVousPatient, ArrayList<BilanOrthophoniqueSchema> bO, ArrayList<FicheDeSuiviSchema> fichesDesSuivis) {

        this.rendezVousPatient = rendezVousPatient;
        this.bO = bO;
        this.fichesDesSuivis = fichesDesSuivis;
        this.id  = id;
    }

    public DossierPatientSchema() {
    }



    public void addRdv(RendezVousSchema newRdv){
        this.rendezVousPatient.add(newRdv);
    }
    public void addBO(BilanOrthophoniqueSchema newBO){
        this.bO.add(newBO);
    }
    public void addFicheSuivi(FicheDeSuiviSchema newFicheSuivi){
        this.fichesDesSuivis.add(newFicheSuivi);
    }
    public TreeSet<RendezVousSchema> getRendezVousPatient() {
        return rendezVousPatient;
    }
    public ArrayList<BilanOrthophoniqueSchema> getbO() {
        return bO;
    }

    public ArrayList<FicheDeSuiviSchema> getFichesDesSuivis() {
        return fichesDesSuivis;
    }

    public void setRendezVousPatient(TreeSet<RendezVousSchema> rendezVousPatient) {
        this.rendezVousPatient = rendezVousPatient;
    }

    public void setbO(ArrayList<BilanOrthophoniqueSchema> bO) {
        this.bO = bO;
    }

    public void setFichesDesSuivis(ArrayList<FicheDeSuiviSchema> fichesDesSuivis) {
        this.fichesDesSuivis = fichesDesSuivis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public static DossierPatientSchema loadDossierPatient(String orthoEmail, String nomPatient, String prenomPatient) {
        String filePath = "./data/orthophonistes/" + orthoEmail + "/dossiersPatients/" + nomPatient + "_" + prenomPatient + ".dat";
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (DossierPatientSchema) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean saveDossierPatient(DossierPatientSchema dossier, String orthoEmail, String nomPatient, String prenomPatient) {
        String filePath = "./data/orthophonistes/" + orthoEmail + "/dossiersPatients/" + nomPatient + "_" + prenomPatient + ".dat";
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(dossier);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void  redirectToDossierPatient(Event event, DossierPatientSchema dossierPatient, String nom, String prenom, String fileName, String title) throws  IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fileName));
        Parent root = (Parent) fxmlLoader.load();


        Object controller= fxmlLoader.getController();
        if(controller instanceof HelloController.InitializeWithDossierPatient) {
            ((HelloController.InitializeWithDossierPatient) controller).initializeWithDossierPatient(dossierPatient, nom, prenom);
        }
        Stage stage =(Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();    }

}
