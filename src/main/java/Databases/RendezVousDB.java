package Databases;

import Models.RendezVous.RendezVousSchema;

import java.time.LocalDate;

public interface RendezVousDB {
    public RendezVousSchema createRendezVous(RendezVousSchema rendezVous);
    public RendezVousSchema deleteRendezVous(LocalDate date, String heure);
    public RendezVousSchema updateRendezVous(RendezVousSchema rendezVous);
    public RendezVousSchema findRendezVous(LocalDate date, String heure);
    public boolean RendezVousExist(LocalDate date, String heure);
    public int countRdv ();
}
