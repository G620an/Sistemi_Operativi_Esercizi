package Lettori_Scrittori_Monitor_Nativi;

import java.util.LinkedList;

public class BibliotecaImplFIFO implements Biblioteca{
    private int N;
    private LinkedList<Thread> codaAccesso;
    private boolean scrivendo;
    private int contaLettori;

    public BibliotecaImplFIFO(int N){
        this.N = N;
        codaAccesso = new LinkedList<>();
        scrivendo = false;
        contaLettori = 0;
    }

    public synchronized void inizioLettura(){
        try{
            codaAccesso.add(Thread.currentThread());
            while(scrivendo || codaAccesso.getFirst() != Thread.currentThread()){
                wait();
            }
            contaLettori++;
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public synchronized void fineLettura(){
        contaLettori--;
        codaAccesso.removeFirst();
        notifyAll();
    }

    public synchronized void inizioScrittura(){
        try{
            while(scrivendo ||  contaLettori > 0){
                wait();
            }
            scrivendo = true;
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public synchronized void fineScrittura(){
        scrivendo = false;
        notifyAll();
    }

    public void run(){
        for(int i = 0; i<N; i++){
            (new Thread(new LettoreImpl(i, this))).start();
            if (i%10 == 0){
                (new Thread(new ScrittoreImpl(i/10, this))).start();
            }
        }
        for(int i = 0; i<N/10; i++){

        }
    }
}
