package Controllers.testsAnamsControllers;

import Models.DossierPatient.DossierPatientSchema;
import Models.Ortophoniste.OrtophonisteSchema;
import Models.Test.TestQstSchema;
import Models.Test.TestSchema;
import Models.patient.PatientSchema;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class mesTestsController implements HelloController.InitializeData {
    private OrtophonisteSchema orthophoniste;
    @FXML
    private Label orthoField;
    @FXML
    private ListView<TestSchema> testsList;
    private String orthoNom;
    private String orthoPrenom;
    private final ObservableList<TestSchema> mestests = FXCollections.observableArrayList();
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
        mestests.addAll(HelloApplication.testModel.getAllTest());
        // Set custom cell factory
        testsList.setCellFactory(new Callback<ListView<TestSchema>, ListCell<TestSchema>>() {
            @Override
            public ListCell<TestSchema> call(ListView<TestSchema> listView) {
                return new ListCell<TestSchema>() {

                    @Override
                    protected void updateItem(TestSchema test, boolean empty) {
                        super.updateItem(test, empty);
                        if (empty || test == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            String ortho = HelloApplication.currentUser.getEmail();
                            String typeTest = "";
                            if (test instanceof TestQstSchema) {
                                typeTest = "Test de questions";
                                System.out.println(typeTest);
                            } else {
                                typeTest = "Test d'exercices";
                                System.out.println(typeTest);
                            }

                            HBox hBox = new HBox(10);
                            Label nomTestLabel = new Label(test.getNom());
                           // Label testType = new Label(typeTest);
                            //Button btn = new Button("Supprimer");

                            nomTestLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-pref-width: 146.4; -fx-pref-height: 36; -fx-alignment: center;");
                            //testType.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-pref-width: 170.4; -fx-pref-height: 36; -fx-alignment: center;");
                            hBox.setStyle(" -fx-pref-height: 36; ; -fx-background-color: #fff;   -fx-padding: 5;");
                            //btn.setStyle("-fx-background-color: #1588ea; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight:bold; -fx-pref-width: 100; -fx-pref-height: 36;  -fx-alignment: center; -fx-cursor:hand;");

                            HBox btnBox = new HBox();
                            //btnBox.getChildren().add(btn);
                            //btnBox.setMargin(btn, new Insets(0, 0, 0, 45));

                            testsList.setSelectionModel(null);

                            //btn.setOnAction(event -> {
                              //  try {

                                //    // TODO Delete function, dont forget to remove comments
                                   // DossierPatientSchema.redirectToDossierPatient(event, dossierPatient, patient.getNom(), patient.getPrenom(), "dossier.fxml", "Dossier Patient");
                                //} catch (Exception e) {
                                  //  e.printStackTrace();
                                //}
                            //});
                            //hBox.getChildren().addAll(nomTestLabel, testType, btn);
                            setGraphic(hBox);
                        }
                    }
                };
            }
        });

        testsList.setItems(mestests);
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

