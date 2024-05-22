package Models.RendezVous;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class RendezVousSchema implements Comparable<RendezVousSchema> , Serializable {
    private LocalDate date;
    private String heure;
    private String observation ;
    private String duree;

    public RendezVousSchema(LocalDate date, String heure,String observation, String duree){
        this.date = date;
        this.heure = heure;
        this.duree = duree;
        this.observation = observation;
    }
    @Override
    public int compareTo(RendezVousSchema o) {
        return this.date.compareTo(o.date) == 0 ? this.heure.compareTo(o.heure) : this.date.compareTo(o.date);
    }

    public LocalDate getDate() {
        return date;
    }
    public String getHeure() {
        return heure;
    }
    public  String getDuree(){
        return duree;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setHeure(String heure) {
        this.heure = heure;
    }
    public void setDuree(String duree) {this.duree=duree;}
}
