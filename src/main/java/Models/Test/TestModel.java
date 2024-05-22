package Models.Test;


import Databases.TestDB;
import Models.Test.Exercice.ExerciceSchema;
import Models.Test.Question.QuestionSchema;
import com.example.tp_poo.HelloApplication;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class TestModel implements TestDB, Serializable {

    TreeMap<String, TestSchema> mesTests = new TreeMap<>();

    public TestModel(TreeMap<String, TestSchema> mesTests) {
        this.mesTests = mesTests;
    }
    @Override
    public TestSchema createTestQst(String nom, String observation, ArrayList<QuestionSchema> questions) {
        TestQstSchema testQstSchema = new TestQstSchema(nom, observation, questions);
        mesTests.put(nom, testQstSchema);
        return testQstSchema;
    }
    @Override
    public TestSchema createTestQst(TestQstSchema testQstSchema) {
        mesTests.put(testQstSchema.getNom(), testQstSchema);
        return testQstSchema;
    }
    @Override
    public TestQstSchema deleteTestQst(String nom) {
        return (TestQstSchema) mesTests.remove(nom);
    }
    @Override
    public boolean testQstExist(String nom) {
        return mesTests.containsKey(nom);
    }
    @Override
    public TestQstSchema updateTestQst(TestQstSchema testQst) {
        return (TestQstSchema) mesTests.put(testQst.getNom(), testQst);
    }

    @Override
    public TestSchema createTestExo(String nom, String observation, ArrayList<ExerciceSchema> exercices) {
        TestExoSchema testExo = new TestExoSchema(nom, observation, exercices);
        mesTests.put(nom, testExo);
        return testExo;
    }
    @Override
    public TestSchema createTestExo(TestSchema testExo) {
        mesTests.put(testExo.getNom(), testExo);
        return testExo;
    }
    @Override
    public TestSchema findTest(String nom) {
        return mesTests.get(nom);
    }

    @Override
    public TestSchema deleteTestExo(String nom) {
        return mesTests.remove(nom);
    }
    @Override
    public boolean testExoExist(String nom) {
        return mesTests.containsKey(nom);
    }
    @Override
    public TestSchema updateTestExo(TestSchema testExo) {
        return mesTests.put(testExo.getNom(), testExo);
    }
    @Override
    public void loadTests() throws IOException, ClassNotFoundException {
        // Check if the file exists
        File file = new File(HelloApplication.currentUserDir + "/mesTests.dat");
        if (file.exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                mesTests = (TreeMap<String, TestSchema>) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                // Handle exceptions
                System.out.println(e.getMessage());
            }
        } else {
            // File doesn't exist, initialize mesTests as an empty TreeMap
            mesTests = new TreeMap<>();
        }
    }

    @Override
    public void saveTests() throws IOException {
        // save mesTests to mesTests.dat
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(HelloApplication.currentUserDir + "/mesTests.dat"))) {
            objectOutputStream.writeObject(mesTests);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public int countTests() {
        return this.mesTests.size();
    }
}


