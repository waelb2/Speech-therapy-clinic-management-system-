package Models.EpreuveClinique;

import Models.Test.TestSchema;

import java.io.Serializable;

public class EpreuveCliniqueSchema  implements Serializable {
    private String observation;
    private TestSchema test;
}
