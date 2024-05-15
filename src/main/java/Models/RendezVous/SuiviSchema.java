package Models.RendezVous;
import Models.RendezVous.TypeSuivi;

import java.time.LocalDate;

public class SuiviSchema extends  RendezVousSchema{
   private  int numDossier ;
   private TypeSuivi typeSuivi ;

   public SuiviSchema(LocalDate date, String heure  , int numDossier, TypeSuivi typeSuivi, String observation){
       super(date, heure, observation );
       this.numDossier = numDossier;
       this.typeSuivi = typeSuivi;
   }

   public int getNumDossier() {
       return numDossier;
   }
   public TypeSuivi getTypeSuivi() {
       return typeSuivi;
   }
   public void setNumDossier(int numDossier) {
       this.numDossier = numDossier;
   }
   public  void setTypeSuivi(TypeSuivi typeSuivi){
       this.typeSuivi = typeSuivi;
   }
}
