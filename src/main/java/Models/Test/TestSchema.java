package Models.Test;

public abstract class TestSchema {
    private String nom;
    private String observation;

    public TestSchema(String nom, String observation) {
        this.nom = nom;
        this.observation = observation;
    }

    public TestSchema() {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
