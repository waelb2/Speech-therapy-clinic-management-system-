package Models.Question;

public class QuestionSchema {
    private String enonce;
    private int score;

    public QuestionSchema(String enonce, int score) {
        this.enonce = enonce;
        this.score = score;
    }

    public String getEnoncé() {
        return enonce;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
