package Lettori_Scrittori_Monitor_Nativi;

public class BibliotecaImpl implements Biblioteca {
    private int N;
    private int contaLettori;
    private Boolean scrittura;
    private Boolean scrivendo;

    public BibliotecaImpl(int N) {
        this.N = N;
        this.contaLettori = 0;
        this.scrittura = false;
        this.scrivendo = false;
    }

    public synchronized void inizioLettura(){
        try{
            while(scrivendo){
                wait();
            }
            contaLettori++;
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public synchronized void fineLettura(){
        contaLettori--;
        if(contaLettori == 0){
            scrittura = true;
            notifyAll();
        }
    }

    public synchronized void inizioScrittura(){
        try{
            while(scrivendo || contaLettori > 0){
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
