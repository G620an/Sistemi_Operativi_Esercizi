package Esame_SISOP;

import java.util.Random;

public interface Esame extends Runnable{
    public static final Random r = new Random();
    public static final int maxVoto = 33;
    public static final int minVoto = 3;
    public static final int passaScritto = 15;
    public static final int passaEsame = 18;
    public static final int maxPostiScritto = 8;
    public static final int maxPostiOrale = 2;

    public boolean scritto(int MAT);
    public boolean orale(int MAT);
    public int votoComplessivo(int MAT);
}
