package Casello_Autostradale;

import java.util.Random;
import java.util.concurrent.Semaphore;

public interface Porta {
    public static final int T = 2; //€
    public static final Random rand = new Random();

    int getID();

    default int calcolaPagamento(int X){
        return T*X;
    }

    void eseguiPagamento();

    void aggiungi(Veicolo v);

    Semaphore getVuoto();

}
