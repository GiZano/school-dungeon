package com.zanotti.dungeon.oggetti;

import com.zanotti.dungeon.Main;
import com.zanotti.dungeon.entita.Protagonista;

import java.sql.SQLOutput;
import java.util.*;

public class Stanza {

    private Character[][] mappa = new Character[5][5];
    private Cassa cassa;
    private int cassaAttuale = 0;
    private int colonnaGiocatore = 0;
    private int rigaGiocatore = 4;

    public Stanza(ArrayList<String> mappe, StringBuilder contenutoCassa){
        Random random = new Random();
        int stanzaScelta = random.nextInt(mappe.size());
        int numeroCasse = 0;
        String mappaNonProcessata = mappe.get(stanzaScelta);
        int casellaAttuale = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                this.mappa[i][j] = mappaNonProcessata.toCharArray()[casellaAttuale];
                casellaAttuale++;
            }
        }
        for(int i = 0; i < mappaNonProcessata.length(); i++){
            if(mappaNonProcessata.charAt(i) == '3'){
                numeroCasse++;
            }
        }
        this.cassa = new Cassa(contenutoCassa);
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
            apriCassa(giocatore, dungeon);
            Main.waitSleep();
        }
        else if(this.mappa[rigaGiocatore][colonnaGiocatore+1] == 'E'){
            System.out.println("Cambiando stanza!");
            Main.waitSleep();
            return 10;
        }
        else {
            this.mappa[rigaGiocatore][colonnaGiocatore + 1] = 'G';
            this.mappa[rigaGiocatore][colonnaGiocatore] = '0';
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
        else if(this.mappa[rigaGiocatore-1][colonnaGiocatore] == 'E'){
            System.out.println("Cambiando stanza!");
            Main.waitSleep();
            return 10;
        }
        else {
            this.mappa[rigaGiocatore-1][colonnaGiocatore] = 'G';
            this.mappa[rigaGiocatore][colonnaGiocatore] = '0';
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
        else if(this.mappa[rigaGiocatore][colonnaGiocatore-1] == 'E'){
            System.out.println("Cambiando stanza!");
            Main.waitSleep();
            return 10;
        }
        else {
            this.mappa[rigaGiocatore][colonnaGiocatore-1] = 'G';
            this.mappa[rigaGiocatore][colonnaGiocatore] = '0';
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
        else if(this.mappa[rigaGiocatore+1][colonnaGiocatore] == 'E'){
            System.out.println("Cambiando stanza!");
            Main.waitSleep();
            return 10;
        }
        else {
            this.mappa[rigaGiocatore+1][colonnaGiocatore] = 'G';
            this.mappa[rigaGiocatore][colonnaGiocatore] = '0';
            this.rigaGiocatore++;
        }
        return 0;
    }

    public void stampaMappa(){
        Character[][] mappa = this.mappa;
        System.out.printf("""
                     ----- ----- ----- ----- -----          W -> su
                    |     |     |     |     |     |         A -> sx
                    |  %c  |  %c  |  %c  |  %c  |  %c  |         S -> giu
                    |     |     |     |     |     |         D -> dx
                     ----- ----- ----- ----- -----          O -> apri cassa
                    |     |     |     |     |     |         I -> mostra arma
                    |  %c  |  %c  |  %c  |  %c  |  %c  |         cmd -> lista comandi
                    |     |     |     |     |     |         att -> attacca mostro
                     ----- ----- ----- ----- -----          info -> mostra informazioni riguardo le caselle
                    |     |     |     |     |     |         esc -> esci dal gioco
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
                    
                    %n""", mappa[0][0], mappa[0][1], mappa[0][2], mappa[0][3], mappa[0][4], mappa[1][0], mappa[1][1], mappa[1][2], mappa[1][3], mappa[1][4], mappa[2][0], mappa[2][1], mappa[2][2], mappa[2][3], mappa[2][4], mappa[3][0], mappa[3][1], mappa[3][2], mappa[3][3], mappa[3][4], mappa[4][0], mappa[4][1], mappa[4][2], mappa[4][3], mappa[4][4]);
    }

    public void apriCassa(Protagonista giocatore, Dungeon dungeon){

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
            giocatore.setArma(this.cassa.getContenuto());
            this.cassa.setContenuto(armaGiocatore);
        }
    }

    public Cassa getCassa() {
        return this.cassa;
    }

    public Character[][] getMappa() {
        return this.mappa;
    }
}
