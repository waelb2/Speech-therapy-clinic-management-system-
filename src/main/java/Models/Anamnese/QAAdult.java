package Models.Anamnese;

public class QAAdult extends AnamneseQuestion{
    private AnamnQstCateAdult categorie;

    public QAAdult(String questionText, AnamnQstCateAdult categorie) {
        super(questionText);
        this.categorie = categorie;
    }

    public AnamnQstCateAdult getCategorie() {
        return categorie;
    }

    public void setCategorie(AnamnQstCateAdult categorie) {
        this.categorie = categorie;
    }
}
