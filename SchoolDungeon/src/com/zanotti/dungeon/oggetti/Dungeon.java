package com.zanotti.dungeon.oggetti;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Dungeon {

    private ArrayList<Stanza> stanze = new ArrayList<>();
    private ArrayList<String> mappe = new ArrayList<>();
    private HashMap<StringBuilder, Arma> registroArmi = new HashMap<>();
    private String[][] generatoreNome = {{"Leggendario/a", "Epico/a", "Scadente", "Eccellente", "Rancoroso/a"},
                                         {"della Preside", "del prof di Matematica", "della Vicepreside", "del Benatti", "del Piccolo", "del Maturando", "della prof di Sostegno"}};
    private final int numeroTipiArmi = 3;
    private int numeroStanze;

    public Dungeon() {
        this.mappe.add("000XE0X0X0CX000XX0X0G00X0");
        this.mappe.add("CXXXE000X00X0X00X0X0GX000");
        this.numeroStanze = 0;
    }

    public void cambiaStanza(){
        StringBuilder idLoot = generaArma();
        this.stanze.add(new Stanza(this.mappe, idLoot));
        this.numeroStanze++;
    }

    public Stanza getStanzaAttuale(){
        return this.stanze.get(this.numeroStanze-1);
    }

    public StringBuilder generaArma(){
        Random rnd = new Random();
        rnd.nextInt();
        int armaGenerata = (int)(rnd.nextDouble()*10);
        System.out.println("TIPO ARMA: " + armaGenerata);
        StringBuilder id = new StringBuilder((this.numeroStanze+1)+"A");
        String nomeArma = "";
        String tipoArma;
        String prefisso = generatoreNome[0][rnd.nextInt(generatoreNome[0].length)];
        String suffisso;
        if(registroArmi.isEmpty()){
            suffisso = "dello Studente Bocciato";
        }else {
            suffisso = generatoreNome[1][rnd.nextInt(generatoreNome[1].length)];
        }

        if(armaGenerata <= 3){
            id.append('R' + (this.registroArmi.size()));
            tipoArma = "Righello";
            nomeArma = prefisso + " " + tipoArma + " " + suffisso;
            this.registroArmi.put(id, new Arma(nomeArma, numeroStanze+1, tipoArma));
        }
        else if(armaGenerata > 3 && armaGenerata < 7){
            id.append('T' + (this.registroArmi.size()));
            tipoArma = "Tastiera";
            nomeArma = prefisso + " " + tipoArma + " " + suffisso;
           this.registroArmi.put(id, new Arma(nomeArma, numeroStanze+1, tipoArma));
        }
        else if(armaGenerata >= 7){
            id.append('A' + this.registroArmi.size());
            tipoArma = "Astuccio";
            nomeArma = prefisso + " " + tipoArma + " " + suffisso;
            this.registroArmi.put(id, new Arma(nomeArma, numeroStanze+1, tipoArma));
        }
        return id;
    }

    public HashMap<StringBuilder, Arma> getRegistroArmi() {
        return registroArmi;
    }
}
