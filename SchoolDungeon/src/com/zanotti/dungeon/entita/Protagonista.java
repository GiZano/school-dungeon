package com.zanotti.dungeon.entita;

import com.zanotti.dungeon.Main;
import com.zanotti.dungeon.oggetti.Arma;
import com.zanotti.dungeon.oggetti.Dungeon;

import java.util.HashMap;

public class Protagonista extends Entita {

    private String nome;
    private int attacco;
    private int difesa;
    private StringBuilder idArma;

    public Protagonista(String nome, StringBuilder idArma){
        this.nome = nome;
        this.idArma = idArma;
        this.attacco = 1;
        this.difesa = 1;
    }

    public StringBuilder getIdArma() {
        return idArma;
    }

    public void setArma(StringBuilder idArma) {
        this.idArma = idArma;
    }

    public void mostraArma(HashMap<StringBuilder, Arma> registroArmi){
        Main.clearConsole();
        System.out.println("Arma | Giocatore");
        System.out.println(registroArmi.get(idArma).toString());
    }
}
