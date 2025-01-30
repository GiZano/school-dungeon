package com.zanotti.dungeon.entita;

import com.zanotti.dungeon.oggetti.Arma;

import java.util.HashMap;

public abstract class Entita {

    protected String nome;
    protected int attacco;
    protected int difesa;
    protected StringBuilder idArma;
    protected int hp;

    public Entita(String nome, StringBuilder idArma) {
        this.nome = nome;
        this.idArma = idArma;
        this.hp = 100;
    }

    public abstract int attacca(HashMap<StringBuilder, Arma> registroArmi);

    public int getHp(){
        return this.hp;
    }

    public String getNome(){
        return this.nome;
    }

    public StringBuilder getIdArma(){
        return this.idArma;
    }

    public void colpisci(int danno){
        danno -= (difesa/10);
        this.hp -= danno;
    }

}
