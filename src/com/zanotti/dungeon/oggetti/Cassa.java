package com.zanotti.dungeon.oggetti;

public class Cassa {

    private int id;
    private StringBuilder contenuto;

    public Cassa(StringBuilder contenuto) {
        this.contenuto = contenuto;
    }

    public StringBuilder getContenuto(){ return contenuto; }
    public void setContenuto(StringBuilder contenuto){ this.contenuto = contenuto; }

}
