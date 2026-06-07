package Casa_di_Cura;

public class CasaDiCuraS extends CasaDiCura{
    //Usare i semafori
    private Semaphore sda;
    private Semaphore mutexOp;
    private Semaphore rM;
    private static final int count = 0;
    private static final Random r = new Random();

    public CasaDiCuraS(){
        this.sda = new Semaphore(3); //sda è il semaforo della Sala Di Attesa
        this.mutexOp = new Semaphore(1, true); //mutex per l'accesso alla sala operatoria
        this.rM = new Semaphore(0); //semaforo di risveglio medico
    }

    public void lancioCasualePaziente(){
        this.sleep(this.r.nextInt(1000, 10000));
        p = new PazienteImpl(this.count, this);
        this.count++;
        Thread t = new Thread(p);
        p.start()
    }

    public void pazienteEntra(){
        this.sda.acquire()
        this.mutexOp.acquire()
        this.rm.release()
    }

    public void pazienteEsci(){
        this.sda.release();
    }

    public void chiamaEIniziaOperazione(){
        this.rM.acquire();
    }

    public void fineOperazione(){
        this.mutexOp.release();
    }

    public void run(){
        Medico medico = new MedicoImpl(1, this);
        Thread tm = new Thread(medico);
        tm.start();
        while (true){
            this.lancioCasualePaziente();
        }
    }

    public static class PazienteImpl extends PazienteAbstract{
        private int ID;
        private CasaDiCura cdc;

        public PazienteImpl(int ID, CasaDICura cdc){
            this.ID = ID;
            this.cdc = cdc;
        }

        public CasaDiCura getCasaDiCura(){
            return this.cdc; 
        }

        public int getID(){
            return this.ID;
        }

        public void run(){
            cdc.pazienteEntra()

        }
    }

    public static class MedicoImpl extends MedicoAbstract{
    private CasaDiCura cdc;
    private int ID;

    
    public MedicoImpl(int ID, CasaDICura cdc){
        this.setDeamon(true);
        this.ID = ID;
        this.cdc = cdc;
    }

    public CasaDiCura getCasaDiCura(){
        return this.cdc
    }

    public int getID(){
        return this.ID
    }

    public void run(){
        while(true){
            this.cdc.chiamaEIniziaOperazione();
            this.sleep(this.rand.nextInt(20000, 40000));
            this.cdc.fineOperazione();
            this.cdc.pazienteEsci();
        }
    }

}
}   
