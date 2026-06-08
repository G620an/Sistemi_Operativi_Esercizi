package Casa_di_Cura;

public class MedicoImpl extends MedicoAbstract{
    private CasaDiCura cdc;
    private int ID;


    public MedicoImpl(int ID, CasaDiCura cdc){
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
        try {
            while (true) {
                this.cdc.chiamaEIniziaOperazione();
                System.out.println("Sono il medico, sto iniziando l'operazione!");
                Thread.sleep(this.rand.nextInt(20000, 40000));
                System.out.println("Sono il medico, ho finito l'operazione!");
                this.cdc.fineOperazione();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}
