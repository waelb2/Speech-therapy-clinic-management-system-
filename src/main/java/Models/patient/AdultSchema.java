package Models.patient;

import java.io.Serializable;
import java.time.LocalDate;

public class AdultSchema extends  PatientSchema implements Serializable {
    private String diplome ;
    private String profession;

    public AdultSchema(String nom, String prenom, LocalDate dateDeNaissance,String lieuDeNaissance, String adresse, String diplome, String profession) {
        super(nom, prenom, dateDeNaissance,lieuDeNaissance, adresse);
        this.diplome = diplome;
        this.profession = profession;
    }

    public String getDiplome(){
        return diplome;
    }
    public String getProfession(){
        return  profession;
    }

    public void setDiplome(String diplome){
        this.diplome = diplome;
    }
    public void setProfession(String profession){
        this.profession = profession;
    }

}
