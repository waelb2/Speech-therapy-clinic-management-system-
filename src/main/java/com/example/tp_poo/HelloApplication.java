package com.example.tp_poo;

import Models.DossierPatient.DossierPatientModel;
import Models.DossierPatient.DossierPatientSchema;
import Models.Ortophoniste.OrtophonisteModel;
import Models.Ortophoniste.OrtophonisteSchema;
import Models.RendezVous.RendezVousModel;
import Models.RendezVous.RendezVousSchema;
import Models.Test.TestModel;
import Models.patient.PatientModel;
import Models.patient.PatientSchema;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.TreeMap;
import java.util.TreeSet;

public class HelloApplication extends Application {

    // init needed data
    public static TreeMap<String , OrtophonisteSchema> users  = new TreeMap<>();
    public static TreeMap<String, PatientSchema> patients = new TreeMap<>();
    public static TreeSet<RendezVousSchema> rendezVouss = new TreeSet<>();
    public static TreeMap<Integer, DossierPatientSchema> dossiersPatients = new TreeMap<>();
    public static OrtophonisteModel ortophonisteModel = new  OrtophonisteModel(users);
    public static PatientModel patientModel = new PatientModel(patients);
    public static RendezVousModel rendezVousModel = new RendezVousModel(rendezVouss);
    public static DossierPatientModel dossierPatientModel = new DossierPatientModel(dossiersPatients);
    public static TestModel testModel = new TestModel();
    public static String  orthophonistesDir = "./data/orthophonistes";
    public static String appUsersDir = "./data/app_users";
    public static String currentUserDir ;
    public static OrtophonisteSchema currentUser = null ;
    final int WIDTH_SIZE = 800;
    final int HEIGHT_SIZE= 600;
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
        // load app users
        ortophonisteModel.loadOrthophoniste();
    }
    @Override
    public  void stop() throws Exception{
        // save orthophonistes file
        ortophonisteModel.saveOrthophonistes();
        //save patients file
        patientModel.savePatients();
        //save rendez-vous file
        rendezVousModel.saveRendezVous();
        // save dossierPatients file
        dossierPatientModel.saveDossierPatient();
        testModel.saveTests();
        super.stop();
    }
    public static void main(String[] args) {
        launch();
    }
}