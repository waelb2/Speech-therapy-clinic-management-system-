package Models.Ortophoniste;

public class OrtophonisteSchema {
    private String nom ;
    private String prenom;
    private String email;
    private String motDePasse ;
    private String adresse ;
    private String numTelephone ;

    public OrtophonisteSchema(String nom, String prenom, String email, String motDePasse, String adresse, String numTelephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.adresse = adresse;
        this.numTelephone = numTelephone;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public String getEmail() {
        return email;
    }
    public String getAdresse() {
        return adresse;
    }
    public String getNumTelephone() {
        return numTelephone;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
