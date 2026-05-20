# Descrizione del Progetto

Questo progetto consiste nella realizzazione di un mini RPG a turni sviluppato in Java con interfaccia grafica Swing.  
Il gioco utilizza la classe `SecureRandom()` per simulare il lancio di un dado a 20 facce (`d20`) durante i combattimenti.
Il giocatore può:
- scegliere il nome del proprio personaggio
- scegliere una classe
- ottenere statistiche differenti in base alla classe scelta
- affrontare nemici e boss finali
- utilizzare armi e pozioni
- combattere in mondi differenti con lore personalizzata

## Sistema di Combattimento
Il combattimento avviene a turni tramite il lancio di un dado `d20`.
## Regole del dado
- `1` → MISS
  - l'attacco fallisce
- `2 - 19` → danno normale
- `20` → CRITICAL HIT
  - il danno viene aumentato
Il primo personaggio che raggiunge `HP = 0` perde la partita.

# Classi Principali
## Classe Player
La classe `Player` rappresenta il personaggio giocabile.

| Attributo | Descrizione |
| nome | nome del personaggio |
| classe | tipologia del personaggio |
| hp | punti vita attuali |
| maxHp | vita massima |
| mp | mana |
| forza | danno fisico |
| intelligenza | potenza magica |
| destrezza | precisione |
| fortuna | probabilità favorevoli |
| agilita | velocità |
| difesa | riduzione danni |
| arcano | potere magico |
| numPozioni | numero di pozioni |

# 4 Personaggi Disponibili
# Barbaro
HP: 120 | MP: 10 | Forza: 18 | Intelligenza: 6 | Destrezza: 10 | Agilita: 12 | Difesa: 14
# Guerriero
HP: 100 | MP: 20 | Forza: 15 | Intelligenza: 10 | Destrezza: 12 | Agilita: 10 | Difesa: 16
# Mago
HP: 70 | MP: 80 | Forza: 6 | Intelligenza: 18 | Destrezza: 11 | Agilita: 11 | Difesa: 8
# Ladro
HP: 85 | MP: 30 | Forza: 10 | Intelligenza: 11 | Destrezza: 18 | Agilita: 16 | Difesa: 10

# Sistema Armi
Ogni arma possiede:
- bonus attacco
- bonus difesa
- classe affine
Se l’arma è compatibile con la classe del personaggio, vengono applicati bonus aggiuntivi.

## Esempi di armi
| Arma | Classe |
| Ascia Bipenne | Barbaro |
| Katana | Guerriero |
| Bastone Arcano | Mago |
| Daggers | Ladro |

# Classe Nemico
La classe `Nemico` gestisce:
- HP
- attacco
- difesa
- boss finali
  
Esistono:
- nemici comuni
- boss finali con statistiche superiori

# Classe Mondo
Ogni mondo contiene:
- nome
- lore
- nemici comuni
- boss finale
- 
# Mondi presenti
- Toscana
- Campania
- Lombardia

# Interfaccia Grafica
L’interfaccia è stata realizzata tramite Java Swing.
Comprende:
- barra HP giocatore
- barra HP nemico
- log combattimento
- pulsanti attacco
- utilizzo pozioni
- selezione personaggio e mondo
