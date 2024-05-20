package Controllers.userControllers;
import Exceptions.AllInputsShouldBeProvidedException;
import Exceptions.EmailExistsException;
import Models.Ortophoniste.OrtophonisteModel;
import Models.Ortophoniste.OrtophonisteSchema;
import Utils.Popups;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SignupFormController {
    OrtophonisteModel orthophonisteModel = HelloApplication.ortophonisteModel;
    @FXML
    private AnchorPane leftPanel;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_mdp;
    @FXML
    private TextField tf_tele;
    @FXML
    private TextField tf_adresse;
    @FXML
    private Button btn_signup;

    @FXML
    protected void handleOnLeftPanelClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginForm.fxml"));
        Stage stage =(Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),800, 600);
        stage.setTitle("OrthoTech");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void handleOnMouseEntered(MouseEvent event) {
        leftPanel.setCursor(Cursor.HAND);
    }

    @FXML
    protected void handleSignUp(ActionEvent event) throws  Exception{
        String nom = tf_nom.getText().trim().toLowerCase() ;
        String prenom = tf_prenom.getText().trim().toLowerCase() ;
        String email = tf_email.getText().trim().toLowerCase() ;
        String mdp = tf_mdp.getText().trim().toLowerCase() ;
        String tele = tf_tele.getText().trim().toLowerCase() ;
        String adresse = tf_adresse.getText().trim().toLowerCase() ;


        try {
            if(nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty() || tele.isEmpty() || adresse.isEmpty())   throw  new AllInputsShouldBeProvidedException();
            if(orthophonisteModel.findOrtophoniste(email) != null) throw new EmailExistsException();

            OrtophonisteSchema newOrthophoniste = new OrtophonisteSchema(nom, prenom,email, mdp,adresse,tele);
            orthophonisteModel.createOrtophoniste(newOrthophoniste);
            HelloApplication.currentUser = newOrthophoniste;
            HelloApplication.currentUserDir = "./data/orthophonistes/"+email;

            // creating the new user folder
            String newUserPath = HelloApplication.orthophonistesDir + "/" + email;
            File userFolder  = new File(newUserPath);
            userFolder.mkdirs();
            // creating the patients folder
            String patientsPath= HelloApplication.orthophonistesDir + "/" + email + "/dossiersPatients";
            File patientsFolder  = new File(patientsPath);
            patientsFolder.mkdirs();
            // redirecting the doctor to his dashboard
            HelloController.redirectPage(event, "dashboard.fxml", "Dashboard");
        }catch (AllInputsShouldBeProvidedException | EmailExistsException  e ){
            System.out.println(e.getMessage());
            Popups.showSuccessMessage("Sign-up error", e.getMessage());
        }
    }
}
