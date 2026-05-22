package Casello_Autostradale;

import java.util.Random;

public interface Veicolo{
    public static final int VELOCITA = 1;
    public static Random rand = new Random();
    int getID();
    int getX();
    void scegliPorta();
    void percorri();
}
