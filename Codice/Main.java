package Codice;

import java.security.SecureRandom;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom();

        // Creazione giocatore
        System.out.print("Inserisci nome Player 1: ");
        String nome1 = scanner.nextLine();

        System.out.print("Scegli classe (barbaro, guerriero, mago, ladro...): ");
        String classe1 = scanner.nextLine();

        Player p1 = new Player(nome1, classe1);

        // Creazione CPU
        System.out.print("Inserisci nome Player 2: ");
        String nome2 = scanner.nextLine();

        System.out.print("Scegli classe: ");
        String classe2 = scanner.nextLine();

        Player p2 = new Player(nome2, classe2);

        // si usa il d20 per fare il tiro di iniziativa
        int init1 = tiroIniziativa(p1, random);
        int init2 = tiroIniziativa(p2, random);

        System.out.println(p1.getNome() + " iniziativa: " + init1);
        System.out.println(p2.getNome() + " iniziativa: " + init2);

        Player primo, secondo;

        if (init1 >= init2) {
            primo = p1;
            secondo = p2;
        } else {
            primo = p2;
            secondo = p1;
        }

        System.out.println("Inizia: " + primo.getNome());

        // il Combattimento
        while (p1.isVivo() && p2.isVivo()) {

            attacco(primo, secondo, random);
            if (!secondo.isVivo()) break;

            attacco(secondo, primo, random);
        }

        System.out.println("\nVincitore: " +
                (p1.isVivo() ? p1.getNome() : p2.getNome()));
    }

    public static int tiroIniziativa(Player p, SecureRandom random) {
        return random.nextInt(20) + 1 + p.getAgilita();
    }

    public static void attacco(Player attaccante, Player difensore, SecureRandom random) {
        int dado = random.nextInt(20) + 1;

        System.out.println("\n" + attaccante.getNome() + " tira: " + dado);

        if (dado == 1) {
            System.out.println("MISS!");
        } else if (dado == 20) {
            int danno = (random.nextInt(6) + 1) * 2;
            System.out.println("CRITICAL HIT! Danno: " + danno);
            difensore.subisciDanno(danno);
        } else {
            int danno = random.nextInt(6) + 1;
            System.out.println("HIT! Danno: " + danno);
            difensore.subisciDanno(danno);
        }

        System.out.println("HP " + difensore.getNome() + ": " + difensore.getHp());
    }

}
