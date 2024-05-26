package Controllers.statsControllers;

import Models.BilanOrthophonique.BilanOrthophoniqueSchema;
import Models.DossierPatient.DossierPatientSchema;
import Models.Ortophoniste.OrtophonisteSchema;
import Models.Trouble.TroubleCategories;
import Models.Trouble.TroubleSchema;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

public class StatsController implements  HelloController.InitializeData{
    private OrtophonisteSchema orthophoniste ;
    @FXML
    Label orthoField;
    @FXML
    Label nombrePatients;
    @FXML
    Label nombreRdvs;
    @FXML
    Label troubleD;
    @FXML
    Label troubleC;
    @FXML
    Label troubleN;
    @FXML
    Label pourcentageD;
    @FXML
    Label pourcentageC;
    @FXML
    Label pourcentageN;
    private  String orthoNom  ;
    private  String orthoPrenom  ;

    boolean init = false;
    @FXML
    @Override
    public  void initialize(){
        if (init) return;
        this.orthophoniste = HelloApplication.currentUser;
        this.orthoNom  = orthophoniste.getNom();
        this.orthoPrenom = orthophoniste.getPrenom();
        orthoField.setText(" " + orthoNom + " " + orthoPrenom);
        this.nombrePatients.setText(String.valueOf(HelloApplication.patientModel.countPatients()));
        this.nombreRdvs.setText(String.valueOf(HelloApplication.rendezVousModel.countRdv()));


        ArrayList<DossierPatientSchema> dossiers = new ArrayList<>(HelloApplication.dossierPatientModel.getDossiers());
        // stats
        Map<TroubleCategories, Integer> categoryCounts = new EnumMap<>(TroubleCategories.class);



        for (DossierPatientSchema dossier : dossiers) {
            ArrayList<BilanOrthophoniqueSchema> bos = dossier.getbO();
            for (BilanOrthophoniqueSchema bo : bos) {
                ArrayList<TroubleSchema> troubles = bo.getDiagnostiques();
                for (TroubleSchema trouble : troubles) {
                    TroubleCategories category = trouble.getCategorie();
                    categoryCounts.put(category, categoryCounts.getOrDefault(category, 0) + 1);
                }
            }
        }

        this.troubleC.setText(String.valueOf(categoryCounts.getOrDefault(TroubleCategories.cognitifs, 0)));
        this.troubleD.setText(String.valueOf(categoryCounts.getOrDefault(TroubleCategories.Déglutition, 0)));
        this.troubleN.setText(String.valueOf(categoryCounts.getOrDefault(TroubleCategories.neuroDeveloppementaux, 0)));

        int totalDossiers = dossiers.size();

        int cognitifsCount = categoryCounts.getOrDefault(TroubleCategories.cognitifs, 0);
        int deglutitionCount = categoryCounts.getOrDefault(TroubleCategories.Déglutition, 0);
        int neuroDeveloppementauxCount = categoryCounts.getOrDefault(TroubleCategories.neuroDeveloppementaux, 0);

        this.pourcentageC.setText(String.valueOf(cognitifsCount * 100 / totalDossiers));
        this.pourcentageD.setText(String.valueOf(deglutitionCount * 100 / totalDossiers));
        this.pourcentageN.setText(String.valueOf(neuroDeveloppementauxCount * 100 / totalDossiers));


        init = true;
    }
    public void handlePatientsClick(ActionEvent evet) {
        try{
            HelloController.redirectPage(evet , "dossiersPatients.fxml", "Mes patients");
        }catch (Exception e){
            e.getMessage();
        }
    }
    public void handleAddRdvClick(ActionEvent evet) {
        try{
            HelloController.redirectPage(evet , "rdv.fxml", "Créer rendez-vous");
        }catch (Exception e){
            e.getMessage();
        }
    }
    public void handleStatsClick(ActionEvent event) {
        try{
            HelloController.redirectPage(event , "stats.fxml", "Mes Statistiques");
        }catch (Exception e){
            e.getMessage();
        }
    }
    public void handleSettingsClick(ActionEvent event) {
        try{
            HelloController.redirectPage(event, "parametres.fxml", "Mes paramètres");
        }catch (Exception e){
            e.getMessage();
        }
    }
    public void handleLogoClick(MouseEvent event) {
        try{
            HelloController.redirectPage(event, "dashboard.fxml", "Dashboard");
        }catch (Exception e){
            e.getMessage();
        }
    }
    public void handleTestsClick(ActionEvent event) {
        try{
            HelloController.redirectPage(event, "testAnam.fxml", "Testes et Anamnèses");
        }catch (Exception e){
            e.getMessage();
        }}

}
