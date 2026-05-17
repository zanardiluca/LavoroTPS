package progettoTPS;

public class Player {
    private String nome;
    private String classe;
    private int maxHp;
    private int hp;
    private int mp;
    private int forza, intelligenza, destrezza, fortuna, agilita, difesa, arcano;
    private int numPozioni = 2; 
    private Arma armaEquipaggiata = null;

    public Player(String nome, String classe) {
        this.nome = nome;
        this.classe = classe.toLowerCase();
        assegnaStatistiche();
        this.hp = this.maxHp;
    }
    
//tutto quello che un PG ha
    private void assegnaStatistiche() {
        switch (classe) {
            case "barbaro":
                maxHp = 120; mp = 10; forza = 18; intelligenza = 6; destrezza = 10; fortuna = 10; agilita = 12; difesa = 14; arcano = 5;
                break;
            case "guerriero":
                maxHp = 100; mp = 20; forza = 15; intelligenza = 10; destrezza = 12; fortuna = 10; agilita = 10; difesa = 16; arcano = 6;
                break;
            case "mago":
                maxHp = 70; mp = 80; forza = 6; intelligenza = 18; destrezza = 11; fortuna = 12; agilita = 11; difesa = 8; arcano = 18;
                break;
            case "ladro":
                maxHp = 85; mp = 30; forza = 10; intelligenza = 11; destrezza = 18; fortuna = 16; agilita = 16; difesa = 10; arcano = 8;
                break;
            default: // Default per evitare crash e gestire inserimenti imprevisti
                maxHp = 90; mp = 30; forza = 11; intelligenza = 11; destrezza = 11; fortuna = 11; agilita = 11; difesa = 11; arcano = 11;
                break;
        }
    }
    
    //stats migliori per un PG

    public double getMoltiplicatoreDanno() {
        int statPrincipale = 10;
        switch (classe) {
            case "barbaro":
            case "guerriero": 
                statPrincipale = forza; 
                break;
            case "mago": 
                statPrincipale = intelligenza; 
                break;
            case "ladro": 
                statPrincipale = destrezza; 
                break;
        }
        // +5% di danno per ogni punto sopra il 10
        return 1.0 + ((statPrincipale - 10) * 0.05);
    }

    public int getBonusAttaccoArma() {
        if (armaEquipaggiata != null && armaEquipaggiata.getClasseAffine().equalsIgnoreCase(this.classe)) {
            return armaEquipaggiata.getBonusAttacco();
        }
        return 0;
    }

    public int getBonusDifesaArma() {
        if (armaEquipaggiata != null && armaEquipaggiata.getClasseAffine().equalsIgnoreCase(this.classe)) {
            return armaEquipaggiata.getBonusDifesa();
        }
        return 0;
    }

    public void subisciDanno(int danno) {
        int difesaTotale = difesa + getBonusDifesaArma();
        int dannoRidotto = danno - (difesaTotale / 2);
        if (dannoRidotto < 1) dannoRidotto = 1; 

        hp -= dannoRidotto;
        if (hp < 0) hp = 0;
    }    
    
    public String usaPozione() {
        if (numPozioni > 0) {
            numPozioni--;
            int cura = 35;
            hp += cura;
            if (hp > maxHp) hp = maxHp;
            return nome + " usa una pozione e recupera " + cura + " HP!";
        }
        return "Non hai più pozioni!";
    }
    
    //metodi get/set ecc...

    public void aggiungiPozione() { numPozioni++; }
    public String getNome() { return nome; }
    public String getClasse() { return classe.toUpperCase(); }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAgilita() { return agilita; }
    public int getNumPozioni() { return numPozioni; }
    public boolean isVivo() { return hp > 0; }
    public void setArma(Arma arma) { this.armaEquipaggiata = arma; }
    public Arma getArma() { return armaEquipaggiata; }
}
