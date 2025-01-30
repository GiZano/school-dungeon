package com.zanotti.dungeon.oggetti;

import com.zanotti.dungeon.Main;
import com.zanotti.dungeon.entita.Protagonista;
import com.zanotti.dungeon.entita.Mostro;

import java.util.*;

public class Stanza {
    private Random rnd = new Random();
    private Character[][] mappa = new Character[5][5];
    private Cassa cassa;
    private Mostro nemico;
    private int colonnaGiocatore = 0;
    private int rigaGiocatore = 4;
    private String[][] generatoreNomiMostri = { {"Fantastico/a", "Imminente", "Intransigente", "Squisito/a", "Frettoloso/a", "Glorioso/a"},
                                                {"Professore/ssa", "Preside", "Vicepreside", "Personale ATA"},
                                                {"dell'Archimede", "dello Zenale", "del Cantoni", "dell'ABF"},
                                                {"Benatti", "Piccolo"}};

    public Stanza(ArrayList<String> mappe, StringBuilder contenutoCassa, StringBuilder armaMostro, int numeroStanza){
        int stanzaScelta = rnd.nextInt(mappe.size());
        String mappaNonProcessata = mappe.get(stanzaScelta);
        int casellaAttuale = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                this.mappa[i][j] = mappaNonProcessata.toCharArray()[casellaAttuale];
                casellaAttuale++;
            }
        }
        this.cassa = new Cassa(contenutoCassa);
        String nomeMostro = generaNomeMostro();
        boolean mostroSpeciale = (nomeMostro.charAt(0) == '1');
        nomeMostro = nomeMostro.substring(1);
        this.nemico = new Mostro(nomeMostro, armaMostro,numeroStanza, mostroSpeciale);
    }

    public int muoviDx(Protagonista giocatore, Dungeon dungeon){

        if(colonnaGiocatore == 4){
            System.out.println("Impossibile uscire dalla mappa!");
        }
        else if(this.mappa[rigaGiocatore][colonnaGiocatore+1] == 'X'){
            System.out.println("Muro presente!");
            Main.waitSleep();
        }
        else if(this.mappa[rigaGiocatore][colonnaGiocatore+1] == 'C'){
            System.out.println("Apertura cassa!");
            Main.waitSleep();
            apriCassa(giocatore, dungeon);
        }
        else if(this.mappa[rigaGiocatore][colonnaGiocatore+1] == 'N'){
            System.out.println("Attaccando nemico!");
            Main.waitSleep();
            int risultato = combattimento(giocatore, dungeon);
            if(risultato == 0){
                int exp = rnd.nextInt(11);
                System.out.println("Exp guadagnata: " + exp);
                giocatore.aggiungiExp(exp);
                Main.waitSleep();
                this.mappa[rigaGiocatore][colonnaGiocatore + 1] = 'G';
                this.mappa[rigaGiocatore][colonnaGiocatore] = ' ';
                this.colonnaGiocatore++;
            }
            else if(risultato == -1){
                System.out.println("Il Gioco verrà chiuso automaticamente.");
                Main.waitSleep();
                System.exit(0);
            }
        }
        else if(this.mappa[rigaGiocatore][colonnaGiocatore+1] == 'E'){
            System.out.println("Cambiando stanza!");
            Main.waitSleep();
            return 10;
        }
        else {
            this.mappa[rigaGiocatore][colonnaGiocatore + 1] = 'G';
            this.mappa[rigaGiocatore][colonnaGiocatore] = ' ';
            this.colonnaGiocatore++;
        }
        return 0;
    }
    public int muoviSu(Protagonista giocatore, Dungeon dungeon){
        if(rigaGiocatore == 0){
            System.out.println("Impossibile uscire dalla mappa!");
            Main.waitSleep();
        }
        else if(this.mappa[rigaGiocatore-1][colonnaGiocatore] == 'X'){
            System.out.println("Muro presente!");
            Main.waitSleep();
        }
        else if(this.mappa[rigaGiocatore-1][colonnaGiocatore] == 'C'){
            System.out.println("Apertura cassa!");
            apriCassa(giocatore, dungeon);
            Main.waitSleep();
        }
        else if(this.mappa[rigaGiocatore-1][colonnaGiocatore] == 'N'){
            System.out.println("Attaccando nemico!");
            Main.waitSleep();
            int risultato = combattimento(giocatore, dungeon);
            if(risultato == 0){
                int exp = rnd.nextInt(11);
                System.out.println("Exp guadagnata: " + exp);
                giocatore.aggiungiExp(exp);
                Main.waitSleep();
                this.mappa[rigaGiocatore-1][colonnaGiocatore] = 'G';
                this.mappa[rigaGiocatore][colonnaGiocatore] = ' ';
                this.rigaGiocatore--;
            }
            else if(risultato == -1){
                System.out.println("Il Gioco verrà chiuso automaticamente.");
                Main.waitSleep();
                System.exit(0);
            }
        }
        else if(this.mappa[rigaGiocatore-1][colonnaGiocatore] == 'E'){
            System.out.println("Cambiando stanza!");
            Main.waitSleep();
            return 10;
        }
        else {
            this.mappa[rigaGiocatore-1][colonnaGiocatore] = 'G';
            this.mappa[rigaGiocatore][colonnaGiocatore] = ' ';
            this.rigaGiocatore--;
        }
        return 0;
    }
    public int muoviSx(Protagonista giocatore, Dungeon dungeon){
        if(colonnaGiocatore == 0){
            System.out.println("Impossibile uscire dalla mappa!");
            Main.waitSleep();
        }
        else if(this.mappa[rigaGiocatore][colonnaGiocatore-1] == 'X'){
            System.out.println("Muro presente!");
            Main.waitSleep();
        }
        else if(this.mappa[rigaGiocatore][colonnaGiocatore-1] == 'C'){
            System.out.println("Apertura cassa!");
            apriCassa(giocatore, dungeon);
            Main.waitSleep();
        }
        else if(this.mappa[rigaGiocatore][colonnaGiocatore-1] == 'N'){
            System.out.println("Attaccando nemico!");
            Main.waitSleep();
            int risultato = combattimento(giocatore, dungeon);
            if(risultato == 0){
                int exp = rnd.nextInt(11);
                System.out.println("Exp guadagnata: " + exp);
                giocatore.aggiungiExp(exp);
                Main.waitSleep();
                this.mappa[rigaGiocatore][colonnaGiocatore-1] = 'G';
                this.mappa[rigaGiocatore][colonnaGiocatore] = ' ';
                this.colonnaGiocatore--;
            }
            else if(risultato == -1){
                System.out.println("Il Gioco verrà chiuso automaticamente.");
                Main.waitSleep();
                System.exit(0);
            }
        }
        else if(this.mappa[rigaGiocatore][colonnaGiocatore-1] == 'E'){
            System.out.println("Cambiando stanza!");
            Main.waitSleep();
            return 10;
        }
        else {
            this.mappa[rigaGiocatore][colonnaGiocatore-1] = 'G';
            this.mappa[rigaGiocatore][colonnaGiocatore] = ' ';
            this.colonnaGiocatore--;
        }
        return 0;
    }
    public int muoviGiu(Protagonista giocatore, Dungeon dungeon){
        if(rigaGiocatore == 4){
            System.out.println("Impossibile uscire dalla mappa!");
            Main.waitSleep();
        }
        else if(this.mappa[rigaGiocatore+1][colonnaGiocatore] == 'X'){
            System.out.println("Muro presente!");
            Main.waitSleep();
        }
        else if(this.mappa[rigaGiocatore+1][colonnaGiocatore] == 'C'){
            System.out.println("Apertura cassa!");
            apriCassa(giocatore, dungeon);
            Main.waitSleep();
        }
        else if(this.mappa[rigaGiocatore+1][colonnaGiocatore] == 'N'){
            System.out.println("Attaccando nemico!");
            Main.waitSleep();
            int risultato = combattimento(giocatore, dungeon);
            if(risultato == 0){
                int exp = rnd.nextInt(11);
                System.out.println("Exp guadagnata: " + exp);
                giocatore.aggiungiExp(exp);
                Main.waitSleep();
                this.mappa[rigaGiocatore+1][colonnaGiocatore] = 'G';
                this.mappa[rigaGiocatore][colonnaGiocatore] = ' ';
                this.rigaGiocatore++;
            }
            else if(risultato == -1){
                System.out.println("Il Gioco verrà chiuso automaticamente.");
                Main.waitSleep();
                System.exit(0);
            }
        }
        else if(this.mappa[rigaGiocatore+1][colonnaGiocatore] == 'E'){
            System.out.println("Cambiando stanza!");
            Main.waitSleep();
            return 10;
        }
        else {
            this.mappa[rigaGiocatore+1][colonnaGiocatore] = 'G';
            this.mappa[rigaGiocatore][colonnaGiocatore] = ' ';
            this.rigaGiocatore++;
        }
        return 0;
    }

    public void stampaMappa(int numeroStanza){
        Character[][] mappa = this.mappa;
        System.out.printf("""
                     Stanza Numero %d
                     ----- ----- ----- ----- -----          W -> su
                    |     |     |     |     |     |         A -> sx
                    |  %c  |  %c  |  %c  |  %c  |  %c  |         S -> giu
                    |     |     |     |     |     |         D -> dx
                     ----- ----- ----- ----- -----          I -> mostra arma
                    |     |     |     |     |     |         stat -> mostra statistiche giocatore
                    |  %c  |  %c  |  %c  |  %c  |  %c  |         cmd -> lista comandi
                    |     |     |     |     |     |         info -> mostra informazioni riguardo le caselle
                     ----- ----- ----- ----- -----          esc -> esci dal gioco
                    |     |     |     |     |     |
                    |  %c  |  %c  |  %c  |  %c  |  %c  |
                    |     |     |     |     |     |
                     ----- ----- ----- ----- -----
                    |     |     |     |     |     |
                    |  %c  |  %c  |  %c  |  %c  |  %c  |
                    |     |     |     |     |     |
                     ----- ----- ----- ----- -----
                    |     |     |     |     |     |
                    |  %c  |  %c  |  %c  |  %c  |  %c  |
                    |     |     |     |     |     |
                     ----- ----- ----- ----- -----
                    
                    %n""", numeroStanza, mappa[0][0], mappa[0][1], mappa[0][2], mappa[0][3], mappa[0][4], mappa[1][0], mappa[1][1], mappa[1][2], mappa[1][3], mappa[1][4], mappa[2][0], mappa[2][1], mappa[2][2], mappa[2][3], mappa[2][4], mappa[3][0], mappa[3][1], mappa[3][2], mappa[3][3], mappa[3][4], mappa[4][0], mappa[4][1], mappa[4][2], mappa[4][3], mappa[4][4]);
    }

    private void apriCassa(Protagonista giocatore, Dungeon dungeon){

        Main.clearConsole();
        Scanner scn = new Scanner(System.in);
        System.out.println("Cassa:");
        System.out.println(dungeon.getRegistroArmi().get(cassa.getContenuto()).toString());
        System.out.println("\nScambiare la propria arma (" + dungeon.getRegistroArmi().get(giocatore.getIdArma()) + ") per quella nella cassa?");
        String risposta;
        do{
            System.out.println("Inserire risposta (Si/No):");
            risposta = scn.nextLine();
            risposta = risposta.toUpperCase();
        }while(!Objects.equals(risposta, "SI") && !Objects.equals(risposta, "NO"));
        if(risposta.equals("SI")){
            StringBuilder armaGiocatore = giocatore.getIdArma();
            giocatore.setIdarma(this.cassa.getContenuto());
            this.cassa.setContenuto(armaGiocatore);
        }
    }

    private String generaNomeMostro(){
       String nomeMostro;
       boolean mostroSpeciale = false;
        int prefissoGenerato = rnd.nextInt(generatoreNomiMostri[0].length);
        nomeMostro = generatoreNomiMostri[0][prefissoGenerato];
        int ruoloGenerato = rnd.nextInt(generatoreNomiMostri[1].length);
        nomeMostro += " " + generatoreNomiMostri[1][ruoloGenerato];
        if(ruoloGenerato == 0){
            if(rnd.nextDouble() > 0.5){
                mostroSpeciale = true;
            }
        }
        if(mostroSpeciale){
            System.out.println("!!!!Attenzione a questo piano!!!!");
            Main.waitSleep();
            Main.waitSleep();
            nomeMostro = "1" + nomeMostro + " " + generatoreNomiMostri[3][rnd.nextInt(generatoreNomiMostri[3].length)];
        }
        else{
            int suffissoGenerato = rnd.nextInt(generatoreNomiMostri[2].length);
            nomeMostro = "0" + nomeMostro + " " + generatoreNomiMostri[2][suffissoGenerato];
        }
        return nomeMostro;
    }

    private int combattimento(Protagonista giocatore, Dungeon dungeon){
        Scanner scn =  new Scanner(System.in);
        boolean tentennamento = false;
        boolean abilitaUsata = false;
        boolean attaccoEseguito;
        do{
            attaccoEseguito = false;
            Main.clearConsole();
            System.out.println("\\\\ COMBATTIMENTO //");
            System.out.println("Nemico:");
            System.out.println(nemico.toString());
            System.out.println("Tu:");
            System.out.println(giocatore.toString());

            System.out.println("\n Menu' combattimento: ");
            System.out.println("""
                    ATT -> Attacca
                    AB -> Abilita' (1 volta per combattimento)
                    """);
            System.out.println("Inserire azione:");
            String azione = scn.next();
            int danno = 0;
            switch(azione.toUpperCase()){
                case "ATT":
                    System.out.println("Attacco!");
                    danno = giocatore.attacca(dungeon.getRegistroArmi());
                    attaccoEseguito = true;
                    Main.waitSleep();
                    break;
                case "AB":
                    if(!abilitaUsata) {
                        System.out.println("Abilita'!");
                        Main.waitSleep();
                        if (Objects.equals(dungeon.getRegistroArmi().get(giocatore.getIdArma()).getNomeAbilita(), "MISURA! + infliggi danno x 3") || Objects.equals(dungeon.getRegistroArmi().get(giocatore.getIdArma()).getNomeAbilita(), "SVUOTA! - infliggi danno x 3")) {
                            danno = giocatore.attacca(dungeon.getRegistroArmi()) * 3;
                            attaccoEseguito = true;
                        } else if (Objects.equals(dungeon.getRegistroArmi().get(giocatore.getIdArma()).getNomeAbilita(), "SCOSSA! - infiggli danno x 1,50 + causa tentennamento")) {
                            danno = (int)Math.round(giocatore.attacca(dungeon.getRegistroArmi()) * 1.50);
                            attaccoEseguito = true;
                            tentennamento = true;
                        }
                    }
                    else{
                        System.out.println("Abilita' gia' utilizzata!");
                    }
                    abilitaUsata = true;
                    break;
                default:
                    continue;
            }

            if(attaccoEseguito) {
                System.out.println(giocatore.getNome() + " infligge " + danno + " di danno a " + nemico.getNome());
                nemico.colpisci(danno);
                if(nemico.getHp() > 0) {
                    if (!tentennamento) {
                        System.out.println(nemico.getNome() + " attacca!");
                        int dannoMostro = nemico.attacca(dungeon.getRegistroArmi());
                        System.out.println(nemico.getNome() + " infligge " + dannoMostro + " a " + giocatore.getNome());
                        giocatore.colpisci(dannoMostro);
                        Main.waitSleep();
                    } else {
                        System.out.println(nemico.getNome() + " tentenna! Non riesce ad attaccare!");
                        Main.waitSleep();
                        tentennamento = false;
                    }
                }
            }

        }while(nemico.getHp() > 0 && giocatore.getHp() > 0);

        if(giocatore.getHp() > 0){
            System.out.println(nemico.getNome() + " sconfitto!");
            Main.waitSleep();
            System.out.println("Il nemico trasportava:");
            System.out.println(dungeon.getRegistroArmi().get(nemico.getIdArma()).toString());
            System.out.println("La vuoi raccogliere? (!!Il tuo oggetto verra' distrutto irreversibilmente!!");
            String risposta;
            do{
                System.out.println("Inserire risposta (Si/No):");
                risposta = scn.nextLine();
                risposta = risposta.toUpperCase();
            }while(!Objects.equals(risposta, "SI") && !Objects.equals(risposta, "NO"));
            if(risposta.equals("SI")){
                giocatore.setIdarma(this.nemico.getIdArma());
            }
            return 0;
        }
        else{
            System.out.println(nemico.getNome() + " ti ha sconfitto!");
            return -1;
        }
    }

}
