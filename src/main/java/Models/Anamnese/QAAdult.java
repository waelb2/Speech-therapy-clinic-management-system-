package Models.Anamnese;

import Models.Test.Question.QuestionSchema;

import java.util.ArrayList;

public class QAAdult extends AnamneseQuestion{
    private AnamnQstCateAdult categorie;

    public QAAdult(String questionText, AnamnQstCateAdult categorie) {
        super(questionText);
        this.categorie = categorie;
    }

    public QAAdult(String enonce) {
        super(enonce);
    }



    public AnamnQstCateAdult getCategorie() {
        return categorie;
    }

    public void setCategorie(AnamnQstCateAdult categorie) {
        this.categorie = categorie;
    }

    public void initializeWithQuestionsAnam(ArrayList<QAAdult> questions){
    };
}
