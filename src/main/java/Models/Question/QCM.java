package Models.Question;

import java.util.ArrayList;

public class QCM extends QuestionSchema{
    private ArrayList<String> reponses;

    public QCM(String enoncé, int score, ArrayList<String> reponses) {
        super(enoncé, score);
        this.reponses = reponses;
    }

    public ArrayList<String> getReponses() {
        return reponses;
    }

    public void setReponses(ArrayList<String> reponses) {
        this.reponses = reponses;
    }

    public void addReponses(String rep){
        this.reponses.add(rep);
    }

    public void delReponse(int ind){
        reponses.remove(ind);

    }
}
