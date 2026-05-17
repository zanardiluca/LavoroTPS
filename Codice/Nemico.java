package progettoTPS;

public class Nemico {
    private String nome;
    private int hp;
    private int maxHp;
    private int attaccoBase;
    private int difesa;
    private boolean isBoss;

    public Nemico(String nome, boolean isBoss) {
        this.nome = nome;
        this.isBoss = isBoss;
        if (isBoss) {
            this.maxHp = 150;
            this.hp = 150;
            this.attaccoBase = 22;
            this.difesa = 12;
        } else {
            this.maxHp = 45;
            this.hp = 45;
            this.attaccoBase = 12;
            this.difesa = 6;
        }
    }

    public String getNome() { return nome; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttaccoBase() { return attaccoBase; }
    public int getDifesa() { return difesa; }
    public boolean isBoss() { return isBoss; }
    public boolean isVivo() { return hp > 0; }

    public void subisciDanno(int danno) {
        int dannoRidotto = danno - (difesa / 2);
        if (dannoRidotto < 1) dannoRidotto = 1;
        hp -= dannoRidotto;
        if (hp < 0) hp = 0;
    }
}
