package Controllers.fichesSuiviControllers;

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

public class FIcheEvaluationController implements  HelloController.InitializeWithFiche {
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
    public void initializeWithFiche (FicheDeSuiviSchema fiche , DossierPatientSchema dossier, String nom, String prenom) {
        if (isInitialized) return; // Skip if already initialized

        updatedDossier = dossier;
        this.orthophoniste = HelloApplication.currentUser;
        this.orthoNom = orthophoniste.getNom();
        this.orthoPrenom = orthophoniste.getPrenom();
        orthoField.setText(" " + orthoNom + " " + orthoPrenom);
        this.nomPatient = nom;
        this.prenomPatient = prenom;


        // Add rdvs to the list
        objectifs.addAll(fiche.getObjectifs());
        ArrayList<ObjectifSchema> updatedObjectifs = new ArrayList<>();

        objectifsList.setCellFactory(new Callback<ListView<ObjectifSchema>, ListCell<ObjectifSchema>>() {
            @Override
            public ListCell<ObjectifSchema> call(ListView<ObjectifSchema> listView) {
                return new ListCell<ObjectifSchema>() {

                    @Override
                    protected void updateItem(ObjectifSchema objectif, boolean empty) {
                        super.updateItem(objectif, empty);
                        if (empty || objectif == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            HBox hBox = new HBox(10);
                            Label objectifNom = new Label(objectif.getNom());
                            Label objectifDuree = new Label(objectif.getDuree().toString());
                            TextField objectifNote = new TextField() ;
                             Button btn = new Button("Attribuer");

                            objectifNom.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-pref-width:145.4 ; -fx-pref-height: 34.4; -fx-alignment:center ");
                            objectifDuree.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-pref-width: 240.6; -fx-pref-height: 36; -fx-alignment: center;");
                            objectifNote.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-pref-width: 194.4; -fx-pref-height: 36; -fx-alignment: center;");
                            hBox.setStyle(" -fx-pref-height: 36; ; -fx-background-color: #fff; -fx-spacing: 10;   -fx-padding: 5;");
                            btn.setStyle("-fx-background-color: #1588ea; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight:bold; -fx-pref-width: 80; -fx-pref-height: 36;  -fx-alignment: center; -fx-cursor:hand;");

                            btn.setOnAction(event -> {
                                objectif.setNote(Integer.parseInt(objectifNote.getText()));
                                objectifNote.setVisible(false);
                                btn.setVisible(false);
                                updatedObjectifs.add(objectif);
                                fiche.setObjectifs(updatedObjectifs);
                                updatedFiche = fiche;
                            });
                            hBox.getChildren().addAll(objectifNom,objectifDuree, objectifNote,btn);
                            setGraphic(hBox);
                        }
                    }
                };
            }
        });


        objectifsList.setItems(objectifs);
        isInitialized = true;



//        DossierPatientSchema.saveDossierPatient(dossier,HelloApplication.currentUser.getEmail(),)
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
        // save the updated fiche
        ArrayList<FicheDeSuiviSchema> fiches = updatedDossier.getFichesDesSuivis();
        fiches.remove(fiches.size()-1);
        fiches.add(updatedFiche);

        updatedDossier.setFichesDesSuivis(fiches);
        HelloApplication.dossierPatientModel.updateDossierPatient(updatedDossier);
        HelloApplication.dossierPatientModel.saveDossierPatient();
        System.out.println(updatedDossier.getFichesDesSuivis().get(0).getObjectifs().get(1).getNote());

        DossierPatientSchema.saveDossierPatient(updatedDossier,HelloApplication.currentUser.getEmail(),nomPatient , prenomPatient);

        HelloController.redirectPage(event,"observation.fxml", "Observation");
    }

}
