package Controllers.bilanController;

import Controllers.testsAnamsControllers.TestsAnamsControllers;
import Exceptions.AllInputsShouldBeProvidedException;
import Models.BilanOrthophonique.BilanOrthophoniqueSchema;
import Models.DossierPatient.DossierPatientSchema;
import Models.EpreuveClinique.EpreuveCliniqueSchema;
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



    @FXML
    private Button btn_next;

    private final ObservableList<TestSchema> testes = FXCollections.observableArrayList();
    private  DossierPatientSchema dossier;

    @FXML
    @Override
    public void initializeWithDossierPatient(DossierPatientSchema dossierPatient, String nom, String prenom) {

        this.orthophoniste = HelloApplication.currentUser;
        this.orthoNom  = orthophoniste.getNom();
        this.orthoPrenom = orthophoniste.getPrenom();
        orthoField.setText(" " + orthoNom + " " + orthoPrenom);

        if(nom != "Consultation"){
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

    public void handleNextButton(ActionEvent event) throws Exception{

        //validate inputs
        try{
          if(tf_trouble.getText().isEmpty() || tf_project.getText().isEmpty() || troublePicker.getValue() == null){
             throw new AllInputsShouldBeProvidedException();
         }
            String troubleNom = tf_trouble.getText();
            TroubleCategories troubleCategory = (TroubleCategories) troublePicker.getValue();
            TroubleSchema trouble = new TroubleSchema(troubleNom, troubleCategory);
            String projet = tf_project.getText();
            ArrayList<TestSchema> testes = new ArrayList<>();
            EpreuveCliniqueSchema epreuveClinique = new EpreuveCliniqueSchema("observation", testes);
            ArrayList<EpreuveCliniqueSchema> epreuvesCliniques = new ArrayList<>();
            epreuvesCliniques.add(epreuveClinique);
            ArrayList<TroubleSchema>  troubles = new ArrayList<>();
            troubles.add(trouble);


            // Create BilanOrthophoniqueSchema
             BilanOrthophoniqueSchema bilan = new BilanOrthophoniqueSchema(epreuvesCliniques, troubles, projet);
             dossier.addBO(bilan);
            System.out.println(dossier.getId() +  " " + dossier.getbO().size());
            // Redirect to the next page
        } catch (AllInputsShouldBeProvidedException e){
            Popups.showErrorMessage("Erreur", "Tous les champs doivent être remplis");
        }
    }
    public void handleAnamButton(ActionEvent event) throws Exception{
        System.out.println("Anam");
    }




}
