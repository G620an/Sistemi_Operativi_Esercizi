public interface Medico extends Runnable{
    public static Random rand = new Random();
    public CasaDiCura getCasaDiCura();
    //I Thread Medici (o medico) chiameranno le proprie operazioni nella casa di cura
}