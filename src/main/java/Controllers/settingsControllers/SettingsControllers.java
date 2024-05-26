package Controllers.settingsControllers;




import Models.Ortophoniste.OrtophonisteSchema;
import Utils.Popups;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SettingsControllers implements HelloController.InitializeData {
    private OrtophonisteSchema orthophoniste ;
    @FXML
    Label orthoField;
    @FXML
    TextField tf_mdp;
    @FXML
    TextField tf_adr;
    @FXML
    TextField tf_tele;
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
        }
    }
    public void handleUpdatePassword(ActionEvent event) throws  Exception{
        if(tf_mdp.getText().isEmpty()){
            Popups.showErrorMessage("Champ doit etre rempli");
        }else {

            HelloApplication.currentUser.setNumTelephone(tf_mdp.getText());
            HelloApplication.ortophonisteModel.updateOrtophoniste(HelloApplication.currentUser);
            HelloApplication.ortophonisteModel.saveOrthophonistes();
        }
        Popups.showSuccessMessage("sucees", "Mis à jour avec succès");
        HelloController.redirectPage(event, "dashboard.fxml", "Dashbaord");
    }
    public void handleUpdateAdr(ActionEvent event) throws IOException {
        if(tf_adr.getText().isEmpty()){

            Popups.showErrorMessage("Champ doit etre rempli");
        }else {


            HelloApplication.currentUser.setNumTelephone(tf_adr.getText());
            HelloApplication.ortophonisteModel.updateOrtophoniste(HelloApplication.currentUser);
            HelloApplication.ortophonisteModel.saveOrthophonistes();

            Popups.showSuccessMessage("sucees", "Mis à jour avec succès");
            HelloController.redirectPage(event, "dashboard.fxml", "Dashbaord");
        }
    }
    public  void handleUpdateTele(ActionEvent event) throws IOException {
       if(tf_tele.getText().isEmpty()){
           Popups.showErrorMessage("Champ doit etre rempli");
       }else {

           HelloApplication.ortophonisteModel.saveOrthophonistes();

           Popups.showSuccessMessage("sucees", "Mis à jour avec succès");
           HelloController.redirectPage(event, "dashboard.fxml", "Dashbaord");
       }
    }

}
