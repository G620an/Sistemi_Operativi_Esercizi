package Casa_di_Cura;

public interface Paziente extends Runnable{
    public static Random rand = new Random();
    public int getID();
    public CasaDiCura getCasaDiCura();
    //I thread Pazienti chiameranno i propri metodi nella Casa di Cura
}
