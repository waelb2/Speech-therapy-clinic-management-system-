package Models.Question;

public class QuestionSchema {
    private String enoncé;
    private int score;

    public QuestionSchema(String enoncé, int score) {
        this.enoncé = enoncé;
        this.score = score;
    }

    public String getEnoncé() {
        return enoncé;
    }

    public void setEnoncé(String enoncé) {
        this.enoncé = enoncé;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
