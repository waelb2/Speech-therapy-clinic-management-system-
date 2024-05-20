package Models.RendezVous;

import java.time.LocalDate;

public abstract class RendezVousSchema {
    private LocalDate date;
    private String heure;
    private String observation ;
// TODO : duree ou heure de la fin du rendezVous ??
    public RendezVousSchema(LocalDate date, String heure,String observation){
        this.date = date;
        this.heure = heure;
        this.observation = observation;
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
