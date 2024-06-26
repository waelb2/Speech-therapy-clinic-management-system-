package Controllers.patientsControllers;

import Models.DossierPatient.DossierPatientSchema;
import Models.Ortophoniste.OrtophonisteSchema;
import Models.patient.PatientSchema;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class DossiersPatientsController implements HelloController.InitializeData {
    private OrtophonisteSchema orthophoniste;
    @FXML
    private Label orthoField;
    @FXML
    private ListView<PatientSchema> patientsList ;
    private String orthoNom;
    private String orthoPrenom;
    private final ObservableList<PatientSchema> patients = FXCollections.observableArrayList();
    private boolean isInitialized = false;

    @FXML
    @Override
    public void initialize() {
        if (isInitialized) return; // Skip if already initialized

        this.orthophoniste = HelloApplication.currentUser;
        this.orthoNom = orthophoniste.getNom();
        this.orthoPrenom = orthophoniste.getPrenom();
        orthoField.setText(" " + orthoNom + " " + orthoPrenom);

        // Add patients to the list
        patients.addAll(HelloApplication.patientModel.getAllPatients());
        // Set custom cell factory
        patientsList.setCellFactory(new Callback<ListView<PatientSchema>, ListCell<PatientSchema>>() {
            @Override
            public ListCell<PatientSchema> call(ListView<PatientSchema> listView) {
                return new ListCell<PatientSchema>() {

                    @Override
                    protected void updateItem(PatientSchema patient, boolean empty) {
                        super.updateItem(patient, empty);
                        if (empty || patient == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            String ortho = HelloApplication.currentUser.getEmail();

                            DossierPatientSchema dossierPatient = DossierPatientSchema.loadDossierPatient(ortho, patient.getNom(), patient.getPrenom());
                            int numDossier = dossierPatient.getId();

                            HBox hBox = new HBox(10);
                            Label numLabel = new Label(String.valueOf(numDossier));
                            Label lastNameLabel = new Label(patient.getNom());
                            Label firstNameLabel = new Label(patient.getPrenom());
                            Button btn = new Button("Consulter");

                            numLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-pref-width: 65.6; -fx-pref-height: 34.4; -fx-alignment:center ");
                            lastNameLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-pref-width: 146.4; -fx-pref-height: 36; -fx-alignment: center;");
                            firstNameLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-pref-width: 170.4; -fx-pref-height: 36; -fx-alignment: center;");
                            hBox.setStyle(" -fx-pref-height: 36; ; -fx-background-color: #fff;   -fx-padding: 5;");
                            btn.setStyle("-fx-background-color: #1588ea; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight:bold; -fx-pref-width: 100; -fx-pref-height: 36;  -fx-alignment: center; -fx-cursor:hand;");

                            HBox btnBox = new HBox();
                            btnBox.getChildren().add(btn);
                            btnBox.setMargin(btn, new Insets(0, 0, 0, 45));

                            patientsList.setSelectionModel(null);

                            btn.setOnAction(event -> {
                                try {
                                    // redirect to dossier patient

                                    DossierPatientSchema.redirectToDossierPatient(event ,dossierPatient, patient.getNom(), patient.getPrenom(), "dossier.fxml", "Dossier Patient");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                            hBox.getChildren().addAll(numLabel, lastNameLabel, firstNameLabel, btn);
                            setGraphic(hBox);
                        }
                    }
                };
            }
        });

        patientsList.setItems(patients);
        // Set the flag to true after initialization
        isInitialized = true;
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
}
