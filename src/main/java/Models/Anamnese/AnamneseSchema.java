package Models.Anamnese;

import Exceptions.ElementNotFoundException;

import java.util.ArrayList;

public class AnamneseSchema {
    private ArrayList<AnamneseQuestion> questionsAnamnese;

    public AnamneseSchema(ArrayList<AnamneseQuestion> questionsAnamnese) {
        this.questionsAnamnese = questionsAnamnese;
    }

    public AnamneseSchema() {
    }

    public void addQuestion(AnamneseQuestion AQ) {
       this.questionsAnamnese.add(AQ);
    }

    public void delQuestion(AnamneseQuestion AQ) throws ElementNotFoundException {
       if(!this.questionsAnamnese.remove(AQ)) {
           throw new ElementNotFoundException() ;
       }
    }
}
