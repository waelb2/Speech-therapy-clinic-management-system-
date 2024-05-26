package Databases;

import Models.Test.Exercice.ExerciceSchema;
import Models.Test.Question.QuestionSchema;
import Models.Test.TestQstSchema;
import Models.Test.TestSchema;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public interface TestDB {
    public TreeMap<String,TestSchema> getAllTests();
    public TestSchema[] getAllTest();
    public TestSchema createTestQst(String nom, String observation, ArrayList<QuestionSchema> questions);

    public TestSchema createTestQst(TestQstSchema testQstSchema);

    public TestQstSchema deleteTestQst(String nom) ;

    public boolean testQstExist(String nom);
    public TestQstSchema updateTestQst(TestQstSchema testQst);


    public TestSchema createTestExo(String nom,String observation, ArrayList<ExerciceSchema> exercices);

    public TestSchema createTestExo(TestSchema testExo);

    public TestSchema findTest(String nom);


    public TestSchema deleteTestExo(String nom);

    public boolean testExoExist(String nom );

    public TestSchema updateTestExo(TestSchema testExo);
    public void loadTests() throws IOException, ClassNotFoundException ;


    public void saveTests() throws  IOException ;
    public int countTests();



}
