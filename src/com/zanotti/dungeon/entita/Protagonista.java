package com.zanotti.dungeon.entita;

import com.zanotti.dungeon.Main;
import com.zanotti.dungeon.oggetti.Arma;

import java.util.HashMap;
import java.util.Random;

public class Protagonista extends Entita {

    private int esperienza;
    private int maxHp;
    private int livello = 0;

    public Protagonista(String nome, StringBuilder idArma){
        super(nome, idArma);
        this.attacco = 10;
        this.difesa = 10;
        this.esperienza = 0;
        this.maxHp = 100;
    }

    public void setIdarma(StringBuilder idArma) {
        this.idArma = idArma;
    }

    public void mostraArma(HashMap<StringBuilder, Arma> registroArmi){
        Main.clearConsole();
        System.out.println("Arma | Giocatore");
        System.out.println(registroArmi.get(idArma).toString());
    }

    @Override
    public int attacca(HashMap<StringBuilder, Arma> registroArmi){
        int danno = this.attacco;
        danno += (registroArmi.get(idArma).getAttacco());
        return danno;
    }

    public void aggiungiExp(int esperienza){
        Random rnd = new Random();
        this.esperienza += esperienza;
        if(this.esperienza >= 10){
            System.out.println(this.nome + " sale di livello!");
            this.livello++;
            this.esperienza -= 10;
            this.attacco += rnd.nextInt(10);
            this.difesa += rnd.nextInt(10);
            this.maxHp += rnd.nextInt(20);
            this.hp = this.maxHp;
            Main.waitSleep();
        }
    }

    public void cura(int quantitaCura){
        this.hp += quantitaCura;
    }

    @Override
    public String toString(){
        return "Giocatore | " + this.nome + " livello " + this.livello + "\n Attacco: " + this.attacco + "\n Difesa: " + this.difesa + "\n" + "HP: " + this.hp;
    }

}
