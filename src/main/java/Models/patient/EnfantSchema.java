package Models.patient;
import java.io.Serializable;
import java.time.LocalDate;
public class EnfantSchema  extends  PatientSchema implements Serializable {
    private String classeEtude ;
    private String[] numParents ;

    public EnfantSchema(String nom, String prenom, LocalDate  dateDeNaissance,String lieuDeNaissance, String adresse, String classeEtude, String[] numParents) {
        super(nom, prenom, dateDeNaissance,lieuDeNaissance, adresse);
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
