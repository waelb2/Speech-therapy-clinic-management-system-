package Controllers.bilanController;

import Models.BilanOrthophonique.BilanOrthophoniqueSchema;
import Models.DossierPatient.DossierPatientSchema;
import Models.FicheDeSuivi.FicheDeSuiviSchema;
import Models.Objectif.ObjectifSchema;
import Models.Objectif.TermeEnum;
import Models.Ortophoniste.OrtophonisteSchema;
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
import javafx.util.Callback;

import java.util.ArrayList;

public class BilansDisplayController implements HelloController.InitializeWithDossierPatient {
    private OrtophonisteSchema orthophoniste;
    @FXML
    private Label orthoField;
    @FXML
    private ListView boList ;
    @FXML
    private Label numDossier;
    @FXML
    private Label nomPatient;
    @FXML
    private Label prenomPatient;
    private String orthoNom;
    private String orthoPrenom;
    private final ObservableList<BilanOrthophoniqueSchema> bilans = FXCollections.observableArrayList();
    private boolean isInitialized = false;
    private ArrayList<ObjectifSchema> objectifs = new ArrayList<>();
    private DossierPatientSchema dossier;



    @FXML
    @Override
    public void initializeWithDossierPatient(DossierPatientSchema dossierPatient, String nom, String prenom) {

        if (isInitialized) return; // Skip if already initialized

        this.orthophoniste = HelloApplication.currentUser;
        this.orthoNom = orthophoniste.getNom();
        this.orthoPrenom = orthophoniste.getPrenom();
        orthoField.setText(" " + orthoNom + " " + orthoPrenom);
        this.numDossier.setText(String.valueOf(dossierPatient.getId()));
        this.nomPatient.setText(nom);
        this.prenomPatient.setText(prenom);
        // Add patients to the list
        bilans.addAll(dossierPatient.getbO());
        dossier = dossierPatient;



        // Set custom cell factory
        boList.setCellFactory(new Callback<ListView<BilanOrthophoniqueSchema>, ListCell<BilanOrthophoniqueSchema>>() {
            @Override
            public ListCell<BilanOrthophoniqueSchema> call(ListView<BilanOrthophoniqueSchema> listView) {
                return new ListCell<BilanOrthophoniqueSchema>() {

                    @Override
                    protected void updateItem( BilanOrthophoniqueSchema bilan, boolean empty) {
                        super.updateItem(bilan, empty);
                        if (empty || bilan == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            String ortho = HelloApplication.currentUser.getEmail();


                            HBox hBox = new HBox(10);
                            Label numLabel = new Label("Bilan Orthophoniste num : "+ boList.getItems().indexOf(bilan) + 1);
                            Button btn = new Button("Consulter");

                            numLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-pref-width: 200.6; -fx-pref-height: 34.4; -fx-alignment:center ");
                            hBox.setStyle(" -fx-pref-height: 36; ; -fx-background-color: #fff;   -fx-padding: 5;");
                            btn.setStyle("-fx-background-color: #1588ea; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight:bold; -fx-pref-width: 100; -fx-pref-height: 36;  -fx-alignment: center; -fx-cursor:hand;");

                            HBox btnBox = new HBox();
                            btnBox.getChildren().add(btn);
                            btnBox.setMargin(btn, new Insets(0, 0, 0, 245));

                            boList.setSelectionModel(null);

                            btn.setOnAction(event -> {
                                try {
                                    // redirect to dossier patient

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

        boList.setItems(bilans);
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

    public void handleCreateButton(ActionEvent event){
        // create new fiche
        FicheDeSuiviSchema newFiche = new FicheDeSuiviSchema(objectifs);

        dossier.addFicheSuivi(newFiche);
        DossierPatientSchema.saveDossierPatient(dossier,HelloApplication.currentUser.getEmail(), this.nomPatient.getText(), this.prenomPatient.getText());

        HelloApplication.dossierPatientModel.updateDossierPatient(dossier);
        HelloApplication.dossierPatientModel.saveDossierPatient();
    }}
