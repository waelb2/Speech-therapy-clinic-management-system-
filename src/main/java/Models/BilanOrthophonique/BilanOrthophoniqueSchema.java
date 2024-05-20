package Models.BilanOrthophonique;

import Models.Anamnese.AnamneseSchema;

import java.io.Serializable;
import java.util.ArrayList;

public class BilanOrthophoniqueSchema implements Serializable {
    private AnamneseSchema anamnese;
    //private ArrayList<EpreuveCliniqueSchema> epreuvesCliniques;
    //private ArrayList<Trouble> diagnostiques;
    private String projetTherapeutique;


    public BilanOrthophoniqueSchema(AnamneseSchema anamnese, String projetTherapeutique) {
        this.anamnese = anamnese;
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
}
