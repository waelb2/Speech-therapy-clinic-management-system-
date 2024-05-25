package Controllers.testsAnamsControllers.testCreationControllers;

import Exceptions.AllInputsShouldBeProvidedException;
import Models.Anamnese.AnamneseSchema;
import Models.Ortophoniste.OrtophonisteSchema;
import Models.Test.TestQstSchema;
import Models.Test.TestSchema;
import Utils.Popups;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ExoTestCreationController implements HelloController.InitializeData {
    private OrtophonisteSchema orthophoniste;
    private ArrayList<TestSchema> mesTests;
    private ArrayList<AnamneseSchema> mesAnamneses;
    @FXML
    Label orthoField;
    private String orthoNom;
    private String orthoPrenom;
    @FXML
    private ToggleGroup questionType;
    @FXML
    private TextField tf_intitule;


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

    public void handlePatientsClick(ActionEvent evet) {
        try {
            HelloController.redirectPage(evet, "dossiersPatients.fxml", "Mes patients");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void handleAddRdvClick(ActionEvent evet) {
        try {
            HelloController.redirectPage(evet, "rdv.fxml", "Créer rendez-vous");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void handleStatsClick(ActionEvent event) {
        try {
            HelloController.redirectPage(event, "stats.fxml", "Mes Statistiques");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void handleSettingsClick(ActionEvent event) {
        try {
            HelloController.redirectPage(event, "parametres.fxml", "Mes paramètres");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void handleLogoClick(MouseEvent event) {
        try {
            HelloController.redirectPage(event, "dashboard.fxml", "Dashboard");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void handleTestsClick(ActionEvent event) {
        try {
            HelloController.redirectPage(event, "testAnam.fxml", "Testes et Anamnèses");
        } catch (Exception e) {
            e.getMessage();
        }
    }


    private void openPopup(Event event, String fxmlPath, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openPopup(String fxmlPath, String title, String question, String questionType) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));
            Parent root = fxmlLoader.load();



            DialogPane dialogPane = new DialogPane();
            dialogPane.setContent(root);

            Dialog<String> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle(title);
            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addExercice(ActionEvent event) {
        try {
            if( tf_intitule == null) throw new AllInputsShouldBeProvidedException();
            openPopup(event, "addExoPopup.fxml", "Ajouter un QCL");
        } catch (AllInputsShouldBeProvidedException e) {
            Popups.showErrorMessage("Error", e.getMessage());
            System.out.println(e.getMessage());
        }
    }

}


