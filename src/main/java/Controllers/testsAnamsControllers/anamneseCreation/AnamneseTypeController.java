package Controllers.testsAnamsControllers.anamneseCreation;

import Models.Anamnese.AnamneseSchema;
import Models.Ortophoniste.OrtophonisteSchema;
import Models.Test.TestSchema;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class AnamneseTypeController implements HelloController.InitializeData {

    private OrtophonisteSchema orthophoniste;
    private ArrayList<TestSchema> mesTests;
    private ArrayList<AnamneseSchema> mesAnamneses;
    @FXML
    Label orthoField;
    private String orthoNom;
    private String orthoPrenom;


    @FXML
    private Button adultCat;

    @FXML
    private Button createRdv;

    @FXML
    private Button dossiersPatients;

    @FXML
    private Button kidCat;

    @FXML
    private ImageView logoImage;



    @FXML
    private Button settings;

    @FXML
    private Button stats;

    @FXML
    private Button testAnam;

    @FXML

    @Override
    public void initialize() {
        this.orthophoniste = HelloApplication.currentUser;
        this.orthoNom = orthophoniste.getNom();
        this.orthoPrenom = orthophoniste.getPrenom();
        this.mesTests = orthophoniste.getMesTests();
        this.mesAnamneses = orthophoniste.getMesAnamneses();
        orthoField.setText(" " + orthoNom + " " + orthoPrenom);

    }
    @FXML
    public void handlePatientsClick(ActionEvent evet) {
        try {
            HelloController.redirectPage(evet, "dossiersPatients.fxml", "Mes patients");
        } catch (Exception e) {
            e.getMessage();
        }
    }
    @FXML
    public void handleAddRdvClick(ActionEvent evet) {
        try {
            HelloController.redirectPage(evet, "rdv.fxml", "Créer rendez-vous");
        } catch (Exception e) {
            e.getMessage();
        }
    }
    @FXML
    public void handleStatsClick(ActionEvent event) {
        try {
            HelloController.redirectPage(event, "stats.fxml", "Mes Statistiques");
        } catch (Exception e) {
            e.getMessage();
        }
    }
    @FXML
    public void handleSettingsClick(ActionEvent event) {
        try {
            HelloController.redirectPage(event, "parametres.fxml", "Mes paramètres");
        } catch (Exception e) {
            e.getMessage();
        }
    }
    @FXML
    public void handleLogoClick(MouseEvent event) {
        try {
            HelloController.redirectPage(event, "dashboard.fxml", "Dashboard");
        } catch (Exception e) {
            e.getMessage();
        }
    }
    @FXML
    public void handleTestsClick(ActionEvent event) {
        try {
            HelloController.redirectPage(event, "testAnam.fxml", "Testes et Anamnèses");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @FXML
    public void handleAdultAnamnese(ActionEvent event) {
        try {
            HelloController.redirectPage(event, "AdultAnamneseCreation.fxml", "adultCat");
        } catch (Exception e) {
            e.getMessage();
        }

    }

    @FXML
    public void handleKidAnamnese(ActionEvent event) {
        try {
            HelloController.redirectPage(event, "KidAnamneseCreation.fxml", "kidCat");
        } catch (Exception e) {
            e.getMessage();
        }

    }
}


