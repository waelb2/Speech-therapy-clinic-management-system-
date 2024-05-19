package Models.BilanOrthophonique;

import java.util.ArrayList;

public class BilanOrthophoniqueSchema {
    private Anamnese anamnese;
    //private ArrayList<EpreuveClinique> epreuvesCliniques;
    //private ArrayList<Trouble> diagnostiques;
    private String projetTherapeutique;


    public BilanOrthophoniqueSchema(Anamnese anamnese, String projetTherapeutique) {
        this.anamnese = anamnese;
        this.projetTherapeutique = projetTherapeutique;
    }

    public Anamnese getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(Anamnese anamnese) {
        this.anamnese = anamnese;
    }

    public String getProjetTherapeutique() {
        return projetTherapeutique;
    }

    public void setProjetTherapeutique(String projetTherapeutique) {
        this.projetTherapeutique = projetTherapeutique;
    }
}
