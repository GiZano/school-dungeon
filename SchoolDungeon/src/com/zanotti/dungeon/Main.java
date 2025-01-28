package com.zanotti.dungeon;

import com.zanotti.dungeon.*;

public class Main {

    static String Schermata_Iniziale = """
             ------------------------------
            /                              \\
            |                              |
            |                              |
            |       School Dungeon         |
            |                              |
            |                              |
            \\                              /
             ------------------------------
            Inserire un qualsiasi tasto per iniziare:
           
            """;

    public static void main(String[] args) {
        System.out.println("School Dungeon Version 1.0");
        System.out.println(Schermata_Iniziale);

    }

    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
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
    }
}
