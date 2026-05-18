# LavoroTPS
Lavoro di gruppo TPS 

definire una classe playeer attributi: nome, classe, nPuntiVita. utlizzando securRandom() simulare un combattimento a turni dove il 
risultato del securRandom() sarà riferito a un d20. stablire che in caso in cui il lancio del dado sia uguale a 1 dovrete stampare un
messaggio che dica "miss". tutti i numeri da 2 a 19 si riferiranno al danno effettivo. nel caso in cui il risultato del dado si uguale a
20 dovrete stampare un messaggio che dica "criticalhit" i danni saranno raddopiati. stabilire i turni. il primo personaggo che raggiunge
hp 0 perderà la partita. creare un report su GitHub fare un file md(per descrive). aggiungere alla classe player un attributo chiamato
classe. la classe che verra scelta dall'utente stara ad indicare la tipologia di personaggio e associare ad ogni tipologia di classe
delle caratterisstiche hp, mp, forza, iniziativa, inteligenza, destrezza, fortuna, agilità, difesa, arcano. fissare a piacere le
statistiche per ogni tipologia di classe. far scegliere il nome del personaggio. 

Un simulatore di combattimento 1v1 tra due giocatori ispirato ai sistemi di D&D 5e. Il gioco utilizza dadi virtuali per determinare
iniziativa, successi degli attacchi e danni critici.

✨ Caratteristiche Principali
Sistema di Iniziativa: Tiro con d20 + modificatore di Agilità per determinare l'ordine di turno

Meccaniche di Combattimento:

Tiro attacco d20 (1 = miss automatico, 20 = critico)

Danno base d6, doppio sui critici

Riduzione danno basata sulla Difesa

4 Classi Disponibili:
Barbaro[HP: 120, MP: 10, Forza: 18, Inteligenza: 6, Destrezza: 10, Agilita: 12, Difesa: 14]
Guerriero[HP: 100, MP: 20, Forza: 15, Inteligenza: 10, Destrezza: 12, Agilita: 10, Difesa: 16]
Mago[HP: 70, MP: 80, Forza: 6, Inteligenza: 18, Destrezza: 11, Agilita: 11, Difesa: 8]
Ladro[HP: 85, MP: 30, Forza: 10, Inteligenza: 11, Destrezza: 18, Agilita: 16, Difesa: 10]
