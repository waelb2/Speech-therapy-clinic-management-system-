package Controllers.rdvControllers;

import Models.DossierPatient.DossierPatientSchema;
import Models.FicheDeSuivi.FicheDeSuiviSchema;
import Models.Objectif.ObjectifSchema;
import Models.Ortophoniste.OrtophonisteSchema;
import Utils.Popups;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;

public class AjouterObservationController implements  HelloController.InitializeData {
    private OrtophonisteSchema orthophoniste;
    @FXML
    private Label orthoField;
    @FXML
    private Label numDossier;
    @FXML
    private ListView objectifsList;
    private String orthoNom;
    private String orthoPrenom;
    private String nomPatient ;
    private String prenomPatient;
    private final ObservableList<ObjectifSchema> objectifs = FXCollections.observableArrayList();
    private boolean isInitialized = false;
    private DossierPatientSchema updatedDossier;
    private FicheDeSuiviSchema updatedFiche;
    @FXML
    @Override
    public void initialize () {
        if (isInitialized) return; // Skip if already initialized

        this.orthophoniste = HelloApplication.currentUser;
        this.orthoNom = orthophoniste.getNom();
        this.orthoPrenom = orthophoniste.getPrenom();
        orthoField.setText(" " + orthoNom + " " + orthoPrenom);

    }

    // Event handler methods
    public void handlePatientsClick(ActionEvent event) {
        try {
            HelloController.redirectPage(event, "dossiersPatients.fxml", "Mes patients");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleAddRdvClick(ActionEvent event) {
        try {
            HelloController.redirectPage(event, "rdv.fxml", "Créer rendez-vous");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleStatsClick(ActionEvent event) {
        try {
            HelloController.redirectPage(event, "stats.fxml", "Mes Statistiques");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleSettingsClick(ActionEvent event) {
        try {
            HelloController.redirectPage(event, "parametres.fxml", "Mes paramètres");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleLogoClick(MouseEvent event) {
        try {
            HelloController.redirectPage(event, "dashboard.fxml", "Dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleTestsClick(ActionEvent event) {
        try {
            HelloController.redirectPage(event, "testAnam.fxml", "Testes et Anamnèses");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleBoClick(ActionEvent event) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void handleFicheSuiviClick(ActionEvent event){
        try {
//            DossierPatientSchema.redirectToDossierPatient(event,updatedDossier,this.nomPatient.getText(), this.prenomPatient.getText(),"fichesSuivi.fxml", "Fiche de suivi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleNextButton(ActionEvent event ) throws IOException {

        Popups.showSuccessMessage("", "Rendez-vous passé avec succès");
        HelloController.redirectPage(event,"dashboard.fxml", "Dashboard");
    }

}
