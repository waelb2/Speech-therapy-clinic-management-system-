package Models.Anamnese;

import java.io.Serializable;
import java.util.ArrayList;
import Exceptions.ElementNotFoundException;

public class AnamneseSchema implements Serializable {
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
        if (!this.questionsAnamnese.remove(AQ)) {
            throw new ElementNotFoundException();
        }
    }
}
