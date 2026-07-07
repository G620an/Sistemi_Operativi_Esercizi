package Esame_SISOP;

import java.util.Random;

public interface Studente extends Runnable{
    public static final Random r = new Random();
    public int getMAT();
    public Esame getEsame();
}
