package Casa_di_Cura;

import java.util.Objects;
import java.util.Random;

public abstract class CasaDiCura implements Runnable{
    public final static int Posti_Sala_Attesa = 3;
    public final static Random r = new Random();
    private Paziente[] pazienti = new Paziente[1000];
    public abstract int getID();
    public abstract void pazienteEntra() throws InterruptedException;
        //Il pazziente deve poter entrare in sala di attesa
    public abstract void pazienteEsci() throws InterruptedException;
        //rilascio il semaforo che blocca i pazienti in sala di attesa
    public abstract void chiamaEIniziaOperazione() throws InterruptedException;
        //Cerca di chiamare un nuovo paziente dalla sala di attesa
    public abstract void fineOperazione() throws InterruptedException;
        //il medico segnala la fine delle operazioni non aspetta che il paziente esca
        //Il medico termina le operazioni e prepara la sala
    public abstract void addCount();
    public abstract int getCount();

    public String toString(){
        return "(ID: " + this.getID() + ")";
    }

    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        if (o instanceof CasaDiCura cdc){
            return cdc.getID() == this.getID();
        }
        return false;
    }

    public int hash(){
        return Objects.hash(this.getID());
    }

    public void lancioCasualePaziente() throws InterruptedException {
        Thread.sleep(this.r.nextInt(1000, 10000));
        Paziente p = new PazienteImpl(this.getCount(), this);
        this.addCount();
        Thread tp = new Thread(p);
        tp.start();
    }

    public void run(){
        Medico medico = new MedicoImpl(1, this);
        Thread tm = new Thread(medico);
        tm.start();
        while (true){
            try {
                this.lancioCasualePaziente();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}