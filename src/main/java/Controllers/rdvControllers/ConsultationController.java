package Controllers.rdvControllers;

import Exceptions.AllInputsShouldBeProvidedException;
import Exceptions.PatientDoesNotExistException;
import Exceptions.PatientHasConsultationException;
import Models.Ortophoniste.OrtophonisteSchema;
import Models.patient.PatientSchema;
import Utils.Popups;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.stream.IntStream;

public class ConsultationController implements  HelloController.InitializeDataWithObject {
    private OrtophonisteSchema orthophoniste ;
    @FXML
    Label orthoField;
    private  String orthoNom  ;
    private  String orthoPrenom  ;


    @FXML
    private  Label label_nom;
    @FXML
    private  Label label_prenom;
    @FXML
    private  Label label_age;
    @FXML
    private Label label_date;
    @FXML
    private Label label_hour;
    @FXML
    private Label label_min;

    @FXML
    @Override
    public  void initializeWithData(HelloController.ConsultationObject consultationObject){
        this.orthophoniste = HelloApplication.currentUser;
        this.orthoNom  = orthophoniste.getNom();
        this.orthoPrenom = orthophoniste.getPrenom();
        orthoField.setText(" " + orthoNom + " " + orthoPrenom);
        this.label_nom.setText( consultationObject.getNom());

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


}
