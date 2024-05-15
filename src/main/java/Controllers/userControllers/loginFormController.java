package Controllers.userControllers;

import Models.Ortophoniste.OrtophonisteSchema;
import Utils.Popups;
import com.example.tp_poo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;
import java.io.IOException;
import Models.Ortophoniste.OrtophonisteModel;
import Exceptions.*;
public class loginFormController {
    final OrtophonisteModel ortophonisteModel = HelloApplication.ortophonisteModel;

    @FXML
    private TextField tf_email;
    @FXML
    private PasswordField tf_password;
    public  void redirectSignup(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signupForm.fxml"));
        Stage stage =(Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 680, 500);
        stage.setTitle("Sign Up");
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
            Popups.showSuccessMessage("loggedIn", "You're in ");

        }catch (UserNotFoundException | EmailNotProvidedException| PasswordNotProvidedException | WrongPasswordException e){
            System.out.println(e.getMessage());
            Popups.showErrorMessage("Login error", e.getMessage());
        }

    }

}
