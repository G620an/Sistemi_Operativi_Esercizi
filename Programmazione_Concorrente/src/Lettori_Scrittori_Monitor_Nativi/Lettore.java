package Lettori_Scrittori_Monitor_Nativi;

import java.util.Random;

public interface Lettore extends Runnable{
    public static final Random r = new Random();
    public Biblioteca getBib();
    public int getID();
}
