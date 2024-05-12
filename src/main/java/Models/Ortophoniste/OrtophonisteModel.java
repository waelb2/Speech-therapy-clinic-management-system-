package Models.Ortophoniste;
import Databases.OrtophonisteDB;
import java.util.TreeMap;

public class OrtophonisteModel  implements  OrtophonisteDB{
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

    @Override
    public boolean OrtophonisteExist(String email){
        return users.containsKey(email);
    }
}
