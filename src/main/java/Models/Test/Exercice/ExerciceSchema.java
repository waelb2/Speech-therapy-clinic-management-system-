package Models.Test.Exercice;

import Models.Test.TestSchema;

import java.io.Serializable;

public class ExerciceSchema   implements Serializable {
    private String nom;
    private String materielRequis;
    private int score;

    public ExerciceSchema() {
    }

    public ExerciceSchema(String nom) {
        this.nom = nom;
    }

    public ExerciceSchema(String nom, String materielRequis) {
        this.nom = nom;
        this.materielRequis = materielRequis;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getMaterielRequis() {
        return materielRequis;
    }

    public void setMaterielRequis(String materielRequis) {
        this.materielRequis = materielRequis;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
