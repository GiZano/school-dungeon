package com.zanotti.dungeon.oggetti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Dungeon {

    private ArrayList<Stanza> stanze = new ArrayList<>();
    private ArrayList<String> mappe = new ArrayList<>();
    private HashMap<StringBuilder, Arma> registroArmi = new HashMap<>();
    private String[][] generatoreNome = {{"Leggendario/a", "Epico/a", "Scadente", "Eccellente", "Rancoroso/a"},
                                         {"della Preside", "del prof di Matematica", "della Vicepreside", "del Benatti", "del Piccolo", "del Maturando", "della prof di Sostegno"}};
    private int numeroStanze;

    public Dungeon() {
        this.mappe.add("   XE X X CX N XX X G  X ");
        this.mappe.add("CXXXE   X  XNX  X X GX   ");
        this.mappe.add("E  N X XX X CX XXXX G    ");
        this.mappe.add("      XXX  NCX  XXX GXE  ");
        this.mappe.add("CN  EX XX X XX X XX G    ");
        this.mappe.add("CXXXX N XXEX XXXX XXG  XX");
        this.mappe.add("C E            XXNXXG    ");
        this.numeroStanze = 0;
    }

    public void cambiaStanza(){
        StringBuilder idLoot = generaArma();
        StringBuilder idArmaMostro = generaArma();
        this.stanze.add(new Stanza(this.mappe, idLoot, idArmaMostro, numeroStanze));
        this.numeroStanze++;
    }

    public Stanza getStanzaAttuale(){
        return this.stanze.get(this.numeroStanze-1);
    }

    public StringBuilder generaArma(){
        Random rnd = new Random();
        rnd.nextInt();
        int armaGenerata = (int)(rnd.nextDouble()*10);
        StringBuilder id = new StringBuilder((this.numeroStanze+1)+"A");
        String nomeArma;
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

    public int getNumeroStanze() {
        return numeroStanze;
    }
}
