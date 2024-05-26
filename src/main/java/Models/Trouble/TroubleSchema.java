package Models.Trouble;

import java.io.Serializable;

public class TroubleSchema implements Serializable {
    private String nom;
    private TroubleCategories categorie;


    public TroubleSchema(String nom, TroubleCategories categorie) {
        this.nom = nom;
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TroubleCategories getCategorie() {
        return categorie;
    }

    public void setCategorie(TroubleCategories categorie) {
        this.categorie = categorie;
    }



}
