package Models.RendezVous;

import Models.patient.PatientSchema;

import java.time.LocalDate;

public class ConsultationSchema  extends  RendezVousSchema{
   private PatientSchema patient ;
   public ConsultationSchema(LocalDate date, String heure, PatientSchema newPatient, String observation) {
       super(date, heure,  observation );
       this.patient = newPatient;
   }

   public PatientSchema getPatient() {
       return patient;
   }
   public void setPatient(PatientSchema patient) {
       this.patient = patient;
   }
}
