package Models.Anamnese;

import java.util.ArrayList;

public class QAChild extends AnamneseQuestion{
    private AnamnQstCateChild categorie;

    public QAChild(String questionText, AnamnQstCateChild categorie) {
        super(questionText);
        this.categorie = categorie;
    }

    public AnamnQstCateChild getCategorie() {
        return categorie;
    }

    public void setCategorie(AnamnQstCateChild categorie) {
        this.categorie = categorie;
    }

    public void initializeWithQuestionsAnam(ArrayList<QAChild> questions){
    };
}
