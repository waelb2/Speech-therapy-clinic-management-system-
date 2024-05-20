package Models.Test;

import Models.Exercice.ExerciceSchema;

import java.io.Serializable;
import java.util.ArrayList;

public class TestExoSchema extends TestSchema implements Serializable {
    private ArrayList<ExerciceSchema> exercices;
    private int scoreMoy;

}