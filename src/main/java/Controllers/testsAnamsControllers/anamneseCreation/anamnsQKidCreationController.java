package Controllers.testsAnamsControllers.anamneseCreation;

import Controllers.testsAnamsControllers.testCreationControllers.QCLController;
import Controllers.testsAnamsControllers.testCreationControllers.QCMController;
import Controllers.testsAnamsControllers.testCreationControllers.QCUController;
import Exceptions.AllInputsShouldBeProvidedException;
import Models.Anamnese.AnamneseSchema;
import Models.Anamnese.QAAdult;
import Models.Anamnese.QAChild;
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

public class anamnsQKidCreationController implements HelloController.InitializeData {
    private OrtophonisteSchema orthophoniste;
    private ArrayList<TestSchema> mesTests;
    private ArrayList<AnamneseSchema> mesAnamneses;
    @FXML
    Label orthoField;
    private String orthoNom;
    private String orthoPrenom;
    @FXML
    private ToggleGroup questionAnamType;
    @FXML
    private TextField tf_intitule;
    private ArrayList<QAAdult> questions;

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
            QCMController controller = fxmlLoader.getController();
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

            if (questionType.equals("QCM")) {
                QCMController controller = fxmlLoader.getController();
                controller.loadQuestion(question);
            } else if (questionType.equals("QCU")) {
                QCUController controller = fxmlLoader.getController();
                controller.loadQuestion(question);
            } else if (questionType.equals("Libre")) {
                QCLController controller = fxmlLoader.getController();
                controller.loadQuestion(question);
            }

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


    public void addQuestion(ActionEvent event) throws Exception {
        try {
            RadioButton selectedRadioButton = (RadioButton) questionAnamType.getSelectedToggle();

            if (selectedRadioButton == null || tf_intitule == null) throw new AllInputsShouldBeProvidedException();

            String selectedQstType = selectedRadioButton.getText();

            openPopup(event, "QAnamPopUp.fxml", "Ajouter une question");


        } catch (
                AllInputsShouldBeProvidedException e) {
            Popups.showErrorMessage("Error", e.getMessage());
            System.out.println(e.getMessage());
        }

    }

    public void handleSaveTest(ActionEvent event) throws IOException {
        String nom = tf_intitule.getText();
        TestQstSchema test = new TestQstSchema(nom);
        System.out.println(HelloApplication.testModel.createTestQst(test).getNom());

        HelloApplication.testModel.saveTests();
    }

}