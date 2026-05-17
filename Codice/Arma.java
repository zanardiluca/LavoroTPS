package progettoTPS;

public class Arma {
    private String nome;
    private String classeAffine;
    private int bonusAttacco;
    private int bonusDifesa;

    public Arma(String nome, String classeAffine, int bonusAttacco, int bonusDifesa) {
        this.nome = nome;
        this.classeAffine = classeAffine;
        this.bonusAttacco = bonusAttacco;
        this.bonusDifesa = bonusDifesa;
    }

    public String getNome() { return nome; }
    public String getClasseAffine() { return classeAffine; }
    public int getBonusAttacco() { return bonusAttacco; }
    public int getBonusDifesa() { return bonusDifesa; }
}


