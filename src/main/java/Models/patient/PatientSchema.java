package Models.patient;

public abstract class PatientSchema {
    private String nom;
    private String prenom;
    private String dateDeNaissance;
    private String adresse;

    public PatientSchema(String nom, String prenom, String dateDeNaissance, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getDateDeNaissance() {
        return dateDeNaissance;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
