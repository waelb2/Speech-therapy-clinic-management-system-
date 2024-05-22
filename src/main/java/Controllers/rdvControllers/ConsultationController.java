package Controllers.rdvControllers;

import Exceptions.AllInputsShouldBeProvidedException;
import Exceptions.PatientDoesNotExistException;
import Exceptions.PatientHasConsultationException;
import Models.BilanOrthophonique.BilanOrthophoniqueSchema;
import Models.DossierPatient.DossierPatientSchema;
import Models.FicheDeSuivi.FicheDeSuiviSchema;
import Models.Ortophoniste.OrtophonisteSchema;
import Models.RendezVous.ConsultationSchema;
import Models.RendezVous.RendezVousSchema;
import Models.patient.AdultSchema;
import Models.patient.EnfantSchema;
import Models.patient.PatientSchema;
import Utils.Popups;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;
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
    private Button btn_creer;

    @FXML
    private Button createRdv;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button dossiersPatients;


    @FXML
    private TextField tf_adresse;

    @FXML
    private TextField tf_class_diplome;

    @FXML
    private TextField tf_lieu;

    @FXML
    private TextField tf_profession;

    @FXML
    private TextField tf_tele_one;

    @FXML
    private TextField tf_tele_second;
    @FXML
    private  TextField tf_duree;


    @FXML
    @Override
    public  void initializeWithData(HelloController.ConsultationObject consultationObject){
        this.orthophoniste = HelloApplication.currentUser;
        this.orthoNom  = orthophoniste.getNom();
        this.orthoPrenom = orthophoniste.getPrenom();
        orthoField.setText(" " + orthoNom + " " + orthoPrenom);
        this.label_nom.setText( consultationObject.getNom());
        this.label_prenom.setText( consultationObject.getPrenom());
        this.label_age.setText( consultationObject.getAge());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String formattedStringDate = consultationObject.getDate().format(formatter);
        this.label_date.setText(formattedStringDate);

        this.label_hour.setText(consultationObject.getHeure());
        this.label_min.setText(consultationObject.getMin());

        if(Integer.parseInt(consultationObject.getAge() ) < 18){
            tf_class_diplome.setPromptText("Classe d'étude");
            tf_profession.setVisible(false);
        }else {
            tf_class_diplome.setPromptText("Diplome");
            tf_tele_second.setVisible(false);
        }

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


    public void handleCreateButton(ActionEvent event) throws Exception{

        // handle the creation of a Consultation and a new Patient
        String nom  = label_nom.getText();
        String prenom  = label_prenom.getText();
        int age  = Integer.parseInt( label_age.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        LocalDate dateRdv = LocalDate.parse(label_date.getText(), formatter);
        LocalDate dateDeNaissance = datePicker.getValue();
        String lieuDeNaissance = tf_lieu.getText();
        String heure = label_hour.getText() + ":" + label_min.getText();
        String observation = "";
        String adresse = tf_adresse.getText();
        String diplomeOuClasse = tf_class_diplome.getText();
        String profession = tf_profession.getText();
        String[] numParents = {tf_tele_one.getText(), tf_tele_second.getText()};
        String duree = "";



        try{
            // handle the inputs validation
           if(datePicker == null || tf_lieu.getText().isEmpty() || tf_adresse.getText().isEmpty() || tf_class_diplome.getText().isEmpty() || tf_tele_one.getText().isEmpty() || (age>18 && tf_profession.getText().isEmpty()) || (age<18 && tf_tele_second.getText().isEmpty()) ){
               throw new AllInputsShouldBeProvidedException();
           }

           if(age < 18){
               duree = "90";
               EnfantSchema enfant = new EnfantSchema(nom, prenom, dateDeNaissance, lieuDeNaissance, adresse, diplomeOuClasse, numParents);
               HelloApplication.patientModel.createEnfant(enfant);
           }else {
               duree = "150";
               AdultSchema adult = new AdultSchema(nom, prenom, dateDeNaissance, lieuDeNaissance, adresse, diplomeOuClasse, profession);
               HelloApplication.patientModel.createAdult(adult);
           }

            HelloApplication.patientModel.savePatients();

           // create dossierPatient
            int dossierId = HelloApplication.patientModel.countPatients();
            TreeSet<RendezVousSchema> rdvs = new TreeSet<>();
            ArrayList < BilanOrthophoniqueSchema> bos = new ArrayList<>();
            ArrayList < FicheDeSuiviSchema> fichesSuivis = new ArrayList<>();
            DossierPatientSchema newDossierPatient = new DossierPatientSchema(dossierId,rdvs,bos,fichesSuivis);
            String orthoEmail = HelloApplication.currentUser.getEmail();

            // creating new consultation
            ConsultationSchema newConsultation  = new ConsultationSchema(dateRdv,heure, duree,nom,prenom,age,observation);

            //adding consultation to dossierPatient, rdvs
            newDossierPatient.addRdv(newConsultation);
            HelloApplication.rendezVousModel.createRendezVous(newConsultation);
            HelloApplication.rendezVousModel.saveRendezVous();


            // creating patient file (dossier patient)
            boolean created = DossierPatientSchema.saveDossierPatient(newDossierPatient,orthoEmail,nom, prenom);

            // adding dossierPatient to dossierPatients file
            HelloApplication.dossierPatientModel.createDossierPatient(newDossierPatient);

            if(created){
                Popups.showSuccessMessage("Created", "Consultation ajoutée avec succès");
            }else {
                Popups.showErrorMessage("Erreur dans la création du consultation");
            }

            // redirect to dashboard
            HelloController.redirectPage(event, "dashboard.fxml", "Dashboard");
        }
        catch (AllInputsShouldBeProvidedException e){
            Popups.showErrorMessage("Error", e.getMessage());
        }

    }


}
