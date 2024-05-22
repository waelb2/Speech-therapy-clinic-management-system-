package Databases;

import Models.Anamnese.AnamneseQuestion;
import Models.Anamnese.AnamneseSchema;


import java.io.*;
import java.util.ArrayList;


public interface AnamneseDB {
    public boolean anamneseExist(String titre);
    public AnamneseSchema createAnamnese(AnamneseSchema anamneseSchema ) ;

    public AnamneseSchema createAnamnese(String titre, ArrayList<AnamneseQuestion> anamQst);

    public AnamneseSchema deleteAnamnese(String titre);

    public AnamneseSchema findAnamnese(String titre);

    public void loadAnamnese() throws IOException, ClassNotFoundException ;

    public void saveAnamnese() throws  IOException ;
}
