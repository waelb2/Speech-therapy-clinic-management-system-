package Models.Anamnese;

import java.io.Serializable;
import java.util.ArrayList;
import Exceptions.ElementNotFoundException;

public class AnamneseSchema implements Serializable {
    private String titre;
    private ArrayList<AnamneseQuestion> questionsAnamnese;

    public AnamneseSchema(String titre, ArrayList<AnamneseQuestion> questionsAnamnese) {
        this.titre = titre;
        this.questionsAnamnese = questionsAnamnese;
    }

    public AnamneseSchema(String titre) {
        this.titre = titre;
    }

    public AnamneseSchema() {
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public ArrayList<AnamneseQuestion> getQuestionsAnamnese() {
        return questionsAnamnese;
    }

    public void setQuestionsAnamnese(ArrayList<AnamneseQuestion> questionsAnamnese) {
        this.questionsAnamnese = questionsAnamnese;
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
