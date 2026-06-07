package Casa_di_Cura;

import java.util.Random;

public interface Medico extends Runnable{
    public static Random rand = new Random();
    public int getID();
    public CasaDiCura getCasaDiCura();
    //I Thread Medici (o medico) chiameranno le proprie operazioni nella casa di cura
}
