package Casa_di_Cura;

import java.util.concurrent.Semaphore;

public class CasaDiCuraS extends CasaDiCura{
    //Usare i semafori
    private int ID;
    private Semaphore sda;
    private Semaphore mutexOp;
    private Semaphore rM;
    private Semaphore op;
    private int count = 0;

    public CasaDiCuraS(int ID){
        this.ID = ID;
        this.sda = new Semaphore(3); //sda è il semaforo della Sala Di Attesa
        this.mutexOp = new Semaphore(1, true); //mutex per l'accesso alla sala operatoria
        this.rM = new Semaphore(0); //semaforo di risveglio medico
        this.op = new Semaphore(0); //semaforo per trattenere il paziente nella sala operatoria

    }

    public int getID(){
        return this.ID;
    }

    public int getCount(){
        return this.count;
    }

    public void addCount(){
        this.count++;
    }

    public void pazienteEntra() throws InterruptedException {
        this.sda.acquire();
        this.mutexOp.acquire();
        this.rM.release();
    }

    public void pazienteEsci() throws InterruptedException {
        this.op.acquire();
    }

    public void chiamaEIniziaOperazione()throws InterruptedException{
        this.rM.acquire();
        this.sda.release();
    }

    public void fineOperazione()throws InterruptedException{
        this.op.release();
        System.out.println("Sono il medico, sto preparando la sala operatoria...");
        Thread.sleep(20000);
        System.out.println("Sono il medico, sala operatoria pronta!");
        this.mutexOp.release();
    }
}   
