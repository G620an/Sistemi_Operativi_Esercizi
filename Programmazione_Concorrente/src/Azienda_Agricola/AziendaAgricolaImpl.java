package Azienda_Agricola;

import java.util.concurrent.Semaphore;

public class AziendaAgricolaImpl implements AziendaAgricola {
    private Semaphore magazzino;
    private Semaphore rifornisci;
    private Semaphore mutexCassa;
    private Semaphore mutexSacchetti;
    private Semaphore mutex;
    private int contaN;
    private static int N;
    private static int contoCassa;

    public AziendaAgricolaImpl(int N) {
        this.N = N;
        contoCassa = 0;
        contaN = N;
        mutex = new Semaphore(1, true);
        magazzino = new Semaphore(sacchi);
        rifornisci = new Semaphore(0);
        mutexCassa = new Semaphore(1, true);
        mutexSacchetti = new Semaphore(1, true);
    }

    @Override
    public void paga(int qta) {
        try{
            this.magazzino.acquire(qta);
            this.mutexCassa.acquire();
            contoCassa+=costo*qta;
            this.mutexCassa.release();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void spostaSacchetti(int qta) {
        try{
            this.mutexSacchetti.acquire();
            for(int i = 0; i<qta; i++){
                Thread.sleep(tSpostamento);
                this.rifornisci.release();
            }
            this.mutex.acquire();
            this.contaN --;
            if (contaN == 0){
                rifornisci.release(sacchi);
            }
            this.mutex.release();
            this.mutexSacchetti.release();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }

    @Override
    public void rifornisci(){
        try{
            rifornisci.acquire(sacchi);
            if(contaN ==0){
                return;
            }
            System.out.println("Sono il magazziniere, sto rifornendo...");
            Thread.sleep(tRifornimento);
            magazzino.release(sacchi);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void run(){
        for(int i = 0; i<N; i++){
            (new Cliente(i, this)).start();
        }
        while(this.contaN > 0){
            this.rifornisci();
        }
        System.out.println(this.toString());
    }

    public String toString() {
        return "Cassa: " + this.contoCassa + "€";
    }
}
