package Controllers.userControllers;

import Models.DossierPatient.DossierPatientSchema;
import Models.Ortophoniste.OrtophonisteSchema;
import Models.Test.TestSchema;
import Models.patient.PatientModel;
import Utils.Popups;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Map;

import Models.Ortophoniste.OrtophonisteModel;
import Exceptions.*;
import Controllers.dashboardControllers.DashboardController;
public class LoginFormController {
    final OrtophonisteModel ortophonisteModel = HelloApplication.ortophonisteModel;

    @FXML
    private TextField tf_email;
    @FXML
    private PasswordField tf_password;
    public void redirectSignup(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signupForm.fxml"));
        Stage stage =(Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();
    }
    public static void redirectDashboard(ActionEvent event )   throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dossiersPatients.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        Stage stage =(Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected  void handleLogin(ActionEvent event) throws Exception{
        String email = tf_email.getText().trim().toLowerCase();
        String password = tf_password.getText().trim();
        try{
            if(email == null || email.isEmpty()) throw new EmailNotProvidedException();
            if(password == null || password.isEmpty()) throw new PasswordNotProvidedException();

            OrtophonisteSchema orthophoniste = ortophonisteModel.findOrtophoniste(email);
            if(orthophoniste == null){
                throw new UserNotFoundException(email);
            }
            if(!orthophoniste.getMotDePasse().equals(password) ) throw new WrongPasswordException();

            HelloApplication.currentUser = orthophoniste;
            HelloApplication.currentUserDir = "./data/orthophonistes/"+email;

            // load patients
            HelloApplication.patientModel.loadPatients();

            //load rendez-vous
            HelloApplication.rendezVousModel.loadRendezVous();

            //load dossiers patients
            HelloApplication.dossierPatientModel.loadDossierPatient();
            HelloApplication.testModel.loadTests();
            for(TestSchema testSchema : HelloApplication.testModel.getAllTest()){
                System.out.println(testSchema.getNom());
                System.out.println(testSchema instanceof TestSchema);
            }

            HelloController.redirectPage(event, "dashboard.fxml", "Dashboard");
        }catch (UserNotFoundException | EmailNotProvidedException| PasswordNotProvidedException | WrongPasswordException e){
            System.out.println(e.getMessage());
            Popups.showErrorMessage("Login error", e.getMessage());
        }

    }

}
