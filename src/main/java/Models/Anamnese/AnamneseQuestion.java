package Models.Anamnese;

public abstract class AnamneseQuestion {
    private String enonce;

    public AnamneseQuestion(String enonce) {
        this.enonce = enonce;
    }

    public String getEnonce() {
        return enonce;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }
}
