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
                Thread.sleep(this.rand.nextInt(20000, 40000));
                this.cdc.fineOperazione();
                this.cdc.pazienteEsci();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}
