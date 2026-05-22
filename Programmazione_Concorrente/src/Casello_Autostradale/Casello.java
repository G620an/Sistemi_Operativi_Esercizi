package Casello_Autostradale;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Casello extends Thread {
    public static int N;
    private static int incasso;
    private static PortaImpl[] porte;
    private static Semaphore[] semafori;
    private static Semaphore mutex;

    public  Casello(int N){
        this.N = N;
        incasso = 0;
        porte = new PortaImpl[N];
        semafori = new Semaphore[N];
        mutex = new Semaphore(1);
        for (int i = 0; i < N; i++) {
            semafori[i] = new Semaphore(1, true);
            porte[i] = new PortaImpl(i);
        }
    }

    public int getIncasso(){
        return incasso;
    }

    public void run(){
        for(int i=0; i<N; i++){
            porte[i].start();
        }
        int i = 1;
        while(true){
            try {
                this.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Incasso: "+this.getIncasso()+"€"+" "+i+"s");
            i++;
        }
    }

    public static class PortaImpl extends PortaAbstract{
        private int ID;
        private Veicolo v;
        private Semaphore vuoto;
        public PortaImpl(int ID) {
            this.ID = ID;
            this.setDaemon(true);
            vuoto = new Semaphore(0);
        }

        @Override
        public int getID(){
            return ID;
        }

        @Override
        public void eseguiPagamento(){
            try {
                this.sleep(1000 * rand.nextInt(3, 7));
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        public void aggiungi(Veicolo v){
            this.v = v;
        }

        public Semaphore getVuoto(){
            return vuoto;
        }

        public void run(){
            while(true) {
                try {
                    vuoto.acquire();
                    this.eseguiPagamento();
                    mutex.acquire();
                    incasso += this.calcolaPagamento(v.getX());
                    mutex.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                semafori[ID].release();
            }
        }
    }

    public static class VeicoloImpl extends VeicoloAbstract{
        private int ID;
        private int x;

        public VeicoloImpl(int ID){
            this.ID = ID;
            this.x = rand.nextInt(50,101);
        }

        @Override
        public int getID() {
            return ID;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public void scegliPorta(){
            int n = rand.nextInt(N);
            try {
                semafori[n].acquire();
                porte[n].aggiungi(this);
                porte[n].vuoto.release();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        public void run(){
            this.percorri();
            this.scegliPorta();
        }

        @Override
        public void percorri(){
            int s = this.getX()*this.VELOCITA;
            try {
                this.sleep(1000 * s);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}