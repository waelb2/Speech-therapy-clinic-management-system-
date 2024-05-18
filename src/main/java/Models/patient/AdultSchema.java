package Models.patient;

import java.time.LocalDate;

public class AdultSchema extends  PatientSchema {
    private String diplome ;
    private String profession;

    public AdultSchema(String nom, String prenom, LocalDate dateDeNaissance, String adresse, String diplome, String profession) {
        super(nom, prenom, dateDeNaissance, adresse);
        this.diplome = diplome;
        this.profession = profession;
    }

    public String getDiplome(){
        return diplome;
    }
    public String getProfession(){
        return  diplome;
    }

    public void setDiplome(String diplome){
        this.diplome = diplome;
    }
    public void setProfession(String profession){
        this.profession = profession;
    }

}
