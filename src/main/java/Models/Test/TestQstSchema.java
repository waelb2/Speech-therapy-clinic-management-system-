package Models.Test;

import Models.Test.Question.QuestionSchema;

import java.io.Serializable;
import java.util.ArrayList;

public class TestQstSchema extends TestSchema implements Serializable {
    private ArrayList<QuestionSchema> questions;
    private double scoreMoy;



    public ArrayList<QuestionSchema> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<QuestionSchema> questions) {
        this.questions = questions;
    }

    public double getScoreMoy() {
        return scoreMoy;
    }

    public void setScoreMoy(double scoreMoy) {
        this.scoreMoy = scoreMoy;
    }

    public double calculScore(ArrayList<QuestionSchema> questions) {
        int scoreMoy = 0;
        int j = 0;
        for (int i = 0; i < questions.size(); i++) {
            scoreMoy = questions.get(i).getScore();
            j++;
        }
        scoreMoy = scoreMoy / j;
        return scoreMoy;
    }

    public TestQstSchema(String nom,ArrayList<QuestionSchema> questions) {
        super(nom);
        this.questions = questions;
        this.scoreMoy = calculScore(questions);
    }
    public TestQstSchema(String nom){
        super(nom);
    }

}
