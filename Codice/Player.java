package Codice;

public class Player {

	// generale
    private String nome;
    private String classe;

    //attributi del personaggio
    private int hp;
    private int mp;
    private int forza;
    private int intelligenza;
    private int destrezza;
    private int fortuna;
    private int agilita;
    private int difesa;
    private int arcano;

    public Player(String nome, String classe) {
        this.nome = nome;
        this.classe = classe;
        assegnaStatistiche(classe);
    }
// qui ci stanno solo 4 classi, ma ho intenzione di mettere le restanti in modo tale che ce ne siano 13 come in dnd
    private void assegnaStatistiche(String classe) {
        switch (classe.toLowerCase()) {
            case "barbaro":
                hp = 80; mp = 10; forza = 9; intelligenza = 2; destrezza = 5;
                fortuna = 4; agilita = 5; difesa = 7; arcano = 1;
                break;

            case "guerriero":
                hp = 70; mp = 20; forza = 8; intelligenza = 4; destrezza = 6;
                fortuna = 5; agilita = 6; difesa = 8; arcano = 2;
                break;

            case "mago":
                hp = 40; mp = 80; forza = 2; intelligenza = 9; destrezza = 5;
                fortuna = 6; agilita = 5; difesa = 3; arcano = 9;
                break;

            case "ladro":
                hp = 50; mp = 30; forza = 5; intelligenza = 6; destrezza = 9;
                fortuna = 7; agilita = 9; difesa = 4; arcano = 3;
                break;

            // riempio anche la default per sicurezza
            default:
                hp = 60; mp = 30; forza = 5; intelligenza = 5; destrezza = 5;
                fortuna = 5; agilita = 5; difesa = 5; arcano = 5;
                break;
        }
    }

    // scelta del nome (vale anche per la cpu)
    public String getNome() {
        return nome;
    }

    public int getHp() {
        return hp;
    }

    public int getAgilita() {
        return agilita;
    }

    public void subisciDanno(int danno) {
        int dannoRidotto = danno - difesa / 2;
        if (dannoRidotto < 0) dannoRidotto = 0;

        hp -= dannoRidotto;
        if (hp < 0) hp = 0;
    }

    public boolean isVivo() {
        return hp > 0;
    }

}
