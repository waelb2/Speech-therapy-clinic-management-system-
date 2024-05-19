package Models.Ortophoniste;
import Databases.OrtophonisteDB;
import com.example.tp_poo.HelloApplication;

import java.io.*;
import java.util.TreeMap;

public class OrtophonisteModel  implements  OrtophonisteDB , Serializable{
    private TreeMap<String , OrtophonisteSchema> users  = new TreeMap<>();

    public OrtophonisteModel(TreeMap<String, OrtophonisteSchema> users) {
        this.users = users;
    }

    @Override
    public OrtophonisteSchema createOrtophoniste(OrtophonisteSchema newOrtophoniste) {
        if(users.containsKey(newOrtophoniste.getEmail())){
            return null; // needs an exception here
        }
        users.put(newOrtophoniste.getEmail(), newOrtophoniste);
        return newOrtophoniste;
    }
    @Override
    public OrtophonisteSchema createOrtophoniste(String nom, String prenom, String email, String motDePasse, String adresse, String numTelephone) {
        OrtophonisteSchema newOrtophoniste = new OrtophonisteSchema(nom, prenom, email, motDePasse, adresse, numTelephone);
        if(users.containsKey(newOrtophoniste.getEmail())){
            return null; // needs an exception here
        }
        users.put(newOrtophoniste.getEmail(), newOrtophoniste);
        return createOrtophoniste(newOrtophoniste);
    }

    @Override
    public OrtophonisteSchema findOrtophoniste(String email) {
        if(!users.containsKey(email)){
            return null; // needs an exception here
        }
        return users.get(email);
    }
    @Override
    public OrtophonisteSchema deleteOrtophoniste(String email) {
        if(!users.containsKey(email)){
            return null; // needs an exception here
        }
        return users.remove(email);
    }
    @Override
    public  OrtophonisteSchema updateOrtophoniste(OrtophonisteSchema newOrtophoniste){
        if(!users.containsKey(newOrtophoniste.getEmail())){
            return null; // needs an exception here
        }
        users.put(newOrtophoniste.getEmail(), newOrtophoniste);
        return newOrtophoniste;
    }
    public void saveOrthophonistes() throws IOException {
        // save orthophonistes to orthophonistes.dat
       try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(HelloApplication.appUsersDir +"/orthophonistes.dat"))){
           objectOutputStream.writeObject(users);
       }catch (IOException e ){
           System.out.println(e.getMessage());
       }
    }
    public void loadOrthophoniste() throws  IOException, ClassNotFoundException{
        // load users from othrophonistes.dat
       try(ObjectInputStream objectInputStream= new ObjectInputStream  (new FileInputStream (HelloApplication.appUsersDir + "/orthophonistes.dat"))){
          this.users = (TreeMap<String, OrtophonisteSchema>) objectInputStream.readObject();
       }
       catch (IOException | ClassNotFoundException e ){
           System.out.println(e.getMessage());
       }
    }

    @Override
    public boolean OrtophonisteExist(String email){
        return users.containsKey(email);
    }
}
