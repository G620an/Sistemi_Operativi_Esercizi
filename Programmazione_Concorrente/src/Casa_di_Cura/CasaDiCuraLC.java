package Casa_di_Cura;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class CasaDiCuraLC extends CasaDiCura{
    //Usare Lock e Condiction
    //Con var booleane e linkedlist
    private int ID;
    private int count;
    private static final Random r = new Random();
    private Lock sda;
    private int pis; //Posti in sala
    private Lock mutexOp;
    private Lock rM;
    private Condition sdaC;
    private Condition mutexC;
    private Condition rMC;
    private int psda;
    private boolean opLib;
    private boolean chiama;

    public CasaDiCuraLC(int ID){
        this.ID = ID;
        this.count = 1;
        this.rM = new ReentrantLock();
        this.sda = new ReentrantLock();
        this.mutexOp = new ReentrantLock();
        this.pis = 3;
        this.sdaC = this.sda.newCondition();
        this.mutexC = this.mutexOp.newCondition();
        this.rMC = this.rM.newCondition();
        this.psda = 0;
        this.opLib = true;
        this.chiama = false;
    }

    public int getID() {
        return ID;
    }

    public int getCount(){
        return this.count;
    }

    public void addCount(){
        this.count++;
    }

    @Override
    public void pazienteEntra() throws InterruptedException{
        try{
            this.sda.lock(); //La lock è come l'acquire
            while(this.psda >= 3){
                this.sdaC.await(); //Aspetta sulla condizione
            }
            this.psda ++;
            while(!this.opLib){
                this.mutexC.await();
            }
            this.mutexOp.lock();
            this.opLib = false;
            this.psda --;
            this.rMC.signal();
            this.chiama = true;
        }finally{
            this.sda.unlock();
        }
    }

    @Override
    public void pazienteEsci(){

    }

    @Override
    public void chiamaEIniziaOperazione()throws InterruptedException{
        this.rM.lock();
        while(!this.chiama){
            this.rMC.await();
        }
        this.chiama = false;
    }

    @Override
    public void fineOperazione()throws InterruptedException{
        this.rM.unlock();
        this.mutexC.signal();
    }
}
