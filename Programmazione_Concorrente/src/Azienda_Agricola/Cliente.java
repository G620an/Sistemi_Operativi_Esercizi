package Azienda_Agricola;

import java.util.Random;

public class Cliente extends Thread{
    private static Random r;
    private static final int maxSacchi = 10;
    private AziendaAgricola az;
    private int ID;
    public Cliente(int ID, AziendaAgricola az) {
        this.az = az;
        this.ID = ID;
        r = new Random();
    }
    public void run(){
        int qta = r.nextInt(1, maxSacchi+1);
        System.out.println("Sono il cliente " + ID + " voglio comprare " + qta + " sacchi");
        az.paga(qta);
        System.out.println("Sono il cliente " + ID + " ora cerco di mettere in macchina i sacchi");
        az.spostaSacchetti(qta);
        System.out.println("Sono il cliente " + ID + " ho finito");
    }
}
