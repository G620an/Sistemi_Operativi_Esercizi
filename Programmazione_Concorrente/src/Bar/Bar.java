package Bar;

import java.util.Random;

public interface Bar extends Runnable{
    public static final Random r = new Random();
    public int getID();
    public boolean cassaLibera();
    public boolean bancoLibero();
    public boolean confrontaFile(); //True se la fila della cassa minore o uguale a quella del banco
    public void pagaAllaCassa();
    public void beviCaffe();
    public void serviCliente();
}
