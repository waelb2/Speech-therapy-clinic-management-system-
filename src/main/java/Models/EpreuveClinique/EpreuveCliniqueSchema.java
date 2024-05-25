package Models.EpreuveClinique;

import Models.Test.TestSchema;

import java.io.Serializable;
import java.util.ArrayList;

public class EpreuveCliniqueSchema  implements Serializable {
    private String observation;
    private ArrayList<TestSchema> test;

    public EpreuveCliniqueSchema(String observation, ArrayList<TestSchema> test) {
        this.observation = observation;
        this.test = test;
    }
}
