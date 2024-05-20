package Models.Test;

import Models.Question.QuestionSchema;

import java.io.Serializable;
import java.util.ArrayList;

public class TestQstSchema extends TestSchema implements Serializable {
    private ArrayList<QuestionSchema> questions;
    private int scoreMoy;

}
