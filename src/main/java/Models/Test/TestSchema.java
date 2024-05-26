package Models.Test;

public abstract class TestSchema {
    private String nom;

    public TestSchema(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


}
