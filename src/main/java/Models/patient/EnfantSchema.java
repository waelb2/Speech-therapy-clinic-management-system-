package Models.patient;

public class EnfantSchema  extends  PatientSchema{
    private String classeEtude ;
    private String[] numParents ;

    public EnfantSchema(String nom, String prenom, String dateDeNaissance, String adresse, String classeEtude, String[] numParents) {
        super(nom, prenom, dateDeNaissance, adresse);
        this.classeEtude = classeEtude;
        this.numParents = numParents;
    }

    public String getClasseEtude() {
        return classeEtude;
    }
    public String[] getNumParents() {
        return numParents;
    }
    public void setClasseEtude(String classeEtude) {
        this.classeEtude = classeEtude;
    }
    public void setNumParents(String[] numParents) {
        this.numParents = numParents;
    }
}
