package Controllers.testsAnamsControllers.testCreationControllers;

import Models.Test.Exercice.ExerciceSchema;
import Models.Test.Question.QCM;
import Models.Test.Question.QuestionSchema;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class addExoController implements HelloController.InitializeDataWithExercices {

    @FXML
    private TextField tf_exerciceName;

    @FXML
    private TextField tf_requiredMaterial;

    @FXML
    private Button saveExo;

private ArrayList<ExerciceSchema> exercice;
    private ExerciceSchema exo = new ExerciceSchema();
    @FXML
    @Override
    public void initializeWithExercices(ArrayList<ExerciceSchema> exercices){


    };



    @FXML
    private void submitExo(ActionEvent event) throws IOException {
        exo.setNom(tf_exerciceName.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ExoTestCreation.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Liste des tests");
        stage.show();
    }

    public void loadQuestion(String exercice) {
        tf_exerciceName.setText(exercice);

    }
}

