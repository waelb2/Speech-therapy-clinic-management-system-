package Models.Test.Question;

import java.util.ArrayList;
import java.util.TreeMap;

public class QCU extends QuestionSchema {
    private ArrayList<String> reponses;
    private TreeMap<String, Boolean> propositions;
    public QCU() {
        this.propositions= new TreeMap<>();
    }

    public QCU(String enonce, int score) {
        super(enonce, score);
    }

    public QCU(String enoncé, int score, TreeMap<String, Boolean> propositions ) {
        super(enoncé, score);
        this.propositions = propositions;
    }

    public TreeMap<String, Boolean>  getPropositions() {
        return propositions;
    }


    public void setReponses(TreeMap<String, Boolean> propositions) {
        this.propositions = propositions;
    }

    public void addpropositions(String prop, boolean bool){
        this.propositions.put(prop, bool);
    }

    public void delProposition(int ind){
        propositions.remove(ind);

    }
    // TODO : this is a test method, remove it
    public void show(){
        System.out.println(this.getEnoncé()+" les propositions : " + this.propositions);
    }
}

