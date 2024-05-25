package Models.RendezVous;

import Databases.RendezVousDB;
import Models.patient.PatientSchema;
import com.example.tp_poo.HelloApplication;

import java.io.*;
import java.time.LocalDate;
import java.util.TreeMap;
import java.util.TreeSet;

public class RendezVousModel implements RendezVousDB, Serializable {
    TreeSet<RendezVousSchema> rendezVouss = new TreeSet<>();

    public RendezVousModel(TreeSet<RendezVousSchema> rendezVouss) {
        this.rendezVouss = rendezVouss;
    }
    public RendezVousSchema createRendezVous(RendezVousSchema rdv) {
        rendezVouss.add(rdv);
        return rdv;
    }
    public RendezVousSchema findRendezVous(LocalDate date, String heure) {
        for (RendezVousSchema rdv : rendezVouss) {
            if (rdv.getDate().equals(date) && rdv.getHeure().equals(heure)) {
                return rdv;
            }
        }
        return null;
    }
    public RendezVousSchema deleteRendezVous(LocalDate date, String heure) {
        RendezVousSchema rdv = findRendezVous(date, heure);
        rendezVouss.remove(rdv);
        return rdv;
    }
    public RendezVousSchema updateRendezVous(RendezVousSchema rdv) {
        RendezVousSchema oldRdv = findRendezVous(rdv.getDate(), rdv.getHeure());
        rendezVouss.remove(oldRdv);
        rendezVouss.add(rdv);
        return rdv;
    }
    public boolean RendezVousExist(LocalDate date, String heure) {
        return findRendezVous(date, heure) != null;
    }
   public void saveRendezVous(){

       // save rendez-vouss to patients.dat
       try (ObjectOutputStream objectOutputStream= new ObjectOutputStream(new FileOutputStream(HelloApplication.currentUserDir + "/MesRdvs/rdvs.dat"))) {
           objectOutputStream.writeObject(rendezVouss);
       } catch (IOException e) {
           System.out.println(e.getMessage());
       }
   }
   public void loadRendezVous(){
       // Check if the file exists
       File file = new File(HelloApplication.currentUserDir + "/MesRdvs/rdvs.dat");
       if (file.exists()) {
           try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
               rendezVouss = (TreeSet<RendezVousSchema>) objectInputStream.readObject();

           } catch (IOException | ClassNotFoundException e) {
               // Handle exceptions
               System.out.println(e.getMessage());
           }
       } else {
           // File doesn't exist, initialize rendez-vous as an empty TreeMap
              rendezVouss = new TreeSet<>();
       }
   }
   public TreeSet<RendezVousSchema> getRendezVouss() {
        return rendezVouss;
    }
    public void setRendezVouss(TreeSet<RendezVousSchema> rendezVouss) {
        this.rendezVouss = rendezVouss;
    }
    public TreeMap<LocalDate, TreeSet<RendezVousSchema>> getRendezVoussByDate() {
        TreeMap<LocalDate, TreeSet<RendezVousSchema>> rdvsByDate = new TreeMap<>();
        for (RendezVousSchema rdv : rendezVouss) {
            if (rdvsByDate.containsKey(rdv.getDate())) {
                rdvsByDate.get(rdv.getDate()).add(rdv);
            } else {
                TreeSet<RendezVousSchema> rdvs = new TreeSet<>();
                rdvs.add(rdv);
                rdvsByDate.put(rdv.getDate(), rdvs);
            }
        }
        return rdvsByDate;
    }
    public TreeSet<RendezVousSchema> getRendezVoussByDate(LocalDate date) {
        TreeSet<RendezVousSchema> rdvsByDate = new TreeSet<>();
        for (RendezVousSchema rdv : rendezVouss) {
            if (rdv.getDate().equals(date)) {
                rdvsByDate.add(rdv);
            }
        }
        return rdvsByDate;
    }
}
