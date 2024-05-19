package Controllers.dashboardControllers;

import Models.Ortophoniste.OrtophonisteSchema;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


public class DashboardController  implements HelloController.InitializeData  {
    private  OrtophonisteSchema orthophoniste ;
    @FXML
    Label orthoField;
    private  String orthoNom  ;
    private  String orthoPrenom  ;

    @FXML
    @Override
    public  void initialize(){
        this.orthophoniste = HelloApplication.currentUser;
        this.orthoNom  = orthophoniste.getNom();
        this.orthoPrenom = orthophoniste.getPrenom();
        orthoField.setText(" " + orthoNom + " " + orthoPrenom);
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
