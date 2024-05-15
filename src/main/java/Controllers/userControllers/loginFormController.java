package Controllers.userControllers;

import Models.Ortophoniste.OrtophonisteSchema;
import com.example.tp_poo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import Models.Ortophoniste.OrtophonisteModel;
public class loginFormController {

    @FXML
    private TextField tf_email;
    @FXML
    private PasswordField tf_password;
    public  void redirectSignup(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signupForm.fxml"));
        Stage stage =(Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 680, 500);
        stage.setTitle("Sign Up!");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected  void handleLogin(ActionEvent event) throws IOException {
        String email = tf_email.getText();
        String password = tf_password.getText();
        OrtophonisteModel ortophonisteModel = HelloApplication.ortophonisteModel;
       OrtophonisteSchema ortho = ortophonisteModel.findOrtophoniste(email);
        System.out.println(ortho.getMotDePasse());
    }

}
