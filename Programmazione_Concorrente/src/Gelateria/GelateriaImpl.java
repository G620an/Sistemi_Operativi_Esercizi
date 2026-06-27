package Gelateria;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GelateriaImpl implements Gelateria{
    private int ID;
    private int idCliente = 0;
    private int N;
    private Lock lock;
    private Condition paga;
    private Condition mangia;
    private final int maxPosti = 5;
    private int postiCor;
    private List<Thread> filaCassa;
    private boolean cassaLibera;
    private int cassa;
    public GelateriaImpl(int ID, int N){
        this.ID = ID;
        this.N = N;//Numero di Thread clienti da lanciare
        this.lock = new ReentrantLock();
        this.paga = this.lock.newCondition();
        this.mangia = this.lock.newCondition();
        this.postiCor = 0;
        this.filaCassa = new LinkedList<>();
        this.cassaLibera = true;
        this.cassa=0;
    }

    @Override
    public void mangiaGelato() {
        try{
            this.lock.lock();
            while(this.postiCor > this.maxPosti){
                this.mangia.await();
            }
            Thread.sleep(r.nextInt(5000, 30000)); //Il cliente impiega tra i 10 e i 60s per mangiare il geltato
            //Il cliente finisce di mangiare il gelato, si alza e va a pagare lasciando il posto libero
            this.mangia.signal(); //Uno tra i clienti in attesa si siede e mangia
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            this.lock.unlock();
        }
    }

    public void pagaGelato(){
        try{
            this.lock.lock();
            this.filaCassa.add(Thread.currentThread());
            while(!this.cassaLibera && this.filaCassa.getFirst() == Thread.currentThread()){
                this.paga.await();
            }
            int quantoPaga = r.nextInt(10, 20); //Il cliente paga tra i 10 e i 20€ per gelato
            this.cassa += quantoPaga;
            Thread.sleep(r.nextInt(1000, 30000)); //Il cliente impiega tra i 1 e i 10s per pagare
            this.filaCassa.removeFirst();
            this.cassaLibera = false;
            this.paga.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.lock.unlock();
        }
    }

    @Override
    public void run(){
        for(int i=0; i<this.N; i++){
            (new Thread(new ClienteImpl(this.idCliente, this))).start();
            this.idCliente++;
        }
        while(true);
    }

}
