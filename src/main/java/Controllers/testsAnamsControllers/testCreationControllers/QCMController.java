package Controllers.testsAnamsControllers.testCreationControllers;

import Models.Test.Question.QCM;
import Models.Test.Question.QuestionSchema;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class QCMController implements HelloController.InitializeDataWithQuestions {

    @FXML
    private TextField propsitionField;

    @FXML
    private TextField questionField;

    @FXML
    private CheckBox value;


    private QCM qcm = new QCM();
    @FXML
    @Override
    public void initializeWithQuestions(ArrayList<QuestionSchema> questions){


    };


    @FXML
    private void addProposition(ActionEvent event) {
        if (value.isSelected()) {
            qcm.addpropositions(propsitionField.getText(), Boolean.TRUE);
            System.out.println(propsitionField.getText());


        } else {
            qcm.addpropositions(propsitionField.getText(), Boolean.FALSE);
            System.out.println(propsitionField.getText());
        }

        propsitionField.clear();
        value.setSelected(false);
    }

    @FXML
    private void submitQCM(ActionEvent event) throws IOException {
        addProposition(event);
        qcm.setEnonce(questionField.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionsTestCreation.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Liste des tests");
        stage.show();
    }

    public void loadQuestion(String question) {
        questionField.setText(question);
        // Add code to populate propositions and correct answer
    }
}
