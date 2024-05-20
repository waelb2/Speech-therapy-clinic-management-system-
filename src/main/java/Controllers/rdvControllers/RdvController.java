package Controllers.rdvControllers;

import Exceptions.AllInputsShouldBeProvidedException;
import Exceptions.PasswordNotProvidedException;
import Exceptions.PatientDoesNotExistException;
import Exceptions.PatientHasConsultationException;
import Models.Ortophoniste.OrtophonisteSchema;
import Models.patient.PatientSchema;
import Utils.Popups;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.stream.IntStream;

public class RdvController  implements HelloController.InitializeData{
    private OrtophonisteSchema orthophoniste ;
    @FXML
    Label orthoField;
    private  String orthoNom  ;
    private  String orthoPrenom  ;


    @FXML
    private Button btn_next;

    @FXML
    private DatePicker datePicker;


    @FXML
    private ComboBox<Integer> hourPicker;


    @FXML
    private ComboBox<Integer> minPicker;


    @FXML
    private RadioButton radioAtelier;

    @FXML
    private RadioButton radioConsultaion;

    @FXML
    private RadioButton radioSuivi;


    @FXML
    private ToggleGroup rdvType;


    @FXML
    private TextField tf_age;
    @FXML
    private TextField tf_duree;

    @FXML
    private TextField tf_nom;

    @FXML
    private TextField tf_prenom;
    @FXML
    @Override
    public  void initialize(){
        this.orthophoniste = HelloApplication.currentUser;
        this.orthoNom  = orthophoniste.getNom();
        this.orthoPrenom = orthophoniste.getPrenom();
        orthoField.setText(" " + orthoNom + " " + orthoPrenom);

        // Restrict the age input to numbers only
        tf_age.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tf_age.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });


        // Restrict the period input to numbers only
        tf_duree.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tf_age.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        // Populate the hours ComboBox
        IntStream.range(0, 24).forEach(i -> hourPicker.getItems().add(i));
        hourPicker.getSelectionModel().selectFirst();

        // Populate the minutes ComboBox with intervals of 5 minutes
        IntStream.iterate(0, i -> i + 5).limit(12).forEach(i -> minPicker.getItems().add(i));
        minPicker.getSelectionModel().selectFirst();

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
        try{
            LocalDate date = datePicker.getValue();
            RadioButton selectedRadioButton = (RadioButton) rdvType.getSelectedToggle();

            if(datePicker.getValue() == null || selectedRadioButton == null) throw new AllInputsShouldBeProvidedException();

            String nom = tf_nom.getText().toLowerCase();
            String prenom = tf_prenom.getText().toLowerCase();
            String textAge = tf_age.getText();
            String selectedRdvType = selectedRadioButton.getText();
            String textDuree = tf_duree.getText();

            if(nom.isEmpty() || prenom.isEmpty() || textAge.isEmpty()  || date == null){
                throw new AllInputsShouldBeProvidedException();
            }

            int age = Integer.parseInt(textAge);
            int duree = Integer.parseInt(textDuree);
            //search for patient
            PatientSchema patient = HelloApplication.patientModel.findPatient(nom, prenom);

           switch (selectedRdvType){
                case "Consultation":
                    if(!(patient == null)){
                        throw new PatientHasConsultationException();
                    }
                    HelloController.ConsultationObject consultationObject = new HelloController.ConsultationObject(nom, prenom, textAge, date, hourPicker.getValue().toString(), minPicker.getValue().toString());
                    HelloController.redirectWithData(event, consultationObject, "consultation.fxml", "Consultation");
                    break;
                case "Suivi":
                    if(patient == null){
                        throw new PatientDoesNotExistException(selectedRdvType.toLowerCase());
                    }
                    System.out.println("Suivi");
                    break;
                case "Atelier":
                    if(patient == null){
                        throw new PatientDoesNotExistException(selectedRdvType.toLowerCase());
                    }
                    System.out.println("Atelier");
                    break;
            }


        }catch (AllInputsShouldBeProvidedException | PatientHasConsultationException | PatientDoesNotExistException e){
            Popups.showErrorMessage("Error", e.getMessage());
            System.out.println(e.getMessage());
        }
    }

}
