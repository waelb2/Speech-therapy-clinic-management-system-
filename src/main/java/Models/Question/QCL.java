package Models.Question;

public class QCL extends QuestionSchema{
    private String reponseType;

    public QCL(String enoncé, int score, String reponseType) {
        super(enoncé, score);
        this.reponseType = reponseType;
    }

    public String getReponseType() {
        return reponseType;
    }

    public void setReponseType(String reponseType) {
        this.reponseType = reponseType;
    }
}
