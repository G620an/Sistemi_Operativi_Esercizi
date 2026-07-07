package Funivia;

import java.util.Random;

public interface Funivia extends Runnable {
    public static final Random r = new Random();
    public static int maxPosti = 6;
    public static int postiTuristaPiedi = 1;
    public static int postiTuristaBici = 2; //Perchè la bici occupa un posto
    public static int minutoMilli = 600;
    void pilotaStart();
    void pilotaEnd();
    void turistaSali(int t, int ID); //t definiscce il tipo di turista 0: turista a piedi ; 1: turista in bici
    void turistaScendi(int t, int ID);

}
