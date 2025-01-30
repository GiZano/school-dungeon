package com.zanotti.dungeon;

import com.zanotti.dungeon.entita.Protagonista;
import com.zanotti.dungeon.oggetti.*;

import java.util.Scanner;

public class Main {

    static String schermataIniziale = """
             -----------------------------------------------------------------------------------
            /                                                                                   \\
            |                                                                                   |
            |                                                                                   |
            |                                                                                   |
            |                                                                                   |
            |                                                                                   |
            |                                                                                   |
            |                                 School Dungeon                                    |
            |                                                                                   |
            |                                                                                   |
            |                                                                                   |
            |                                                                                   |
            |                                                                                   |
            |                                                                                   |
            \\                                                                                   /
             -----------------------------------------------------------------------------------
           
            """;
    static String listaComandi = """
            W -> Vai su';
            A -> Vai a sinistra;
            S -> Vai giu';
            D -> Vai a destra;
            O -> Apri cassa;
            I -> Mostra arma;
            STAT -> Mostra statistiche
            CMD -> Lista comandi;
            ATT -> attacca mostro;
            ESC -> esci dal gioco;
            INFO -> stampa informazioni riguardo alle caselle;
            """;
    static String listaInfo = """
            \" \" -> casella libera
            \"X\" -> muro
            \"E\" -> uscita;
            \"C\" -> cassa;
            \"N\" -> nemico
            \"G\" -> giocatore (Tu);
            """;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        clearConsole();
        Dungeon dungeon = new Dungeon();
        String azione, nomeGiocatore;
        nomeGiocatore = start();
        Protagonista giocatore = new Protagonista(nomeGiocatore, dungeon.generaArma());
        dungeon.cambiaStanza();
        while(true){
            clearConsole();
            dungeon.getStanzaAttuale().stampaMappa(dungeon.getNumeroStanze());
            System.out.print("Inserire azione:");
            azione = sc.nextLine();

            switch(azione.toUpperCase()){
                case "W":
                    System.out.println("Vai su!");
                    waitSleep();
                    if(dungeon.getStanzaAttuale().muoviSu(giocatore, dungeon) == 10){
                        dungeon.cambiaStanza();
                    }
                    break;

                case "A":
                    System.out.println("Vai a sinistra!");
                    waitSleep();
                    if(dungeon.getStanzaAttuale().muoviSx(giocatore, dungeon) == 10){
                        dungeon.cambiaStanza();
                    }
                    break;

                case "S":
                    System.out.println("Vai giu!");
                    waitSleep();
                    if(dungeon.getStanzaAttuale().muoviGiu(giocatore, dungeon) == 10){
                        dungeon.cambiaStanza();
                    }
                    break;

                case "D":
                    System.out.println("Vai a destra!");
                    waitSleep();
                    if(dungeon.getStanzaAttuale().muoviDx(giocatore, dungeon) == 10){
                        dungeon.cambiaStanza();
                    }
                    break;
                case "I":
                    System.out.println("Mostra arma attuale!");
                    waitSleep();
                    giocatore.mostraArma(dungeon.getRegistroArmi());
                    System.out.print("INSERISCI UN VALORE QUALSIASI PER CONTINUARE:");
                    sc.next();
                    break;
                case "STAT":
                    System.out.println("Mostra statistiche!");
                    waitSleep();
                    clearConsole();
                    System.out.println(giocatore);
                    System.out.print("INSERISCI UN VALORE QUALSIASI PER CONTINUARE:");
                    sc.next();
                    break;
                case "CMD":
                    System.out.println("Mostra comandi!");
                    waitSleep();
                    clearConsole();
                    System.out.println("Lista Comandi: \n" + listaComandi);
                    System.out.print("INSERIRE QUALSIASI VALORE PER CONTINUARE:");
                    sc.next();
                    break;
                case "INFO":
                    System.out.println("Stampa info!");
                    waitSleep();
                    clearConsole();
                    System.out.println("Lista info: \n" + listaInfo);
                    System.out.print("INSERIRE QUALSIASI PER CONTINUARE:");
                    sc.next();
                    break;
                case "ESC":
                    System.out.println("Esci dal gioco!");
                    waitSleep();
                    System.exit(0);
                default:
            }
        }

    }

    public static void clearConsole() {
        System.out.println("\033[H\033[2J");
        /*
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("clear");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            System.out.println("Errore nel clear del terminale");
        }
        */

    }

    public static String start(){
        String nomeGiocatore;
        System.out.println("School Dungeon Version 1.0");
        clearConsole();
        System.out.println(schermataIniziale);
        System.out.println("INSERIRE QUALSIASI VALORE PER INIZIARE!");
        Scanner scn = new Scanner(System.in);
        scn.next();
        clearConsole();
        System.out.println("Lista comandi:");
        System.out.println(listaComandi);
        System.out.println("INSERIRE QUALSIASI VALORE PER CONTINUARE!");
        scn.next();
        clearConsole();
        System.out.println("Lista informazioni sulla mappa:");
        System.out.println(listaInfo);
        System.out.println("INSERIRE QUALSIASI VLAORE PER CONTINUARE!");
        scn.next();
        clearConsole();
        System.out.println("Come ti chiami?");
        nomeGiocatore = scn.next();
        System.out.println("Il dungeon ti da' il benvenuto, " + nomeGiocatore + ".");
        waitSleep();
        return nomeGiocatore;
    }

    public static void waitSleep(){
        int duration = 500;
        try{
            Thread.sleep(duration);
        }catch(InterruptedException ignored){}
    }

}
