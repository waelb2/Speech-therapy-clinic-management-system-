package Models.BilanOrthophonique;

import Exceptions.ElementNotFoundException;
import Models.Anamnese.AnamneseQuestion;
import Models.Anamnese.AnamneseSchema;
import Models.EpreuveClinique.EpreuveCliniqueSchema;
import Models.Trouble.TroubleSchema;

import java.util.ArrayList;

public class BilanOrthophoniqueSchema {
    private AnamneseSchema anamnese;
    private ArrayList<EpreuveCliniqueSchema> epreuvesCliniques;
    private ArrayList<TroubleSchema> diagnostiques;
    private String projetTherapeutique;


    public BilanOrthophoniqueSchema(AnamneseSchema anamnese, String projetTherapeutique) {
        this.anamnese = anamnese;
        this.projetTherapeutique = projetTherapeutique;
    }

    public BilanOrthophoniqueSchema(AnamneseSchema anamnese, ArrayList<EpreuveCliniqueSchema> epreuvesCliniques, ArrayList<TroubleSchema> diagnostiques, String projetTherapeutique) {
        this.anamnese = anamnese;
        this.epreuvesCliniques = epreuvesCliniques;
        this.diagnostiques = diagnostiques;
        this.projetTherapeutique = projetTherapeutique;
    }

    public AnamneseSchema getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(AnamneseSchema anamnese) {
        this.anamnese = anamnese;
    }

    public String getProjetTherapeutique() {
        return projetTherapeutique;
    }

    public void setProjetTherapeutique(String projetTherapeutique) {
        this.projetTherapeutique = projetTherapeutique;
    }

    public ArrayList<EpreuveCliniqueSchema> getEpreuvesCliniques() {
        return epreuvesCliniques;
    }

    public void setEpreuvesCliniques(ArrayList<EpreuveCliniqueSchema> epreuvesCliniques) {
        this.epreuvesCliniques = epreuvesCliniques;
    }

    public ArrayList<TroubleSchema> getDiagnostiques() {
        return diagnostiques;
    }

    public void setDiagnostiques(ArrayList<TroubleSchema> diagnostiques) {
        this.diagnostiques = diagnostiques;
    }

    public void addEC(EpreuveCliniqueSchema ec){
        this.epreuvesCliniques.add(ec);
    }

    public void delEC(EpreuveCliniqueSchema ec) throws ElementNotFoundException {
        if(!this.epreuvesCliniques.remove(ec)) {
            throw new ElementNotFoundException() ;
        }
    }

    public void addDiagnostique(TroubleSchema tr){
        this.diagnostiques.add(tr);
    }

    public void delDiagnotique(TroubleSchema tr) throws ElementNotFoundException {
        if(!this.diagnostiques.remove(tr)) {
            throw new ElementNotFoundException() ;
        }
    }


}
