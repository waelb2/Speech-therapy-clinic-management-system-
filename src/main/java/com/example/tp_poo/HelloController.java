package com.example.tp_poo;

import Controllers.dashboardControllers.DashboardController;
import Controllers.patientsControllers.DossiersPatientsController;
import Models.Ortophoniste.OrtophonisteSchema;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    public static void redirectWithData(Event event, OrtophonisteSchema orthophoniste, String fileName, String title)   throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fileName));
        Parent root = (Parent) fxmlLoader.load();
       // DashboardController dashboardController = fxmlLoader.getController();
        //dashboardController.initilize(orthophoniste);


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


}