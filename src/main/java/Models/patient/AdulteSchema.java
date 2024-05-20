package Models.patient;

public class AdulteSchema extends PatientSchema{
    private String diplome;
    private String profession;
    private String[] numTel;

    public AdulteSchema(String nom, String prenom, String dateDeNaissance, String adresse, String diplome, String profession, String[] numTel) {
        super(nom, prenom, dateDeNaissance, adresse);
        this.diplome = diplome;
        this.profession = profession;
        this.numTel = numTel;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String[] getNumTel() {
        return numTel;
    }

    public void setNumTel(String[] numTel) {
        this.numTel = numTel;
    }
}
