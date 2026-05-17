package progettoTPS;

public class Mondo {
    private String nome;
    private String lore;
    private String[] nemiciComuni;
    private String boss;

    public Mondo(String nome, String lore, String[] nemiciComuni, String boss) {
        this.nome = nome;
        this.lore = lore;
        this.nemiciComuni = nemiciComuni;
        this.boss = boss;
    }

    public String getNome() { return nome; }
    public String getLore() { return lore; }
    public String[] getNemiciComuni() { return nemiciComuni; }
    public String getBoss() { return boss; }
}

