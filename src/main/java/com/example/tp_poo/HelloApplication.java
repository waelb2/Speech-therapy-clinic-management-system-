package com.example.tp_poo;

import Models.Ortophoniste.OrtophonisteModel;
import Models.Ortophoniste.OrtophonisteSchema;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.TreeMap;

public class HelloApplication extends Application {

    private static TreeMap<String , OrtophonisteSchema> users  = new TreeMap<>();

    public static OrtophonisteModel ortophonisteModel = new  OrtophonisteModel(users);
    final int WIDTH_SIZE = 680;
    final int HEIGHT_SIZE= 500;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),WIDTH_SIZE, HEIGHT_SIZE);
        stage.setTitle("OrthoTech");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void init() throws Exception {
        OrtophonisteSchema ortho1 = new OrtophonisteSchema("nom1", "prenom1", "lw_bouguessa@esi.dz", "password1", "adresse1", "numTelephone1");
        OrtophonisteSchema ortho2 = new OrtophonisteSchema("nom2", "prenom2", "email2", "password2", "adresse2", "numTelephone2");
        OrtophonisteSchema ortho3 = new OrtophonisteSchema("nom3", "prenom3", "email3", "password3", "adresse3", "numTelephone3");
        OrtophonisteSchema ortho4 = new OrtophonisteSchema("nom4", "prenom4", "email4", "password4", "adresse4", "numTelephone4");
        OrtophonisteSchema ortho5 = new OrtophonisteSchema("nom5", "prenom5", "email5", "password5", "adresse5", "numTelephone5");
        ortophonisteModel.createOrtophoniste(ortho1);
        ortophonisteModel.createOrtophoniste(ortho2);
        ortophonisteModel.createOrtophoniste(ortho3);
        ortophonisteModel.createOrtophoniste(ortho4);
        ortophonisteModel.createOrtophoniste(ortho5);
    }
    public static void main(String[] args) {
        launch();
    }
}