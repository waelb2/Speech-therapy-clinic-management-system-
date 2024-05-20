package Models.patient;
import java.io.Serializable;
import java.time.*;

public abstract class PatientSchema implements Serializable {
    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;
    private String lieuDeNaissance;
    private String adresse;

    public PatientSchema(String nom, String prenom, LocalDate dateDeNaissance,String lieuDeNaissance,  String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.lieuDeNaissance = lieuDeNaissance;
        this.adresse = adresse;
    }
    public PatientSchema(String nom , String preonm, LocalDate dateDeNaissance){
        this.nom = nom;
        this.prenom = preonm;
    }


    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }
    public String getAdresse() {
        return adresse;
    }
    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }
    public void setLieuDeNaissance(String lieuDeNaissance){
        this.lieuDeNaissance = lieuDeNaissance;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
