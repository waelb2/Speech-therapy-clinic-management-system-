package com.example.tp_poo;

import Controllers.dashboardControllers.DashboardController;
import Controllers.patientsControllers.DossiersPatientsController;
import Models.DossierPatient.DossierPatientSchema;
import Models.FicheDeSuivi.FicheDeSuiviSchema;
import Models.Ortophoniste.OrtophonisteSchema;
import Models.Test.Exercice.ExerciceSchema;
import Models.Test.Question.QuestionSchema;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class HelloController {
    @FXML
    private Label welcomeText;

    public static void redirectWithData(Event event,ConsultationObject consultationObject, String fileName, String title)   throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fileName));
        Parent root = (Parent) fxmlLoader.load();

        Object controller= fxmlLoader.getController();
        if(controller instanceof InitializeDataWithObject) {
            ((InitializeDataWithObject) controller).initializeWithData(consultationObject);
        }

        Stage stage =(Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    public static void redirectPage(Event event, String fileName, String title)   throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fileName));
        Parent root = (Parent) fxmlLoader.load();

         Object controller= fxmlLoader.getController();
            if(controller instanceof InitializeData) {
           ((InitializeData) controller).initialize();
             }
        Stage stage =(Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }



   public  interface  InitializeData {
        void initialize();
    }
    public interface InitializeDataWithObject {
        void initializeWithData(ConsultationObject consultationObject);
    }
    public interface InitializeWithDossierPatient{
       void initializeWithDossierPatient(DossierPatientSchema dossierPatient, String nom, String prenom);

    }
    public interface InitializeWithFiche {
        void initializeWithFiche(FicheDeSuiviSchema fiche, DossierPatientSchema dossierPatient, String nom, String prenom);
    }
    public interface InitializeDataWithQuestions{
        void initializeWithQuestions(ArrayList<QuestionSchema> questions);
    }
    public interface InitializeDataWithExercices{
        void initializeWithExercices(ArrayList<ExerciceSchema> exercices);
    }
    //dashboardController.initilize(orthophoniste);
     public static class ConsultationObject {
        String nom ;
        String prenom;
        String age;
        LocalDate date;
        String heure;
        String min;

        public ConsultationObject(String nom, String prenom, String age, LocalDate date, String heure, String min) {
            this.nom = nom;
            this.prenom = prenom;
            this.age = age;
            this.date = date;
            this.heure = heure;
            this.min = min;
        }

        public String getNom() {
            return nom;
        }
        public String getPrenom() {
            return prenom;
        }
        public String getAge() {
            return age;
        }
        public LocalDate getDate() {
            return date;
        }
        public String getHeure() {
            return heure;
        }
        public String getMin() {
            return min;
        }

}


}