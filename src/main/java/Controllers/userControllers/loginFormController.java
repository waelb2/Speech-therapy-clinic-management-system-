package Controllers.userControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class loginFormController {
    @FXML
    private void handleSignUp(ActionEvent event) throws IOException {
        // Load the sign up form FXML file
        Parent signUpParent = FXMLLoader.load(getClass().getResource("/views/signupForm"));
        Scene signUpScene = new Scene(signUpParent);
        Stage window = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(signUpScene);
        window.show();
    }
}
