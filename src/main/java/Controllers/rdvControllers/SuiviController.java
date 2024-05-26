package Controllers.rdvControllers;

import Exceptions.AllInputsShouldBeProvidedException;
import Models.DossierPatient.DossierPatientSchema;
import Models.RendezVous.SuiviSchema;
import Models.RendezVous.TypeSuivi;
import Utils.Popups;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SuiviController implements HelloController.InitializeDataWithObject {
    @FXML
    private Label orthoField;
    private String orthoNom;
    private String orthoPrenom;

    @FXML
    private Label label_nom;
    @FXML
    private Label label_prenom;
    @FXML
    private Label label_age;
    @FXML
    private Label label_date;
    @FXML
    private Label label_hour;
    @FXML
    private Label label_min;

    @FXML
    private Button btn_creer;
    @FXML
    private Button createRdv;

    @FXML
    private Button dossiersPatients;
    @FXML
    private TextField tf_numDossier;

    @FXML
    private ComboBox<TypeSuivi> suiviPicker;

    @Override
    public void initializeWithData(HelloController.ConsultationObject consultationObject) {
        this.orthoNom = HelloApplication.currentUser.getNom();
        this.orthoPrenom = HelloApplication.currentUser.getPrenom();
        orthoField.setText(" " + orthoNom + " " + orthoPrenom);
        this.label_nom.setText(consultationObject.getNom());
        this.label_prenom.setText(consultationObject.getPrenom());
        this.label_age.setText(consultationObject.getAge());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String formattedStringDate = consultationObject.getDate().format(formatter);
        this.label_date.setText(formattedStringDate);

        this.label_hour.setText(consultationObject.getHeure());
        this.label_min.setText(consultationObject.getMin());

        ObservableList<TypeSuivi> options = FXCollections.observableArrayList(TypeSuivi.values());
        suiviPicker.setItems(options);
    }

    public void handlePatientsClick(ActionEvent event) {
        try {
            HelloController.redirectPage(event, "dossiersPatients.fxml", "Mes patients");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void handleAddRdvClick(ActionEvent event) {
        try {
            HelloController.redirectPage(event, "rdv.fxml", "Créer rendez-vous");
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

    public void handleCreateButton(ActionEvent event) throws Exception {
        try {
            // Validate inputs
            if (tf_numDossier.getText().isEmpty() || suiviPicker.getValue() == null) {
                throw new AllInputsShouldBeProvidedException();
            }

            int numDossier = Integer.parseInt(tf_numDossier.getText());
            TypeSuivi typeSuivi = suiviPicker.getValue();
            String nom = label_nom.getText();
            String prenom = label_prenom.getText();
            int age = Integer.parseInt(label_age.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            LocalDate dateRdv = LocalDate.parse(label_date.getText(), formatter);
            String heure = label_hour.getText() + ":" + label_min.getText();
            String duree = "30";  // Assuming a default duration
            String observation = "";

            // Create new Suivi
            SuiviSchema newSuivi = new SuiviSchema(dateRdv, heure, duree, numDossier, typeSuivi, observation);

            // Add Suivi to the rdv  model
            HelloApplication.rendezVousModel.createRendezVous(newSuivi);
            HelloApplication.rendezVousModel.saveRendezVous();

            //Add suivi to dossierPatient
            DossierPatientSchema dossierPatient = HelloApplication.dossierPatientModel.findDossierPatient(numDossier);
            dossierPatient.addRdv(newSuivi);

            HelloApplication.dossierPatientModel.updateDossierPatient(dossierPatient);
            HelloApplication.dossierPatientModel.saveDossierPatient();

            DossierPatientSchema.saveDossierPatient(dossierPatient,HelloApplication.currentUser.getEmail(), nom, prenom);

            Popups.showSuccessMessage("Created", "Suivi ajouté avec succès");

            // Redirect to dashboard
            HelloController.redirectPage(event, "dashboard.fxml", "Dashboard");
        } catch (AllInputsShouldBeProvidedException e) {
            Popups.showErrorMessage("Error", e.getMessage());
        }
    }
}
