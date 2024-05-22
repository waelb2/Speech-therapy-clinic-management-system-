package Controllers.rdvControllers;

import Exceptions.AllInputsShouldBeProvidedException;
import Exceptions.PatientDoesNotExistException;
import Models.DossierPatient.DossierPatientSchema;
import Models.RendezVous.AtelierSchema;
import Models.RendezVous.SuiviSchema;
import Models.RendezVous.TypeSuivi;
import Models.patient.PatientSchema;
import Utils.Popups;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AtelierController implements HelloController.InitializeDataWithObject {
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
    private Button btn_add;
    @FXML
    private TextField tf_thematique;
    @FXML
    private ListView<PatientSchema> listPatients;

    private final ObservableList<PatientSchema> patients = FXCollections.observableArrayList();
    private final List<PatientSchema> selectedPatients = new ArrayList<>();
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


        // loading patients
        // Load patients (this could be fetched from a database or other source)
        patients.addAll(HelloApplication.patientModel.getAllPatients());

        // Set the cell factory to display CheckBox and Labels for nom and prenom
        listPatients.setCellFactory(new Callback<ListView<PatientSchema>, ListCell<PatientSchema>>() {
            @Override
            public ListCell<PatientSchema> call(ListView<PatientSchema> listView) {
                return new ListCell<PatientSchema>() {
                    private final CheckBox checkBox = new CheckBox();

                    @Override
                    protected void updateItem(PatientSchema patient, boolean empty) {
                        super.updateItem(patient,empty);
                        if (empty || patient == null) {
                            setGraphic(null);
                        } else {
                            checkBox.setText(patient.getNom() + " " + patient.getPrenom());
                            checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
                                if (isNowSelected) {
                                    selectedPatients.add(patient);
                                } else {
                                    selectedPatients.remove(patient);
                                }
                            });
                            setGraphic(checkBox);
                        }
                    }
                };
            }
        });

        listPatients.setItems(patients);
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

    public void handleAddButton(ActionEvent event) {

            //Add suivi to dossierPatient
            for (PatientSchema patient : selectedPatients) {
                System.out.println(patient.getNom() + " " + patient.getPrenom());
            }

            // Redirect to dashboard
    }

    public  void handleCreateButton(ActionEvent event) throws Exception{
        try{
            // handle inputs validation

            if (tf_thematique.getText().isEmpty() || selectedPatients.isEmpty()) {
                throw new AllInputsShouldBeProvidedException();
            }

            // handle the creation of a Consultation and a new Patient
            String nom  = label_nom.getText();
            String prenom = label_prenom.getText();
            String age = label_age.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            LocalDate dateRdv = LocalDate.parse(label_date.getText(), formatter);
            String heure = label_hour.getText();
            String min = label_min.getText();
            String thematique = tf_thematique.getText();
            ArrayList<String> listePatients = selectedPatients.stream().map(patient->patient.getNom() + "_" + patient.getPrenom()).collect(Collectors.toCollection(ArrayList::new));

            // create new Atelier
            AtelierSchema newAtelier = new AtelierSchema(dateRdv, heure, min,"",thematique,listePatients);

            // save new Atelier
            HelloApplication.rendezVousModel.createRendezVous(newAtelier);
            HelloApplication.rendezVousModel.saveRendezVous();

            String ortho = HelloApplication.currentUser.getEmail();
            // loading patients files (dossiers patients)
             for(PatientSchema patient : selectedPatients){
                 DossierPatientSchema dossierPatient = DossierPatientSchema.loadDossierPatient(ortho, patient.getNom(), patient.getPrenom());
                 int numDossier = dossierPatient.getId();

                 DossierPatientSchema foundDossierPatient = HelloApplication.dossierPatientModel.findDossierPatient(numDossier);
                 foundDossierPatient.addRdv(newAtelier);

                 HelloApplication.dossierPatientModel.updateDossierPatient(foundDossierPatient);

             }

             HelloApplication.dossierPatientModel.saveDossierPatient();

            Popups.showSuccessMessage("Created", "Atelier ajouté avec succès");

            // Redirect to dashboard
            HelloController.redirectPage(event, "dashboard.fxml", "Dashboard");}
        catch (AllInputsShouldBeProvidedException e){
            Popups.showErrorMessage("Erreur", "Tous les champs doivent être remplis");
            e.getMessage();
        }
    }


}
