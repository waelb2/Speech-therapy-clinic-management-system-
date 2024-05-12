package Databases;
import  Models.Ortophoniste.OrtophonisteSchema;
public interface OrtophonisteDB {
    public OrtophonisteSchema createOrtophoniste(String nom, String prenom, String email, String motDePasse, String adresse, String numTelephone);
    public OrtophonisteSchema createOrtophoniste(OrtophonisteSchema ortophoniste);
    public OrtophonisteSchema findOrtophoniste(String email);
    public OrtophonisteSchema deleteOrtophoniste(String email);
    public boolean OrtophonisteExist(String email);
    public OrtophonisteSchema updateOrtophoniste(OrtophonisteSchema ortophoniste);

}
