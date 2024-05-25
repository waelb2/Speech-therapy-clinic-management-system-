package Controllers.dashboardControllers;

import Models.DossierPatient.DossierPatientSchema;
import Models.Ortophoniste.OrtophonisteSchema;
import Models.RendezVous.AtelierSchema;
import Models.RendezVous.ConsultationSchema;
import Models.RendezVous.RendezVousSchema;
import com.example.tp_poo.HelloApplication;
import com.example.tp_poo.HelloController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DashboardController  implements HelloController.InitializeData  {
    private  OrtophonisteSchema orthophoniste ;
    @FXML
    Label orthoField;
    @FXML
    ListView rdvsList;
    private  String orthoNom  ;
    private  String orthoPrenom  ;
    private final ObservableList<RendezVousSchema> rdvs= FXCollections.observableArrayList();

    private boolean isInitialized = false;
    @FXML
    @Override
    public  void initialize(){
        this.orthophoniste = HelloApplication.currentUser;
        this.orthoNom  = orthophoniste.getNom();
        this.orthoPrenom = orthophoniste.getPrenom();
        orthoField.setText(" " + orthoNom + " " + orthoPrenom);
        if (isInitialized) return; // Skip if already initialized


        rdvs.addAll(HelloApplication.rendezVousModel.getRendezVoussByDate(LocalDate.now()));
        rdvsList.setCellFactory(new Callback<ListView<RendezVousSchema>, ListCell<RendezVousSchema>>() {
            @Override
            public ListCell<RendezVousSchema> call(ListView<RendezVousSchema> listView) {
                return new ListCell<RendezVousSchema>() {

                    @Override
                    protected void updateItem(RendezVousSchema rdv, boolean empty) {
                        super.updateItem(rdv, empty);
                        if (empty || rdv == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            Label rdvType = new Label();
                            if (rdv instanceof ConsultationSchema) {
                                rdvType.setText("Consultation");
                            }
                            else if (rdv instanceof AtelierSchema) {
                                rdvType.setText("Atelier");
                            }else {
                                rdvType.setText("Suivi");
                            }
                            HBox hBox = new HBox(10);
                            Label heureLabel = new Label(rdv.getHeure());
                            Label dureeLabel= new Label(rdv.getDuree());
                            Button btn = new Button("Passer");

                            heureLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-pref-width:125.4 ; -fx-pref-height: 34.4; -fx-alignment:center ");
                            dureeLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-pref-width: 110.6; -fx-pref-height: 36; -fx-alignment: center;");
                            rdvType.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-pref-width: 124.4; -fx-pref-height: 36; -fx-alignment: center;");
                            hBox.setStyle(" -fx-pref-height: 36; ; -fx-background-color: #fff; -fx-spacing: 10;   -fx-padding: 5;");
                            btn.setStyle("-fx-background-color: #1588ea; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight:bold; -fx-pref-width: 80; -fx-pref-height: 36;  -fx-alignment: center; -fx-cursor:hand;");

                            HBox btnBox = new HBox();
                            btnBox.getChildren().add(btn);
                            btnBox.setMargin(btn, new Insets(0, 0, 0, 65));

                            // getting dossier patient based on rdv
                            String directoryPath = HelloApplication.currentUserDir +"/dossiersPatients"; // Adjust this path as needed

                                btn.setOnAction(event -> {
                                try {
                                    try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath), "*.dat")) {
                                        Pattern pattern = Pattern.compile("(.+?)_(.+?)\\.dat");
                                        for (Path entry : stream) {
                                            String fileName = entry.getFileName().toString();
                                            Matcher matcher = pattern.matcher(fileName);
                                            if (matcher.matches()) {
                                                String nom = matcher.group(1);
                                                String prenom = matcher.group(2);

                                                // Load the DossierPatientSchema object (if needed)
                                                DossierPatientSchema dossier = DossierPatientSchema.loadDossierPatient(HelloApplication.currentUser.getEmail(), nom, prenom);
                                                // Process the dossier as needed
                                                TreeSet <RendezVousSchema> rdvs = dossier.getRendezVousPatient();
                                                for (RendezVousSchema rdv1 : rdvs) {
                                                    if(rdv.compareTo(rdv1) == 0){
                                                       DossierPatientSchema.redirectToDossierPatient(event, dossier,rdvType.getText(),nom, "creerBilan.fxml", "Créer Bilan Orthophonique");
                                                       return;
                                                    }
                                                }
                                            }
                                        }
                                        // redirect to dossier patient

                                    };
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });

                            hBox.getChildren().addAll(rdvType, heureLabel, dureeLabel, btn );
                            setGraphic(hBox);
                        }
                    }
                };
            }
        });


        rdvsList.setItems(rdvs);
        isInitialized = true;
    };
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
