package Controllers.fichesSuiviControllers;

import Models.DossierPatient.DossierPatientSchema;
import Models.FicheDeSuivi.FicheDeSuiviSchema;
import Models.Objectif.ObjectifSchema;
import Models.Objectif.TermeEnum;
import Models.Ortophoniste.OrtophonisteSchema;
import Models.Trouble.TroubleCategories;
import Models.Trouble.TroubleSchema;
import Models.patient.PatientSchema;
import Utils.Popups;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.ArrayList;

public class FichiSuiviController implements HelloController.InitializeWithDossierPatient{
    private OrtophonisteSchema orthophoniste;
    @FXML
    private Label orthoField;
    @FXML
    private ListView ficheList ;
    @FXML
    private Label numDossier;
    @FXML
    private Label nomPatient;
    @FXML
    private Label prenomPatient;
    @FXML
    private TextField tf_objectif;
    @FXML
    private
    ComboBox objectifPicker;
    private String orthoNom;
    private String orthoPrenom;
    private final ObservableList<FicheDeSuiviSchema> fiches = FXCollections.observableArrayList();
    private boolean isInitialized = false;
    private ArrayList<ObjectifSchema> objectifs = new ArrayList<>();
    private DossierPatientSchema dossier;



    @FXML
    @Override
    public void initializeWithDossierPatient(DossierPatientSchema dossierPatient, String nom, String prenom) {

        System.out.println("here");
        if (isInitialized) return; // Skip if already initialized

        this.orthophoniste = HelloApplication.currentUser;
        this.orthoNom = orthophoniste.getNom();
        this.orthoPrenom = orthophoniste.getPrenom();
        orthoField.setText(" " + orthoNom + " " + orthoPrenom);
        this.numDossier.setText(String.valueOf(dossierPatient.getId()));
        this.nomPatient.setText(nom);
        this.prenomPatient.setText(prenom);
        // Add patients to the list
        fiches.addAll(dossierPatient.getFichesDesSuivis());
        dossier = dossierPatient;

        ObservableList<TermeEnum> options = FXCollections.observableArrayList(TermeEnum.values());
        System.out.println(" dsfkj" +options);
        objectifPicker.setItems(options);

        // Set custom cell factory
        ficheList.setCellFactory(new Callback<ListView<FicheDeSuiviSchema>, ListCell<FicheDeSuiviSchema>>() {
            @Override
            public ListCell<FicheDeSuiviSchema> call(ListView<FicheDeSuiviSchema> listView) {
                return new ListCell<FicheDeSuiviSchema>() {

                    @Override
                    protected void updateItem(FicheDeSuiviSchema fiche, boolean empty) {
                        super.updateItem(fiche, empty);
                        if (empty || fiche == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            String ortho = HelloApplication.currentUser.getEmail();


                            HBox hBox = new HBox(10);
                            Label numLabel = new Label("Fiche du suivi num : "+ ficheList.getItems().indexOf(fiche) + 1);
                            Button btn = new Button("Consulter");

                            numLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-pref-width: 200.6; -fx-pref-height: 34.4; -fx-alignment:center ");
                            hBox.setStyle(" -fx-pref-height: 36; ; -fx-background-color: #fff;   -fx-padding: 5;");
                            btn.setStyle("-fx-background-color: #1588ea; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight:bold; -fx-pref-width: 100; -fx-pref-height: 36;  -fx-alignment: center; -fx-cursor:hand;");

                            HBox btnBox = new HBox();
                            btnBox.getChildren().add(btn);
                            btnBox.setMargin(btn, new Insets(0, 0, 0, 245));

                            ficheList.setSelectionModel(null);

                            btn.setOnAction(event -> {
                                try {
                                    // redirect to dossier patient

                                    FicheDeSuiviSchema.redirectToFicheSuivi(event ,fiche,dossier,nomPatient.getText(), prenomPatient.getText(), "fichSuivi.fxml","Fiche de suivi");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                            hBox.getChildren().addAll(numLabel,btn);
                            setGraphic(hBox);
                        }
                    }
                };
            }
        });

        ficheList.setItems(fiches);
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
    public void handleAddObjectif(ActionEvent event){
        if(tf_objectif.getText().isEmpty() || objectifPicker.getValue() == null){
            Popups.showErrorMessage("Erreur", "Objectif et terme doivent être remplis");
            return;
        }
        String objectifNom = tf_objectif.getText();
        TermeEnum objectifCategory = (TermeEnum) objectifPicker.getValue();
        ObjectifSchema objectif = new ObjectifSchema(objectifNom,objectifCategory);
        objectifs.add(objectif);
        tf_objectif.setText("");
    }
    public void handleCreateButton(ActionEvent event){
        // create new fiche
        FicheDeSuiviSchema newFiche = new FicheDeSuiviSchema(objectifs);

        dossier.addFicheSuivi(newFiche);
        DossierPatientSchema.saveDossierPatient(dossier,HelloApplication.currentUser.getEmail(), this.nomPatient.getText(), this.prenomPatient.getText());

        HelloApplication.dossierPatientModel.updateDossierPatient(dossier);
        HelloApplication.dossierPatientModel.saveDossierPatient();
    }
}
