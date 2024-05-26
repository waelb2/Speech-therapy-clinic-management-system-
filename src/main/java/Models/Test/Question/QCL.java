package Models.Test.Question;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.TreeMap;

public class QCL extends QuestionSchema{
    private String reponseType;
    private ArrayList<String> propositions;



    public QCL(String enoncé, int score, String reponseType) {
        super(enoncé, score);
        this.reponseType = reponseType;
    }

    public String getReponseType() {
        return reponseType;
    }

    public void setReponseType(String reponseType) {
        this.reponseType = reponseType;
    }



    public QCL() {
        this.propositions= new ArrayList<>();
    }

    public QCL(String enonce, int score) {
        super(enonce, score);
    }

    public QCL(String enoncé, int score, ArrayList<String> propositions ) {
        super(enoncé, score);
        this.propositions = propositions;
    }

    public ArrayList<String>  getPropositions() {
        return propositions;
    }


    public void setReponses(ArrayList<String> propositions) {
        this.propositions = propositions;
    }

    public void addpropositions(String prop, boolean bool){
        this.propositions.add(prop);
    }

    public void delProposition(int ind){
        propositions.remove(ind);

    }
    // TODO : this is a test method, remove it
    public void show(){
        System.out.println(this.getEnoncé()+" les propositions : " + this.propositions);
    }
}


