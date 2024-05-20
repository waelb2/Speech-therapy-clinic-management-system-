package Models.RendezVous;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class RendezVousSchema implements Comparable<RendezVousSchema> , Serializable {
    private LocalDate date;
    private String heure;
    private String observation ;

    public RendezVousSchema(LocalDate date, String heure,String observation){
        this.date = date;
        this.heure = heure;
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
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setHeure(String heure) {
        this.heure = heure;
    }
}
