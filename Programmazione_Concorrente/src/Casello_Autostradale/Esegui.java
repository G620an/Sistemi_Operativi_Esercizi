package Casello_Autostradale;

import java.util.Random;

public class Esegui extends Thread {
    private int N;
    private Random rand = new Random();
    public Esegui(int N) {
        this.N = N;
    }
    public void run(){
        try {
            Casello c = new Casello(N);
            c.start();
            int i = 0;
            while (true) {
                (new Casello.VeicoloImpl(i)).start();
                i++;
                this.sleep(rand.nextInt(100,2000));
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
