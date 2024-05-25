package Controllers.patientsControllers;

import Models.DossierPatient.DossierPatientSchema;
import Models.Ortophoniste.OrtophonisteSchema;
import Models.RendezVous.AtelierSchema;
import Models.RendezVous.ConsultationSchema;
import Models.RendezVous.RendezVousSchema;
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

public class DossierPatientController implements  HelloController.InitializeWithDossierPatient{
    private OrtophonisteSchema orthophoniste;
    @FXML
    private Label orthoField;
    @FXML
    private Label numDossier;
    @FXML
    private Label nomPatient;
    @FXML
    private Label prenomPatient;
    @FXML
    private ListView rdvsList;
    @FXML
    private ListView<PatientSchema> patientsList ;
    private String orthoNom;
    private String orthoPrenom;
    private final ObservableList<RendezVousSchema> rdvs= FXCollections.observableArrayList();
    private boolean isInitialized = false;

    @FXML
    @Override
    public void initializeWithDossierPatient (DossierPatientSchema dossierPatient, String nom, String prenom) {
        if (isInitialized) return; // Skip if already initialized

        this.orthophoniste = HelloApplication.currentUser;
        this.orthoNom = orthophoniste.getNom();
        this.orthoPrenom = orthophoniste.getPrenom();
        orthoField.setText(" " + orthoNom + " " + orthoPrenom);
        this.numDossier.setText(String.valueOf(dossierPatient.getId()));
        this.nomPatient.setText(nom);
        this.prenomPatient.setText(prenom);


        // Add rdvs to the list
        rdvs.addAll(dossierPatient.getRendezVousPatient());

        rdvsList.setCellFactory(new Callback<ListView<RendezVousSchema>, ListCell<RendezVousSchema>>() {
            @Override
            public ListCell<RendezVousSchema> call(ListView<RendezVousSchema> listView) {
                return new ListCell<RendezVousSchema>() {

                    @Override
                    protected void updateItem(RendezVousSchema rdv, boolean empty) {
                        super.updateItem(rdv, empty);
                        if (empty || rdv == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            Label rdvType = new Label();
                            if (rdv instanceof ConsultationSchema) {
                                rdvType.setText("Consultation");
                            }
                            else if (rdv instanceof AtelierSchema) {
                                rdvType.setText("Atelier");
                            }else {
                                rdvType.setText("Suivi");
                            }
                            HBox hBox = new HBox(10);
                            Label dateLabel = new Label(rdv.getDate().toString());
                            Label heureLabel = new Label(rdv.getHeure());

                            dateLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-pref-width:165.4 ; -fx-pref-height: 34.4; -fx-alignment:center ");
                            heureLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-pref-width: 230.6; -fx-pref-height: 36; -fx-alignment: center;");
                            rdvType.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-pref-width: 194.4; -fx-pref-height: 36; -fx-alignment: center;");
                            hBox.setStyle(" -fx-pref-height: 36; ; -fx-background-color: #fff; -fx-spacing: 10;   -fx-padding: 5;");

                            hBox.getChildren().addAll(rdvType, dateLabel, heureLabel );
                            setGraphic(hBox);
                        }
                    }
                };
            }
        });


        rdvsList.setItems(rdvs);
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
    public void handleBoClick(ActionEvent event) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void handleFicheSuiviClick(ActionEvent event){
        try {
            HelloController.redirectPage(event, "fichesSuivi.fxml", "Fiche de suivi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
