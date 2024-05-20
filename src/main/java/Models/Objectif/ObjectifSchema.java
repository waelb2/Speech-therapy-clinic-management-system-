package Models.Objectif;

import java.io.Serializable;

public class ObjectifSchema implements Serializable {
    private String nom;
    private TermeEnum duree;
    private int note;

    public ObjectifSchema(String nom, TermeEnum duree) {
        this.nom = nom;
        this.duree = duree;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TermeEnum getDuree() {
        return duree;
    }

    public void setDuree(TermeEnum duree) {
        this.duree = duree;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}

