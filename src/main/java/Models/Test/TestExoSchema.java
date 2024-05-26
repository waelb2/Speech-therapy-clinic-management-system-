package Models.Test;

import Models.Test.Exercice.ExerciceSchema;

import java.io.Serializable;
import java.util.ArrayList;

public class TestExoSchema extends TestSchema implements Serializable {
    private ArrayList<ExerciceSchema> exercices;
    private double scoreMoy;

    public TestExoSchema(String nom,ArrayList<ExerciceSchema> exercices, double scoreMoy) {
        super(nom);
        this.exercices = exercices;
        this.scoreMoy = scoreMoy;
    }
    public TestExoSchema(String nom){
        super(nom);
    }

    public ArrayList<ExerciceSchema> getExercices() {
        return exercices;
    }

    public void setExercices(ArrayList<ExerciceSchema> exercices) {
        this.exercices = exercices;
    }

    public double getScoreMoy() {
        return scoreMoy;
    }

    public void setScoreMoy(double scoreMoy) {
        this.scoreMoy = scoreMoy;
    }

    public double calculScore(ArrayList<ExerciceSchema> exercices) {
        int scoreMoy = 0;
        int j = 0;
        for (int i = 0; i < exercices.size(); i++) {
            scoreMoy = exercices.get(i).getScore();
            j++;
        }
        scoreMoy = scoreMoy / j;
        return scoreMoy;
    }

    public TestExoSchema(String nom, ArrayList<ExerciceSchema> exercices) {
        super(nom);
        this.exercices = exercices;
        this.scoreMoy= calculScore(exercices);
    }
}