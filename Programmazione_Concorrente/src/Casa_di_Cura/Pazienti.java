public interface Paziente extends Runnable{
    public static Random rand = new Random();
    public CasaDiCura getCasaDiCura();
    //I thread Pazienti chiameranno i propri metodi nella Casa di Cura
}