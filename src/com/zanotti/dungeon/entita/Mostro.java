package com.zanotti.dungeon.entita;

import com.zanotti.dungeon.oggetti.Arma;
import java.util.Random;
import java.util.HashMap;

public class Mostro extends Entita {

    private int livello;

    public Mostro(String nome, StringBuilder idArma, int numeroStanza, boolean mostroSpeciale){
        super(nome, idArma);
        Random rnd = new Random();
        this.livello = numeroStanza;
        if(mostroSpeciale) {
            this.attacco = (int) (rnd.nextDouble() * 10) * numeroStanza*5;
            this.difesa = (int) (rnd.nextDouble() * 10) * numeroStanza*5;
        }
        else{
            this.attacco = (int) (rnd.nextDouble() * 10) * numeroStanza / 2;
            this.difesa = (int) (rnd.nextDouble() * 10) * numeroStanza / 2;
        }
        this.hp = 50 + (int)(rnd.nextDouble()*10)*numeroStanza/2;
    }

    public int attacca(HashMap<StringBuilder, Arma> registroArmi){
        Random rnd = new Random();
        int danno = (int)(livello * rnd.nextDouble());
        danno += registroArmi.get(idArma).getAttacco();
        danno += this.attacco;
        return danno;
    }

    @Override
    public String toString(){
        return "Mostro | " + this.nome + "\n Attacco: " + this.attacco + "\n Difesa: " + this.difesa + "\n" + "HP: " + this.hp + "\n";
    }

}
