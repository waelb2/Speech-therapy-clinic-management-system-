package Controllers.userControllers;
import com.example.tp_poo.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class signupFormController {
    @FXML
    private AnchorPane leftPanel;
    @FXML
    protected void handleOnLeftPanelClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginForm.fxml"));
        Stage stage =(Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),680, 500);
        stage.setTitle("OrthoTech");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void handleOnMouseEntered(MouseEvent event) {
        leftPanel.setCursor(Cursor.HAND);
    }
}
