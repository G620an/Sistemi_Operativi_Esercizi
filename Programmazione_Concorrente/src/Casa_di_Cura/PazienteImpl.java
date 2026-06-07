package Casa_di_Cura;

public class PazienteImpl extends PazienteAbstract{
    private int ID;
    private CasaDiCura cdc;

    public PazienteImpl(int ID, CasaDiCura cdc){
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
            cdc.pazienteEntra();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}