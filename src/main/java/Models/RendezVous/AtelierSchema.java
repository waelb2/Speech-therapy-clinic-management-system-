package Models.RendezVous;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AtelierSchema  extends  RendezVousSchema implements Serializable {
    private  String thematique ;
    private ArrayList<String> listPatients;
    public AtelierSchema(LocalDate date, String heure, String duree ,  String observation , String thematique, ArrayList<String> listPatients) {
        super(date, heure,  observation, duree );
        this.listPatients = listPatients;
        this.thematique = thematique;
    }

    public String getThematique() {return thematique;}
    public ArrayList<String> getListPatients(){return listPatients;}
    public void setThematique(String thematique){this.thematique = thematique;}
    public void setListPatients(ArrayList<String> listPatients) {this.listPatients = listPatients;}
}
