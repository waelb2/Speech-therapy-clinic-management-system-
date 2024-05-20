package Models.RendezVous;

import Models.patient.PatientSchema;

import java.io.Serializable;
import java.time.LocalDate;

public class ConsultationSchema  extends  RendezVousSchema implements Serializable {
   private String nom;
   private String prenom;
   private int age;
   public ConsultationSchema(LocalDate date, String heure ,String nom, String prenom,int age, String observation) {
       super(date, heure,  observation );
       this.age= age;
       this.nom = nom;
       this.prenom = prenom;
   }


   public String getNom() {
       return nom;
   }
   public String getPrenom() {
       return prenom;
   }
    public int getAge() {
         return age;
    }
}
