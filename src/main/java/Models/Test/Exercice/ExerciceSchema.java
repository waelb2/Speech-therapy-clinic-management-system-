package Models.Test.Exercice;

import Models.Test.TestSchema;

import java.io.Serializable;

public class ExerciceSchema extends TestSchema implements Serializable {
    private String materielRequis;
    private int score;

    public ExerciceSchema(String materielRequis) {
        this.materielRequis = materielRequis;
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
