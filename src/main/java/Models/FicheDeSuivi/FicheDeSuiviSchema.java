package Models.FicheDeSuivi;

import Models.DossierPatient.DossierPatientSchema;
import Models.Objectif.ObjectifModel;
import Models.Objectif.ObjectifSchema;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class FicheDeSuiviSchema implements Serializable {
    private ArrayList<ObjectifSchema> objectifSchema;
    public FicheDeSuiviSchema(ArrayList<ObjectifSchema> objectifSchema) {
        this.objectifSchema = objectifSchema;
    }

    public ArrayList<ObjectifSchema> getObjectifs() {
        return objectifSchema;
    }
    public void setObjectifs(ArrayList<ObjectifSchema> objectifSchema) {
        this.objectifSchema = objectifSchema;
    }
    public static void  redirectToFicheSuivi(Event event, FicheDeSuiviSchema fiche ,DossierPatientSchema dossier,String nom , String prenom, String fileName, String title) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fileName));
        Parent root = (Parent) fxmlLoader.load();


        Object controller= fxmlLoader.getController();
        if(controller instanceof HelloController.InitializeWithFiche) {
            ((HelloController.InitializeWithFiche) controller).initializeWithFiche(fiche, dossier,  nom,  prenom);
        }
        Stage stage =(Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();    }

}
