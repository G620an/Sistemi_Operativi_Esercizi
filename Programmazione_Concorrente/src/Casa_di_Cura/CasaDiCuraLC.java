package Casa_di_Cura;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class CasaDiCuraLC extends CasaDiCura{
    //Usare Lock e Condiction
    //Con var booleane e linkedlist
    private int ID;
    private int count;
    private LinkedList<Paziente> sala;
    private final static int POSTI = 3;
    private int contaPosti;
    private Lock lock;
    private Condition sdaC;
    private Condition operazioneC;
    private boolean libero;
    private Condition operandoC;
    private Condition risvegliaMC;



    public CasaDiCuraLC(int ID){
        this.ID = ID;
        this.count = 0;
        this.contaPosti = 0;
        this.sala = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.sdaC = this.lock.newCondition();
        this.operazioneC = this.lock.newCondition();
        this.libero = true;
        this.operandoC = this.lock.newCondition();
        this.risvegliaMC = this.lock.newCondition();

    }

    public int getCount() {
        return count;
    }

    public void addCount(){
        this.count++;
    }

    public int getID() {
        return ID;
    }

    @Override
    public void pazienteEntra()throws InterruptedException{
        try{
            this.lock.lock();
            while(this.contaPosti > CasaDiCuraLC.POSTI) {
                this.sdaC.await();
            }
            this.contaPosti++;
            while(!this.libero){
                this.operazioneC.await();
            }
            this.libero = false;
            this.risvegliaMC.signal();
        }finally{
                this.lock.unlock();
        }
    }

    @Override
    public void pazienteEsci()throws InterruptedException{
        try{
            this.lock.lock();
            this.operandoC.await();
        }finally{
            this.lock.unlock();
        }
    }

    @Override
    public void chiamaEIniziaOperazione()throws InterruptedException{
        try{
            this.lock.lock();
            this.risvegliaMC.await();
            this.contaPosti--;
        }finally{
            this.lock.unlock();
        }

    }

    @Override
    public void fineOperazione()throws InterruptedException{
        try{
            this.lock.lock();
            this.operandoC.signal();
        }finally{
            this.lock.unlock();
        }
        System.out.println("Sono il medico, sto preparando la sala operatoria...");
        Thread.sleep(20000);
        System.out.println("Sono il medico, sala operatoria pronta!");
        try{
            this.lock.lock();
            this.libero = true;
            this.operazioneC.signal();
        }finally{
            this.lock.unlock();
        }
    }
}
