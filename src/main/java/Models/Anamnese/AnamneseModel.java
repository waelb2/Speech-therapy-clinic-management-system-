package Models.Anamnese;

import com.example.tp_poo.HelloApplication;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class AnamneseModel implements Serializable {
    TreeMap<String, AnamneseSchema> mesAnamneses = new TreeMap<>();

    public AnamneseModel(TreeMap<String, AnamneseSchema> mesAnamneses) {
        this.mesAnamneses = mesAnamneses;
    }

    public boolean anamneseExist(String titre){
        return mesAnamneses.containsKey(titre);
    }
    public AnamneseSchema createAnamnese(AnamneseSchema anamneseSchema ) {
        while(anamneseExist(anamneseSchema.getTitre())){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ce titre existe deja, donner un autre titre : ");
            String titre = scanner.nextLine();
            scanner.close();
            anamneseSchema.setTitre(titre);
        }
        mesAnamneses.put(anamneseSchema.getTitre(), anamneseSchema);
        return anamneseSchema;
    }

    public AnamneseSchema createAnamnese(String titre, ArrayList<AnamneseQuestion> anamQst){
        while(anamneseExist(titre)){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ce titre existe deja, choisissez un autre titre : ");
            titre = scanner.nextLine();
            scanner.close();
        }
        AnamneseSchema anamneseSchema = new AnamneseSchema(titre, anamQst);
        mesAnamneses.put(titre, anamneseSchema);
        return anamneseSchema;
    }

    public AnamneseSchema deleteAnamnese(String titre){
        return (AnamneseSchema) mesAnamneses.remove(titre);
    }

    public AnamneseSchema findAnamnese(String titre){
        return mesAnamneses.get(titre);
    }

    public void loadAnamnese() throws IOException, ClassNotFoundException {
        File file = new File(HelloApplication.currentUserDir + "/mesAnamneses.dat");
        if (file.exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                mesAnamneses = (TreeMap<String, AnamneseSchema>) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                // Handle exceptions
                System.out.println(e.getMessage());
            }
        } else {
            // File doesn't exist, initialize patients as an empty TreeMap
            mesAnamneses = new TreeMap<>();
        }
    }

    public void saveAnamnese() throws  IOException {
        try (ObjectOutputStream objectOutputStream= new ObjectOutputStream(new FileOutputStream(HelloApplication.currentUserDir + "/mesAnamneses.dat"))) {
            objectOutputStream.writeObject(mesAnamneses);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
