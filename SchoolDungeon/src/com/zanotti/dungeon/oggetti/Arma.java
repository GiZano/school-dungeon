package com.zanotti.dungeon.oggetti;

import java.util.Objects;
import java.util.Random;

public class Arma {

    private String nome;
    private int attacco;
    private String nomeAbilita;
    private String tipo;

    public Arma(String nome, int numeroStanza, String tipo) {
        Random rnd = new Random();
        this.attacco = (int)Math.round(rnd.nextDouble()*10) + 5;
        this.nome = nome;
        this.tipo = tipo;
        if(Objects.equals(tipo, "Righello")){
            this.nomeAbilita = "MISURA!";
        }
        else if(Objects.equals(tipo, "Tastiera")){
            this.nomeAbilita = "SCOSSA!";
        }
        else if(Objects.equals(tipo, "Astuccio")){
            this.nomeAbilita = "SVUOTA!";
        }
    }

    public void usaAbilitaSpeciale(){

    };

    public String getNome() {
        return nome;
    }
    public int getAttacco() {
        return attacco;
    }
    public String getNomeAbilita() {
        return nomeAbilita;
    }
    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return (nome + ".\n Attacco: " + attacco + "\n Abilita': " + nomeAbilita);
    }
}
