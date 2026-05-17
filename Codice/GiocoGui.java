package progettoTPS;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.security.SecureRandom;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class GiocoGui extends JFrame {
    private static final long serialVersionUID = 1L; // Ottimizzazione per conformita Eclipse Swing
    
    private Player giocatore;
    private Nemico nemicoCorrente;
    private Mondo mondoSelezionato;
    private final SecureRandom random = new SecureRandom();
    
    private int nemiciSconfitti = 0;
    private final int NEMICI_PER_BOSS = 5;

    private final String[] classiDisponibili = {"Barbaro", "Guerriero", "Mago", "Ladro"};
    
    private final Arma[] archivioArmi = {
        new Arma("Ascia Bipenne", "barbaro", 15, 0),
        new Arma("Katana", "guerriero", 10, 5),
        new Arma("Bastone Arcano", "mago", 0, 15),
        new Arma("Daggers", "ladro", 5, 10)
    };

    private final Mondo[] archivioMondi = {
        new Mondo("Toscana", "In questo regno, le persone venerano il dio supremo Roberto Benigni, che riposa alla cima di una torre storta ma allo stesso tempo piena di pericoli.", 
                new String[]{"Dario Moccia", "Andrea Bocelli", "Paolo Ruffini"}, "Roberto Benigni"),
        new Mondo("Campania", "Regno costiero dove è presente la capitale Napoli, attualmente governata da Passione. Se si vuole far crollare questa mafia, allora si dovrà affrontare innumerevoli portatori di stand... molto bizzarro.", 
                new String[]{"Bruno Bucciarati", "Guido Mista", "Risotto Nero"}, "Diavolo"),
        new Mondo("Lombardia", "Un regno avvolto nella nebbia più oscura mai concepita da questo mondo, lo smog! non è un posto sicuro... I maranza colpiscono sempre alle spalle", 
                new String[]{"Scrafty", "Stereo-Maranza", "Aran Ryan"}, "La Metro Vivente")
    };

    private JTextArea txtLog;
    private JProgressBar barPlayerHp, barEnemyHp;
    private JButton btnAttacca, btnPozione;
    private JLabel lblStatusMondo, lblInfoNemico, lblInfoPlayer;

    public GiocoGui() {
        setTitle("D and D Mini RPG Arena");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        inizializzaGioco();
    }

    private void inizializzaGioco() {
        String nome = JOptionPane.showInputDialog(this, "Inserisci il nome del tuo eroe:", "Creazione Eroe", JOptionPane.QUESTION_MESSAGE);
        if (nome == null || nome.trim().isEmpty()) nome = "Nameless Man";

        String classeScelta = (String) JOptionPane.showInputDialog(this, "Scegli la tua Classe:", "Classe",
                JOptionPane.QUESTION_MESSAGE, null, classiDisponibili, classiDisponibili[0]);
        if (classeScelta == null) classeScelta = "Guerriero";

        giocatore = new Player(nome, classeScelta);

        Arma armaTrovata = archivioArmi[random.nextInt(archivioArmi.length)];
        giocatore.setArma(armaTrovata);

        String messaggioArma = "Hai trovato l'arma: " + armaTrovata.getNome() + "\n";
        if (armaTrovata.getClasseAffine().equalsIgnoreCase(giocatore.getClasse())) {
            messaggioArma += "AFFINITA ATTIVA! Ottieni +" + armaTrovata.getBonusAttacco() + " Attacco e +" + armaTrovata.getBonusDifesa() + " Difesa.";
        } else {
            messaggioArma += "Non affine alla tua classe. Nessun bonus applicato.";
        }
        JOptionPane.showMessageDialog(this, messaggioArma, "Ritrovamento Arma", JOptionPane.INFORMATION_MESSAGE);

        String[] nomiMondi = new String[archivioMondi.length];
        for (int i = 0; i < archivioMondi.length; i++) {
            nomiMondi[i] = archivioMondi[i].getNome();
        }

        String mondoSceltoNome = (String) JOptionPane.showInputDialog(this, "Seleziona il Mondo da visitare:", "Scelta Mondo",
                JOptionPane.QUESTION_MESSAGE, null, nomiMondi, nomiMondi[0]);
        
        mondoSelezionato = archivioMondi[0];
        if (mondoSceltoNome != null) {
            for (Mondo m : archivioMondi) {
                if (m.getNome().equals(mondoSceltoNome)) {
                    mondoSelezionato = m;
                    break;
                }
            }
        }

        JOptionPane.showMessageDialog(this, "LORE DI " + mondoSelezionato.getNome().toUpperCase() + ":\n" + mondoSelezionato.getLore(), "Lore", JOptionPane.INFORMATION_MESSAGE);

        costruisciInterfaccia();
        prossimoIncontro();
    }

    private void costruisciInterfaccia() {
        JPanel pnlSuperiore = new JPanel(new GridLayout(2, 1));
        pnlSuperiore.setBackground(new Color(45, 45, 45));
        
        lblStatusMondo = new JLabel("Mondo: " + mondoSelezionato.getNome() + " | Progressi: 0/" + NEMICI_PER_BOSS + " Nemici", SwingConstants.CENTER);
        lblStatusMondo.setForeground(Color.WHITE);
        lblStatusMondo.setFont(new Font("Arial", Font.BOLD, 14));
        
        pnlSuperiore.add(lblStatusMondo);
        add(pnlSuperiore, BorderLayout.NORTH);

        JPanel pnlCentrale = new JPanel(new BorderLayout());
        txtLog = new JTextArea();
        txtLog.setEditable(false);
        txtLog.setBackground(new Color(20, 20, 20));
        txtLog.setForeground(new Color(0, 230, 115));
        txtLog.setFont(new Font("Consolas", Font.PLAIN, 12));
        pnlCentrale.add(new JScrollPane(txtLog), BorderLayout.CENTER);

        JPanel pnlStats = new JPanel(new GridLayout(2, 2, 10, 10));
        pnlStats.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblInfoPlayer = new JLabel(giocatore.getNome() + " (" + giocatore.getClasse() + ")");
        barPlayerHp = new JProgressBar(0, giocatore.getMaxHp());
        barPlayerHp.setValue(giocatore.getHp());
        barPlayerHp.setStringPainted(true);
        barPlayerHp.setForeground(Color.GREEN);

        lblInfoNemico = new JLabel("Nemico");
        barEnemyHp = new JProgressBar(0, 100);
        barEnemyHp.setStringPainted(true);
        barEnemyHp.setForeground(Color.RED);

        pnlStats.add(lblInfoPlayer);
        pnlStats.add(lblInfoNemico);
        pnlStats.add(barPlayerHp);
        pnlStats.add(barEnemyHp);
        pnlCentrale.add(pnlStats, BorderLayout.SOUTH);

        add(pnlCentrale, BorderLayout.CENTER);

        JPanel pnlAzioni = new JPanel();
        btnAttacca = new JButton("Attacca (Lancia d20)");
        btnPozione = new JButton("Usa Pozione (" + giocatore.getNumPozioni() + ")");

        btnAttacca.addActionListener(e -> eseguiTurnoGiocatore());
        btnPozione.addActionListener(e -> {
            logMessaggio(giocatore.usaPozione());
            aggiornaInterfaccia();
            turnoNemico();
        });

        pnlAzioni.add(btnAttacca);
        pnlAzioni.add(btnPozione);
        add(pnlAzioni, BorderLayout.SOUTH);
    }

    private void prossimoIncontro() {
        if (nemiciSconfitti < NEMICI_PER_BOSS) {
            String[] elenco = mondoSelezionato.getNemiciComuni();
            String nomeNemico = elenco[random.nextInt(elenco.length)] + " (" + (nemiciSconfitti + 1) + "/" + NEMICI_PER_BOSS + ")";
            nemicoCorrente = new Nemico(nomeNemico, false);
            logMessaggio("\nAppare un nemico: " + nemicoCorrente.getNome());
        } else {
            nemicoCorrente = new Nemico(mondoSelezionato.getBoss() + " [BOSS FINALE]", true);
            logMessaggio("\nATTENZIONE! E apparso il Boss: " + nemicoCorrente.getNome());
        }
        
        barEnemyHp.setMaximum(nemicoCorrente.getMaxHp());
        aggiornaInterfaccia();
    }

    private void eseguiTurnoGiocatore() {
        int dado = random.nextInt(20) + 1;
        logMessaggio("\n" + giocatore.getNome() + " lancia il d20: " + dado);

        if (dado == 1) {
            logMessaggio("MISS! L'attacco fallisce.");
        } else if (dado == 20) {
            logMessaggio("CRITICAL HIT! Danno fisso di 40!");
            nemicoCorrente.subisciDanno(40);
        } else {
            int dannoBase = dado + giocatore.getBonusAttaccoArma();
            int dannoScalato = (int) (dannoBase * giocatore.getMoltiplicatoreDanno());
            logMessaggio("HIT! Danno calcolato con modificatori: " + dannoScalato);
            nemicoCorrente.subisciDanno(dannoScalato);
        }

        aggiornaInterfaccia();

        if (!nemicoCorrente.isVivo()) {
            logMessaggio(nemicoCorrente.getNome() + " e stato sconfitto!");
            
            if (random.nextBoolean()) {
                giocatore.aggiungiPozione();
                logMessaggio("Hai trovato una Pozione curativa sul corpo del nemico.");
            }

            if (nemicoCorrente.isBoss()) {
                JOptionPane.showMessageDialog(this, "VITTORIA! Hai completato il mondo " + mondoSelezionato.getNome() + " sconfiggendo il Boss!", "Vittoria Totale", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } else {
                nemiciSconfitti++;
                lblStatusMondo.setText("Mondo: " + mondoSelezionato.getNome() + " | Progressi: " + nemiciSconfitti + "/" + NEMICI_PER_BOSS + " Nemici");
                prossimoIncontro();
            }
        } else {
            turnoNemico();
        }
    }

    private void turnoNemico() {
        if (!nemicoCorrente.isVivo()) return;

        logMessaggio("\nTurno di: " + nemicoCorrente.getNome());
        int dadoNemico = random.nextInt(20) + 1;

        if (dadoNemico == 1) {
            logMessaggio("Il nemico manca il colpo!");
        } else if (dadoNemico == 20) {
            logMessaggio("CRITICO NEMICO! Subisci 30 danni!");
            giocatore.subisciDanno(30);
        } else {
            int dannoNemico = nemicoCorrente.getAttaccoBase() + (dadoNemico / 3);
            logMessaggio("Il nemico infligge " + dannoNemico + " danni base.");
            giocatore.subisciDanno(dannoNemico);
        }

        aggiornaInterfaccia();

        if (!giocatore.isVivo()) {
            logMessaggio("\nSEI MORTO!");
            JOptionPane.showMessageDialog(this, "GAME OVER. Il tuo eroe e caduto.", "Sconfitta", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private void aggiornaInterfaccia() {
        lblInfoNemico.setText(nemicoCorrente.getNome());
        barPlayerHp.setValue(giocatore.getHp());
        barPlayerHp.setString(giocatore.getHp() + " / " + giocatore.getMaxHp() + " HP");
        
        barEnemyHp.setValue(nemicoCorrente.getHp());
        barEnemyHp.setString(nemicoCorrente.getHp() + " / " + nemicoCorrente.getMaxHp() + " HP");
        
        btnPozione.setText("Usa Pozione (" + giocatore.getNumPozioni() + ")");
    }

    private void logMessaggio(String msg) {
        txtLog.append(msg + "\n");
        txtLog.setCaretPosition(txtLog.getDocument().getLength());
    }

    // Metodo Main per far partire l'applicazione grafica
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GiocoGui().setVisible(true);
        });
    }
}
