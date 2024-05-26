package Controllers.bilanController;

import Controllers.fichesSuiviControllers.FichiSuiviController;
import Controllers.testsAnamsControllers.TestsAnamsControllers;
import Exceptions.AllInputsShouldBeProvidedException;
import Models.BilanOrthophonique.BilanOrthophoniqueSchema;
import Models.DossierPatient.DossierPatientSchema;
import Models.EpreuveClinique.EpreuveCliniqueSchema;
import Models.FicheDeSuivi.FicheDeSuiviSchema;
import Models.Ortophoniste.OrtophonisteSchema;
import Models.RendezVous.TypeSuivi;
import Models.Test.TestSchema;
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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class BilanController implements  HelloController.InitializeWithDossierPatient {
    private OrtophonisteSchema orthophoniste ;
    @FXML
    Label orthoField;
    @FXML
    ListView testsList;
    @FXML
    ListView exercicesList;
    @FXML
    Button btn_creer;
    @FXML
    TextField tf_trouble;
    @FXML
    TextField tf_project;
    @FXML
    ComboBox troublePicker;
    private  String orthoNom  ;
    private  String orthoPrenom  ;
    private String patientNom;
    private String patientPrenom;



    @FXML
    private Button btn_next;

    private final ObservableList<TestSchema> testes = FXCollections.observableArrayList();
    private  DossierPatientSchema dossier;
    ArrayList<TroubleSchema>  troubles = new ArrayList<>();
    String rdvtype ;

    @FXML
    @Override
    public void initializeWithDossierPatient(DossierPatientSchema dossierPatient, String nom, String prenom) {

        this.orthophoniste = HelloApplication.currentUser;
        this.orthoNom  = orthophoniste.getNom();
        this.orthoPrenom = orthophoniste.getPrenom();
        orthoField.setText(" " + orthoNom + " " + orthoPrenom);

        String rdvType = nom;
        rdvtype = nom;
        patientNom = prenom.split(" ")[0];
        patientPrenom = prenom.split(" ")[1];

        if(rdvType != "Consultation"){
           btn_creer.setVisible(false);
        }

        ObservableList<TroubleCategories> options = FXCollections.observableArrayList(TroubleCategories.values());
        troublePicker.setItems(options);

        dossier = dossierPatient;
        // Add tests to the list

    }
    public void handlePatientsClick(ActionEvent evet) {
        try{
            HelloController.redirectPage(evet , "dossiersPatients.fxml", "Mes patients");
        }catch (Exception e){
            e.getMessage();
        }
    }
    public void handleAddRdvClick(ActionEvent evet) {
        try{
            HelloController.redirectPage(evet , "rdv.fxml", "Créer rendez-vous");
        }catch (Exception e){
            e.getMessage();
        }
    }
    public void handleStatsClick(ActionEvent event) {
        try{
            HelloController.redirectPage(event , "stats.fxml", "Mes Statistiques");
        }catch (Exception e){
            e.getMessage();
        }
    }
    public void handleSettingsClick(ActionEvent event) {
        try{
            HelloController.redirectPage(event, "parametres.fxml", "Mes paramètres");
        }catch (Exception e){
            e.getMessage();
        }
    }
    public void handleLogoClick(MouseEvent event) {
        try{
            HelloController.redirectPage(event, "dashboard.fxml", "Dashboard");
        }catch (Exception e){
            e.getMessage();
        }
    }
    public void handleTestsClick(ActionEvent event) {
        try{
            HelloController.redirectPage(event, "testAnam.fxml", "Testes et Anamnèses");
        }catch (Exception e){
            e.getMessage();
        }}

    public void handleTroubleButton(ActionEvent event) {
        if(tf_trouble.getText().isEmpty() || troublePicker.getValue() == null){
            Popups.showErrorMessage("Erreur", "Trouble et catégorie doivent être remplis");
            return;
        }
        String troubleNom = tf_trouble.getText();
        TroubleCategories troubleCategory = (TroubleCategories) troublePicker.getValue();
        TroubleSchema trouble = new TroubleSchema(troubleNom, troubleCategory);
        troubles.add(trouble);
        tf_trouble.setText("");
    }
    public void handleNextButton(ActionEvent event) throws Exception{

        //validate inputs
        try{
          if( tf_project.getText().isEmpty() || troublePicker.getValue() == null){
             throw new AllInputsShouldBeProvidedException();
         }

            String projet = tf_project.getText();
            ArrayList<TestSchema> testes = new ArrayList<>();
            EpreuveCliniqueSchema epreuveClinique = new EpreuveCliniqueSchema("observation", testes);
            ArrayList<EpreuveCliniqueSchema> epreuvesCliniques = new ArrayList<>();
            epreuvesCliniques.add(epreuveClinique);


            // Create BilanOrthophoniqueSchema
             BilanOrthophoniqueSchema bilan = new BilanOrthophoniqueSchema(epreuvesCliniques, troubles, projet);
             dossier.addBO(bilan);
             HelloApplication.dossierPatientModel.updateDossierPatient(dossier);
             HelloApplication.dossierPatientModel.saveDossierPatient();

             DossierPatientSchema.saveDossierPatient(dossier, HelloApplication.currentUser.getEmail(), patientNom, patientPrenom);

            // Redirect to the next page
            if(rdvtype == "Suivi"){
                FicheDeSuiviSchema fiche = dossier.getFichesDesSuivis().get(dossier.getFichesDesSuivis().size() - 1);
                FicheDeSuiviSchema.redirectToFicheSuivi(event, fiche,dossier,patientNom, patientPrenom,"fichEvaluation.fxml", "Fiche de suivi");

            }
            else{
                HelloController.redirectPage(event, "observation.fxml", "Observation");
            }
        } catch (AllInputsShouldBeProvidedException e){
            Popups.showErrorMessage("Erreur", "Tous les champs doivent être remplis");
        }
    }
    public void handleAnamButton(ActionEvent event) throws Exception{
        System.out.println("Anam");
    }




}
