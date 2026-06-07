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
    public void pazienteEntra(){
        this.sdaC.signal();
    }

    @Override
    public void pazienteEsci(){

    }

    @Override
    public void chiamaEIniziaOperazione()throws InterruptedException{

    }

    @Override
    public void fineOperazione()throws InterruptedException{

    }
}
